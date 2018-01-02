package pattern.action.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 * 可以通过访问者来定义整个对象结构通用的功能，从而提高复用程度。
 * 可以通过访问者来分离无关的行为，把相关的行为封装在一起，构成一个访问者，这样每一个访问者的功能都比较单一。
 *
 * 不适用于对象结构中的类经常变化的情况，因为对象结构发生了改变，访问者的接口和访问者的实现都要发生相应的改变，代价太高。
 *
 * 示例相当于实现了 双重分派 两个Element的对象方法都能调用到
 */
public class VisitorClient {
    public static void main(String[] args) {
        ObjectStructure oj = new ObjectStructure();
        oj.add(new ConcreteElementA());
        oj.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitor1();
        oj.action(visitor);

        // ----------------------------
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        Mozi mozi = new Mozi();
        // 参数判定使用静态类型
        mozi.ride(wh);
        mozi.ride(bh);
        // 方法调用使用实际类型
        wh.eat();
        bh.eat();
    }
}

class ObjectStructure {
    private List<Element> elementList = new ArrayList<>();
    void action (Visitor visitor) {
        for (Element element: elementList) {
            element.accept(visitor);
        }
    }

    void add(Element element) {
        elementList.add(element);
    }

}

abstract class Element {
    abstract void accept(Visitor v);
}

class ConcreteElementA extends Element {
    private Visitor v;

    @Override
    void accept(Visitor v) {
        v.visitA(this);
    }

    String operationA() {
        return this.toString();
    }
}

class ConcreteElementB extends Element {
    private Visitor v;
    @Override
    void accept(Visitor v) {
        v.visitB(this);
    }

    String operationB() {
        return this.toString();
    }
}

interface Visitor {
    void visitA(ConcreteElementA a);
    void visitB(ConcreteElementB b);
}

class ConcreteVisitor1 implements Visitor {
    @Override
    public void visitA(ConcreteElementA a) {
        System.out.println(this.toString() + " : " + a.operationA());
    }

    @Override
    public void visitB(ConcreteElementB b) {
        System.out.println(this.toString() + " : " + b.operationB());
    }
}

class ConcreteVisitor2 implements Visitor {
    @Override
    public void visitA(ConcreteElementA a) {
        System.out.println(this.toString() + " : " + a.operationA());
    }

    @Override
    public void visitB(ConcreteElementB b) {
        System.out.println(this.toString() + " : " + b.operationB());
    }
}

// -------------------------------------------------
class Horse {
    public void eat(){
        System.out.println("马吃草");
    }
}
class WhiteHorse extends Horse {
    @Override
    public void eat() {
        System.out.println("白马吃草");
    }
}
class BlackHorse extends Horse {
    @Override
    public void eat() {
        System.out.println("黑马吃草");
    }
}

class Mozi {

    void ride(Horse h){
        System.out.println("骑马");
    }

    void ride(WhiteHorse wh){
        System.out.println("骑白马");
    }

    void ride(BlackHorse bh){
        System.out.println("骑黑马");
    }

}