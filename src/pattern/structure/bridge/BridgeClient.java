package pattern.structure.bridge;

/**
 * 将抽象和实现部分分离
 * 将实现留给调用者,可用于回调设计
 */
public class BridgeClient {
    public static void main(String[] args) {
        Abstraction a = new Abstraction(new ConcreteImplementorA());
        a.operation();
    }
}

interface Implementor {
    void operationImp();
}

class Abstraction {
    private Implementor implementor;
    Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }
    void operation() {
        implementor.operationImp();
    }
}

class ConcreteImplementorA implements Implementor {
    public void operationImp() {
        System.out.println(this.toString());
    }
}

class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public void otherOperation() {}
}

class ConcreteImplementorB implements Implementor {
    public void operationImp() {
        System.out.println(this.toString());
    }
}