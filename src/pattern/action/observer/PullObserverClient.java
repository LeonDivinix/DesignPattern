package pattern.action.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拉模型
 * 主题对象不知道观察者具体需要什么数据，干脆把自身传递给观察者，让观察者自己去按需要取值
 */
public class PullObserverClient {
    public static void main(String[] args) {
        PullObserver o = new ConcretePullObserver();
        PullSubject subject = new ConcretePullSubject();
        subject.attach(o);

        PullObserver o2 = new ConcretePullObserver();
        subject.attach(o2);
        subject.setSubjectState("new state");
        System.out.println("--------------------");
        subject.detach(o2);
        subject.setSubjectState("new state2");
    }
}

abstract class PullSubject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<PullObserver> list = new ArrayList<>();

    abstract String getSubjectState();
    abstract void setSubjectState(String subjectState);
    /**
     * 注册观察者对象
     * @param observer 观察者对象
     */
    void attach(PullObserver observer) {
        this.list.add(observer);
        System.out.println("Attach an observer: " + observer.toString());
    }

    /**
     * 删除观察者对象
     * @param observer 观察者对象
     */
    void detach(PullObserver observer) {
        list.remove(observer);
        System.out.println("Remove an observer: " + observer.toString());
    }

    /**
     * 通知所有注册的观察者对象
     */
    void notifyObservers() {
        for (PullObserver observer: list) {
            observer.update(this);
        }
    }
}

class ConcretePullSubject extends PullSubject {
    private String subjectState;

    @Override
    String getSubjectState() {
        return subjectState;
    }

    @Override
    void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
        System.out.println("主题状态为：" + subjectState);
        //状态发生改变，通知各个观察者
        this.notifyObservers();
    }
}

interface PullObserver {
    void update(PullSubject subject);
}

class ConcretePullObserver implements PullObserver {
    private String observerState;

    @Override
    public void update(PullSubject subject) {
        this.observerState = subject.getSubjectState(); // 观察者自行从主题对象中拉取数据
        System.out.println("观察者" + this.toString() + "状态变动为：" + this.observerState);
    }
}