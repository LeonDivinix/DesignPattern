package pattern.action.memento;

/**
 * 合并掉责任人Caretaker
 * 每个Memento对象保存当前状态
 */
public class SelfMementoClient {
    public static void main(String[] args) {
        SelfOriginator o = new SelfOriginator();
        o.changeState("state 0");
        SelfMementoIF memento0 = o.createMemento();
        o.changeState("state 1");
        SelfMementoIF memento1 = o.createMemento();
        o.changeState("state 2");
        SelfMementoIF memento2 = o.createMemento();
        System.out.println("-----恢复--------");
        o.restorMemento(memento1);
        o.restorMemento(memento0);
        o.restorMemento(memento2);

    }
}

interface SelfMementoIF {}

class SelfOriginator {
    private String state;

    void changeState(String state) {
        this.state = state;
        System.out.println("状态改变为:" + state);
    }

    SelfMemento createMemento() {
        return new SelfMemento(this);
    }

    void restorMemento(SelfMementoIF memento) {
        this.changeState(((SelfMemento) memento).state);

    }
    private class SelfMemento implements SelfMementoIF {
        private String state;

        private SelfMemento(SelfOriginator o) {
            this.state = o.state;
        }

        private String getState() {
            return state;
        }
    }
}
