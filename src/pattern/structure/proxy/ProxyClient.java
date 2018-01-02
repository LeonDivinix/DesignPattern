package pattern.structure.proxy;

/**
 * 一些情况下，一个客户不想或者不能够直接引用一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用
 * 隐藏真正实现的类
 * 代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用
 * 代理对象及实际对象具有相同的接口
 */
public class ProxyClient{
    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.request();
    }
}

class Proxy extends Subject{
    private Subject realSubject = new RealSubject();
    @Override
    void request() {
        System.out.println("代理开始");
        realSubject.request();
        System.out.println("代理结束");
    }


}

abstract class Subject {
    abstract void request();
}

class RealSubject extends Subject {
    @Override
    void request() {
        System.out.println(this.toString());
    }
}