package pattern.action.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 只有一个检查点
 */
public class BlackMementoClient {
    public static void main(String[] args) {
        BlackOriginator o = new BlackOriginator();
        BlackCaretaker c = new BlackCaretaker();

        o.setState("On");
        c.saveMemento(o.createMemento());
        o.setState("off");
        c.saveMemento(o.createMemento());
        o.restoreMemento(c.retrieveMemento(0));
        System.out.println(o.getState());

        o.restoreMemento(c.retrieveMemento(1));
        System.out.println(o.getState());
    }
}

/**
 * 备忘录变成私有类
 */
class BlackOriginator {
    private String state;

    BlackMementoIF createMemento() {
        BlackMemento result = new BlackMemento();
        result.setState(state);
        return result;
    }

    void restoreMemento(BlackMementoIF memento) {
        this.state = ((BlackMemento)memento).getState();
    }

    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
        System.out.println("当前状态：" + this.state);
    }

    private class BlackMemento implements BlackMementoIF {
        private String state;

        String getState() {
            return state;
        }

        void setState(String state) {
            this.state = state;
        }
    }
}

interface BlackMementoIF {}

class BlackCaretaker {
    private List<BlackMementoIF> mementoList = new ArrayList<>();
    private int count;
    BlackMementoIF retrieveMemento(int index) {
        BlackMementoIF result = null;
        if (count > index) {
            result = this.mementoList.get(index);
        }
        return result;
    }

    void saveMemento(BlackMementoIF memento) {
        this.mementoList.add(memento);
        ++count;
    }
}