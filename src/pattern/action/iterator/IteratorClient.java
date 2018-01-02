package pattern.action.iterator;

/**
 * 迭代子模式可以顺序地访问一个聚集中的元素而不必暴露聚集的内部表示
 * Aggregate
 */
public class IteratorClient {
    public static void main(String... args) {
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        Aggregate agg = new ConcreteAggregate(objArray);
        Iterator it = agg.createIterator();
        System.out.println(it.currentItem());
        it.next();
        System.out.println(it.currentItem());
        it.next();
        System.out.println(it.currentItem());
        it.next();
        System.out.println(it.currentItem());
        it.next();
        System.out.println(it.currentItem());
        System.out.println(it.isDone());
        it.next();
        System.out.println(it.currentItem());
        System.out.println(it.isDone());
        it.next();
        System.out.println(it.currentItem());
        System.out.println(it.isDone());
        it.first();
        System.out.println(it.currentItem());
    }
}

interface Iterator {
    void first();
    void next();
    Boolean isDone();
    Object currentItem();
}

abstract class Aggregate {
    abstract Iterator createIterator();
}

class ConcreteIterator implements Iterator {
    private ConcreteAggregate agg;
    private int index;
    private int size;

    ConcreteIterator(ConcreteAggregate agg) {
        this.agg = agg;
        this.size = agg.size();
        this.index = 0;
    }

    @Override
    public void first() {
        this.index = 0;
    }

    @Override
    public void next() {
        if (this.index < this.size) {
            ++this.index;
        }
    }

    @Override
    public Boolean isDone() {
        return this.index >= size;
    }

    @Override
    public Object currentItem() {
        return agg.getElement(this.index);
    }
}

class ConcreteAggregate extends Aggregate {
    private Object[] objArray;
    private int size;

    ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
        this.size = this.objArray.length;
    }

    @Override
    Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    int size() {
        return size;
    }

    Object getElement(int index) {
        return index < this.size ? objArray[index] : null;
    }
}