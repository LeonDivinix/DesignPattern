package pattern.action.templatemethod;

/**
 * 可复用功能在父类完成，可变行为留给子类实现
 */
public class TemplateMethodClient {
    public static void main(String[] args) {
        AbstractClass c = new ConcreteClass();
        c.templateMethod();
    }
}


abstract class AbstractClass {
    public void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        hookMethod();
        System.out.println("完成操作");
    }

    abstract void primitiveOperation1(); // 必需实现的方法
    abstract void primitiveOperation2();
    protected void hookMethod(){} // 可选方法
}


class ConcreteClass extends AbstractClass {
    @Override
    void primitiveOperation1() {
        System.out.println("流程1完成");
    }

    @Override
    void primitiveOperation2() {
        System.out.println("流程2完成");
    }

    @Override
    protected void hookMethod() {
        System.out.println("可选流程完成");
    }
}