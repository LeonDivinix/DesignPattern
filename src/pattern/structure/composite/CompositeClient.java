package pattern.structure.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 将有差异的类补充抽象为相同的类，进行泛用，避免特殊化
 *
 * 生成类似树的目录结构
 */
public class CompositeClient {

    public static void main(String[] args) {
        Component root = new Composite("服装");
        Component man = new Composite("男装");
        Component woman = new Composite("女装");

        Component shirt = new Leaf("衬衫");
        Component jark = new Leaf("夹克");
        Component suit = new Leaf("套装");

        root.add(man);
        root.add(woman);
        man.add(shirt);
        man.add(jark);
        woman.add(shirt);
        woman.add(suit);

        root.operation("");
        man.operation("");
    }
}

abstract class Component {
    abstract void operation(String preStr);

    public void add(Component component) {
        throw new UnsupportedOperationException("对象不支持增加功能");
    }

    public void remove(Component component) {
        throw new UnsupportedOperationException("对象不支持移除功能;");
    }

    public List<Component> getChild() {
        throw new UnsupportedOperationException("对象不支持获得子对象功能");
    }
}

/**
 * 支持全操作的类
 */
class Composite extends Component {
    private List<Component> childComponents = new ArrayList<>();
    private String name;

    Composite(String name) {
        this.name = name;
    }

    @Override
    void operation(String preStr) {
        System.out.println(preStr + "+" + this.name);
        if(this.childComponents != null) {
            preStr += "    ";
            for (Component child : childComponents) { // 循环调用了子对象的打印方法
                child.operation(preStr);
            }
        }
    }

    @Override
    public void add(Component component) {
        childComponents.add(component);
    }

    @Override
    public void remove(Component component) {
        childComponents.remove(component);
    }

    @Override
    public List<Component> getChild() {
        return childComponents;
    }
}

/**
 * 不支持全操作的类
 */
class Leaf extends Component {
    private String name;

    Leaf(String name) {
        this.name = name;
    }

    @Override
    void operation(String preStr) {
        System.out.println(preStr + "-" + this.name);
    }
}