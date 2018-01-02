package pattern.structure.decorator;

/**
 * 装饰模式, 可以在不使用创造更多子类的情况下，将对象的功能加以扩展
 */
public class DecoratorClient {
    public static void main(String[] args) {
        Component obj= new ConcreteComponent();
        Component a = new Decorator(obj);
        a.Operation();
        System.out.println("");
        a = new ConcreteDecoratorA(obj);
        a.Operation();
        System.out.println("");
        a = new ConcreteDecoratorB(obj);
        a.Operation();
    }
}

abstract class Component {
    public abstract void Operation();
}

class ConcreteComponent extends Component {
    @Override
    public void Operation() {
        System.out.println(this.toString());
    }
}

class Decorator extends Component {
    private Component component;

    Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void Operation() {
        this.component.Operation();
    }
}


class ConcreteDecoratorA extends Decorator {
    ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void Operation() {
        super.Operation();
        this.addedState();
    }

    void addedState() {
        System.out.println("addedState");
    }
}

class ConcreteDecoratorB extends Decorator {
    ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void Operation() {
        super.Operation();
        this.addedBehavior();
    }

    void addedBehavior() {
        System.out.println("addedBehavior");
    }
}