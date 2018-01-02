package pattern.structure.adapter;

public class ObjectAdapterClient {
    public static void main(String[] args) {
        ObjectAdaptee adaptee = new ObjectAdaptee();
        ObjectTarget adapter = new ObjectAdapter(adaptee);
        adapter.request();
    }
}

interface ObjectTarget {
    void request();
}

class ObjectAdaptee {
    void specificRequest() {
        System.out.println(this.toString());
    }
}

class ObjectAdapter implements ObjectTarget {
    private ObjectAdaptee adaptee;

    ObjectAdapter(ObjectAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        this.adaptee.specificRequest();
    }
}
