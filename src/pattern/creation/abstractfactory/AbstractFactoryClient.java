package pattern.creation.abstractfactory;

/**
 * 多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类可以创建多个具体产品类的实例。
 *
 * 适用于 增加产品族
 * 不适用 产品族增加产品
 */
public class AbstractFactoryClient {
    public static void main(String[] args) {
        System.out.println("生产产品族A1");
        AbstractFactory af = new ConcreteFactory1();
        AbstractProductA pa = af.createProductA();
        AbstractProductB pb = af.createProductB();
        pa.produce();
        pb.produce();

        System.out.println("生产产品族A2");
        af = new ConcreteFactory2();
        pa = af.createProductA();
        pb = af.createProductB();
        pa.produce();
        pb.produce();
    }
}


interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

/**
 * 生产产品族A1
 */
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}

/**
 * 生产产品族A2
 */
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}

interface AbstractProductA {
    void produce();
}

interface AbstractProductB {
    void produce();
}

class ProductA1 implements AbstractProductA {
    @Override
    public void produce() {
        System.out.println(this.toString());
    }
}

class ProductA2 implements AbstractProductA {
    @Override
    public void produce() {
        System.out.println(this.toString());
    }
}

class ProductB1 implements AbstractProductB {
    @Override
    public void produce() {
        System.out.println(this.toString());
    }
}

class ProductB2 implements AbstractProductB {
    @Override
    public void produce() {
        System.out.println(this.toString());
    }
}