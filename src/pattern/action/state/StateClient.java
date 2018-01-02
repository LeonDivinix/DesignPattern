package pattern.action.state;

/**
 * 环境(Context)角色，也成上下文：定义客户端所感兴趣的接口，
 * 并且保留一个具体状态类的实例。这个具体状态类的实例给出此环境对象的现有状态。
 *
 * 抽象状态(State)角色：定义一个接口，用以封装环境（Context）对象的一个特定的状态所对应的行为。
 *
 * 具体状态(ConcreteState)角色：每一个具体状态类都实现了环境（Context）的一个状态所对应的行为。
 * 即一个分支条件一个类
 *
 * 分支条件的类对用户隐藏
 * 创建实例->调用实例方法传参 (隐藏 根据参数不同使用不同分支的类)
 *
 * 分支参数化
 */
public class StateClient {
    public static void main(String[] args) {
        Contex contex = new Contex();
        contex.request(1);
        contex.request(1);
        contex.request(2);
        contex.request(1);
    }
}

class Contex {
    private State state; // 维持一个实例

    void request(int param) {
        if (1 == param) {
            if (null == state || !(state instanceof ConcreteState1)) {
                state = new ConcreteState1();
            }
        }
        else {
            if (null == state || !(state instanceof ConcreteState2)) {
                state = new ConcreteState2();
            }
        }
        state.handle();
    }
}

interface State {
    void handle();
}

class ConcreteState1 implements State {
    @Override
    public void handle() {
        System.out.println("状态对应解决方案1：" + this.toString());
    }
}

class ConcreteState2 implements State {
    @Override
    public void handle() {
        System.out.println("状态对应解决方案2：" + this.toString());
    }
}