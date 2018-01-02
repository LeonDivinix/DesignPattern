package pattern.action.mediator;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 调停者模式包装了一系列对象相互作用的方式，使得这些对象不必相互明显引用
 *
 * 松散耦合
 *     调停者模式通过把多个同事对象之间的交互封装到调停者对象里面,从而使得同事对象之间松散耦合，
 *     基本上可以做到互补依赖。这样一来，同事对象就可以独立地变化和复用，而不再像以前那样“牵一处而动全身”了。
 * 集中控制交互
 *     多个同事对象的交互，被封装在调停者对象里面集中管理，使得这些交互行为发生变化的时候，
 *     只需要修改调停者对象就可以了，当然如果是已经做好的系统，那么就扩展调停者对象，而各个同事类不需要做修改。
 * 多对多变成一对多
 *     没有使用调停者模式的时候，同事对象之间的关系通常是多对多的，引入调停者对象以后，
 *     调停者对象和同事对象的关系通常变成双向的一对多，这会让对象的关系更容易理解和实现。
 *
 * 调停者模式的一个潜在缺点是，过度集中化。如果同事对象的交互非常多，而且比较复杂，
 * 当这些复杂性全部集中到调停者的时候，会导致调停者对象变得十分复杂，而且难于管理和维护。
 */
public class MediatorClient {
    public static void main(String[] args) {
        ConcreteMediator mediator = new ConcreteMediator();
        ConcreteColleagueA a = new ConcreteColleagueA(mediator);
        ConcreteColleagueB b = new ConcreteColleagueB(mediator);
        mediator.setColleagueA(a);
        mediator.setColleagueB(b);
        a.operation();
        b.operation();
    }
}

interface Mediator {
    void changed(Colleague colleague);
}

abstract class Colleague {
    private Mediator mediator;

    Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    Mediator getMediator() {
        return this.mediator;
    }
}

class ConcreteColleagueA extends Colleague {
    ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        this.getMediator().changed(this);
    }

    void messge() {
        System.out.println("B已经改变,请A响应");
    }
}

class ConcreteColleagueB extends Colleague {
    ConcreteColleagueB(Mediator mediator) {
        super(mediator);
    }

    public void operation() {
        this.getMediator().changed(this);
    }

    void messge() {
        System.out.println("A已经改变,请B响应");
    }
}

class ConcreteMediator implements Mediator {
    //持有并维护同事A
    private ConcreteColleagueA colleagueA;
    //持有并维护同事B
    private ConcreteColleagueB colleagueB;

    public void setColleagueA(ConcreteColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ConcreteColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    @Override
    public void changed(Colleague colleague) {
        if (colleague instanceof  ConcreteColleagueA) {
            // 可以使用ConcreteColleagueA的方法,然后操作其它同事
            colleagueB.messge();
        }
        else {
            colleagueA.messge();
        }
    }
}