package pattern.creation.prototype;

/**
 * 克隆一个原型就类似于实例化一个类
 * 当一个类引用不支持序列化的间接对象，或者引用含有循环结构的时候，克隆方法很难
 *
 * 在深度克隆的过程中，很可能会出现循环引用的问题，必须小心处理
 */
public class PrototypeClient {
    public static void main(String[] args) {
        Prototype prototype = new ConcretePrototype1();
        System.out.println(prototype);

        Client client = new Client(prototype);
        client.operation();
    }
}

interface Prototype {
    Object clone();
}

class ConcretePrototype1 implements Prototype {
    public Object clone() {
        return new ConcretePrototype1();
    }
}

class ConcretePrototype2 implements Prototype {
    public Object clone() {
        return new ConcretePrototype2();
    }
}

class Client {
    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }

    public void operation() {
        System.out.println(prototype.clone());

    }
}