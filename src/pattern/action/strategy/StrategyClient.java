package pattern.action.strategy;

/**
 * 一个分支条件一个类，由客户端判断使用哪个分支
 * 创建实例->设置算法->调用实例方法传参
 *
 * 自行设置分支
 */
public class StrategyClient {
    public static void main(String[] args) {
        Contex contex = new Contex();
        contex.setStrategy(new ConcreteStrategyA());
        contex.contextInterface();
        contex.setStrategy(new ConcreteStrategyB());
        contex.contextInterface();
    }
}

class Contex {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        if (null != strategy) {
            this.strategy.algorithmInterface();
        }
    }
}

interface Strategy {
    void algorithmInterface();
}

class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("采用算法A：" + this.toString());
    }
}

class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithmInterface() {
        System.out.println("采用算法B：" + this.toString());
    }
}