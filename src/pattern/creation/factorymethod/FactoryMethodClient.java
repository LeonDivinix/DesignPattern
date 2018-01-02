package pattern.creation.factorymethod;

/**
 一个抽象产品类，可以派生出多个具体产品类。
 一个抽象工厂类，可以派生出多个具体工厂类。
 每个具体工厂类只能创建一个具体产品类的实例。
 优点：
 （1）工厂方法模式就很好的减轻了工厂类的负担，把某一类/某一种东西交由一个工厂生产；（对应简单工厂的缺点1）
 （2）同时增加某一类”东西“并不需要修改工厂类，只需要添加生产这类”东西“的工厂即可，使得工厂类符合开放-封闭原则。
 缺点：
 （1）相比简单工厂，实现略复杂。
 （2）对于某些可以形成产品族的情况处理比较复杂。
 对于缺点（2），我们可以借用抽象工厂来实现。
 */
public class FactoryMethodClient {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        creator.anOperation();
    }
}

interface Product {
    void print();
}

class ConcreteProduct implements Product {
    @Override
    public void print() {
        System.out.println(this.toString());
    }
}

abstract class Creator {
    abstract Product factoryMethod();
    void anOperation() {
        Product product = factoryMethod();
        product.print();
    }
}

class ConcreteCreator extends Creator {
    @Override
    Product factoryMethod() {
        return new ConcreteProduct();
    }
}
