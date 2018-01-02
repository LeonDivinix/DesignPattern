package pattern.action.memento;

import java.util.ArrayList;
import java.util.List;

public class MementoClient {
    public static void main(String[] args) {
        Originator o = new Originator();
        Caretaker c = new Caretaker(o);
        //改变状态
        o.setState("state 0");
        //建立一个检查点
        System.out.println(c.createMemento());
        //改变状态
        o.setState("state 1");
        //建立一个检查点
        System.out.println(c.createMemento());
        //改变状态
        o.setState("state 2");
        //建立一个检查点
        System.out.println(c.createMemento());
        //改变状态
        o.setState("state 3");
        //建立一个检查点
        System.out.println(c.createMemento());
        //打印出所有检查点
        o.printStates();
        System.out.println("-----------------恢复检查点2-----------------");
        //恢复到第二个检查点
        c.restoreMemento(2);
        //打印出所有检查点
        o.printStates();
        System.out.println("-----------------恢复检查点1-----------------");
        //恢复到第一个检查点
        c.restoreMemento(1);
        //打印出所有检查点
        o.printStates();
    }
}
class Memento {
    private List<String> states;
    private int index;

    Memento(List<String> states, int index) {
        this.states = new ArrayList<>(states);
        this.index = index;
    }

    List<String> getStates() {
        return states;
    }

    int getIndex() {
        return index;
    }
}

class Originator {
    private List<String> states;
    private int index;

    Originator() {
        states = new ArrayList<>();
        index = 0;
    }

    /**
     * 工厂方法，返还一个新的备忘录对象
     */
    Memento createMemento() {
        return new Memento(states, index);
    }

    void restoreMemento(Memento memento) {
        this.states = memento.getStates();
        this.index = memento.getIndex();
    }

    void setState(String state) {
        this.states.add(state);
        ++this.index;
    }

    void printStates() {
        for (String state: states) {
            System.out.println(state);
        }
    }
}

class Caretaker {
    private Originator o;
    private List<Memento> mementos = new ArrayList<>();
    private int current;

    Caretaker(Originator o) {
        this.o = o;
        this.current = 0;
    }

    int createMemento() {
        Memento memento = o.createMemento();
        mementos.add(memento);
        return ++this.current;
    }

    void restoreMemento(int index) {
        Memento memento = mementos.get(index);
        o.restoreMemento(memento);
    }

    void removeMemento(int index) {
        mementos.remove(index);
    }
}