package pattern.action.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * todo: 理解
 * 解释器模式是类的行为模式。
 * 给定一个语言之后，解释器模式可以定义出其文法的一种表示，并同时提供一个解释器。
 * 客户端可以使用这个解释器来解释这个语言中的句子
 */
public class InterpreterClient {
    public static void main(String[] args) {
        Context ctx = new Context();
        Constant c = new Constant(true);
        Variable l = new Variable("x");
        ctx.assign(l, false);
        Variable r = new Variable("y");
        ctx.assign(r, true);

        Expression ex = new Or(new And(c, l), new And(r, new Not(l)));
        System.out.println("x=" + l.interpret(ctx));
        System.out.println("y=" + r.interpret(ctx));
        System.out.println(ex.toString() + "=" + ex.interpret(ctx));
    }
}

/**
 * 存取变量
 */
class Context {
    private Map<Variable, Boolean> map = new HashMap<>();

    /**
     * 存变量
     * @param var
     * @param value
     */
    void assign(Variable var, Boolean value) {
        map.put(var, value);
    }

    /**
     * 取变量
     * @param  var
     * @return Boolean
     * @throws IllegalArgumentException
     */
    Boolean lookup(Variable var) throws IllegalArgumentException {
        Boolean value = map.get(var);
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return value;
    }
}

abstract class Expression {
    public abstract boolean interpret(Context ctx);
    public abstract boolean equals(Object obj);
    public abstract int hashCode();
    public abstract String toString();
}

/**
 * 布尔常量
 */
class Constant extends Expression {
    private Boolean value;

    Constant(Boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Constant && this.value == ((Constant)obj).value;
    }

    @Override
    public boolean interpret(Context ctx) {
        return value;
    }


    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}

/**
 * 有名变量
 */
class Variable extends Expression {
    private String name;

    Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean interpret(Context ctx) {
        return ctx.lookup(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Variable && this.name.equals(((Variable)obj).name);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class And extends Expression {
    private Expression left, right;

    And(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Context ctx) {
        return left.interpret(ctx) && right.interpret(ctx); // 最终使用变量或常量的interpret
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof And && this.left.equals(((And)obj).left)
            && this.right.equals(((And)obj).right);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}

class Or extends Expression {
    private Expression left, right;

    Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(Context ctx) {
        return left.interpret(ctx) || right.interpret(ctx); // 最终使用变量或常量的interpret
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Or && this.left.equals(((Or)obj).left)
            && this.right.equals(((Or)obj).right);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "OR" + right.toString() + ")";
    }
}

class Not extends Expression {
    private Expression exp;

    Not(Expression exp) {
        this.exp = exp;
    }

    @Override
    public boolean interpret(Context ctx) {
        return !exp.interpret(ctx); // 最终使用变量或常量的interpret
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Not && this.exp.equals(((Not) obj).exp);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return "(NOT " + exp.toString() + ")";
    }
}