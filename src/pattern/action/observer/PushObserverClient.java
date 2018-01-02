package pattern.action.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 推送模型
 * 不管观察者是否需要，推送主题对象的全部或部分数据
 */
public class PushObserverClient {
    public static void main(String[] args) {
        PushObserver o = new ConcretePushObserver();
        PushSubject s = new ConcretePushSubject();
        s.attach(o);

        PushObserver o2 = new ConcretePushObserver();
        s.attach(o2);
        s.setState("new state1");
        System.out.println("---------------");
        s.detach(o2);
        s.setState("new state2");
    }
}

interface PushObserver {
    void update(String state);
}

class ConcretePushObserver implements PushObserver {
    private String state;
    @Override
    public void update(String state) {
        this.state = state; // 主题推送数据
        System.out.println("观察者" + this.toString() + "状态变动为：" + this.state);
    }
}


abstract class PushSubject {

    private List<PushObserver> list = new ArrayList<>();

    abstract String getState();

    abstract void setState(String state);

    void attach(PushObserver o) {
        this.list.add(o);
        System.out.println("Attach an observer: " + o.toString());
    }

    void detach(PushObserver o) {
        this.list.remove(o);
        System.out.println("Remove an observer: " + o.toString());
    }

    void notifyObservers(String newState) {
        for(PushObserver observer : list){
            observer.update(newState);
        }
    }
}

class ConcretePushSubject extends PushSubject {
    private String state;
    @Override
    String getState() {
        return this.state;
    }

    @Override
    void setState(String state) {
        this.state = state;
        System.out.println("主题状态为：" + this.state);
        this.notifyObservers(this.state);
    }
}