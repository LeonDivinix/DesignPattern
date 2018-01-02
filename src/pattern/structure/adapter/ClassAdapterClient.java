package pattern.structure.adapter;

/**
 * 类适配器
 * 为用户提供统一的兼容接口，但实际使用的是另外的接口
 */
public class ClassAdapterClient {
    public static void main(String[] args) {
        ClassTarget adapter = new ClassAdapter();
        adapter.request();
    }
}

interface ClassTarget {
    void request();
}

class ClassAdaptee {
    void specificRequest() {
        System.out.println(this.toString());
    }
}

class ClassAdapter extends ClassAdaptee implements ClassTarget {
    public void request() {
        specificRequest();
    }
}