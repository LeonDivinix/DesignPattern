package pattern.action.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 单点白盒
 * 只有一个检查点
 */
public class WhiteMementoClient {
    public static void main(String[] args) {
        WhiteOriginator o = new WhiteOriginator();
        WhiteCaretaker c = new WhiteCaretaker();

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

class WhiteMemento {
    private String state;

    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
    }
}

class WhiteOriginator {
    private String state;

    WhiteMemento createMemento() {
        WhiteMemento result = new WhiteMemento();
        result.setState(state);
        return result;
    }

    void restoreMemento(WhiteMemento whiteMemento) {
        this.state = whiteMemento.getState();
    }

    String getState() {
        return state;
    }

    void setState(String state) {
        this.state = state;
        System.out.println("当前状态：" + this.state);
    }
}

class WhiteCaretaker {
    private List<WhiteMemento> whiteMementoList = new ArrayList<>();
    private int count;

    WhiteMemento retrieveMemento(int index) {
        WhiteMemento result = null;
        if (count > index) {
            result = whiteMementoList.get(index);
        }
        return result;
    }

    void saveMemento(WhiteMemento whiteMemento) {
        this.whiteMementoList.add(whiteMemento);
        ++count;
    }
}