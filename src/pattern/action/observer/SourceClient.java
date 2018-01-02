package pattern.action.observer;

import java.util.Vector;

public class SourceClient {
    public static void main(String[] args) {
        Watched o = new Watched();
        Watch w = new Watch(o);

        o.setData("new state 1");
    }
}

// java观察者模型源码------------------------------------

/**
 * 观察者接口
 */
interface Observer {
    void update (Observable o, Object arg);
}

/**
 * 主题
 */
class Observable {
    private boolean changed = false;
    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        this.changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized(this) {
            if (!changed) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; --i) {
            ((Observer)arrLocal[i]).update(this, arg);
        }
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }
}

// ---------------------------------------------------

class Watched extends Observable {
    private String data = "";

    String getData() {
        return data;
    }

    void setData(String data) {
        if (!this.data.equals(data)) {
            this.data = data;
            setChanged(); // 必须根据情况设置changed
            notifyObservers();
        }
    }
}

class Watch implements Observer {

    public Watch(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("状态发生改变：" + ((Watched)o).getData());
    }
}