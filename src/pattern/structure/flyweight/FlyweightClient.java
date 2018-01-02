package pattern.structure.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元模式以共享的方式高效地支持大量的细粒度对象。
 * 包含 复合享元 单纯享元
 * 单纯享元可共享
 * 复合享元对象本身不能共享，但是它们可以分解成单纯享元对象，而后者则可以共享。
 */
public class FlyweightClient {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight fly = factory.factory('a');
        fly.operation("First Call");

        fly = factory.factory('b');
        fly.operation("Second Call");

        fly = factory.factory('a');
        fly.operation("Third Call");


        List<Character> compositeState  = new ArrayList<>();
        compositeState .add('a');
        compositeState .add('b');
        compositeState .add('c');
        compositeState .add('a');
        compositeState .add('b');

        Flyweight unshared1 = factory.factory(compositeState);
        Flyweight unshared2 = factory.factory(compositeState);
        System.out.println("复合享元模式是否可以共享对象：" + (unshared1 == unshared2));
        unshared1.operation("First Call");
        unshared2.operation("Second Call");

        Flyweight shared1 = factory.factory('a');
        Flyweight shared2 = factory.factory('a');
        System.out.println("单纯享元模式是否可以共享对象：" + (shared1 == shared2));

    }
}

class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<>(); // 享元池 pool of flyweight ？单例数组

    public Flyweight factory(List<Character> compositeState) {
        UnsharedConcreteFlyweight unsharedFlyweight = new UnsharedConcreteFlyweight();
        for (Character state: compositeState) {
            unsharedFlyweight.add(state, this.factory(state));
        }
        return unsharedFlyweight;
    }

    public Flyweight factory(Character state) {
        Flyweight fly = files.get(state);
        if (fly == null) {
            fly = new ConcreteFlyweight(state);
            files.put(state, fly);
        }
        return fly;
    }
}

interface Flyweight {
    public void operation(String state);
}

class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState;

    ConcreteFlyweight(Character intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation(String state) {
        System.out.println("intrinsic state = " + this.intrinsicState);
        System.out.println("extrinsic state = " + state);
        System.out.println(this.toString());
        System.out.println("");
    }
}

/**状态
 * 复合
 */
class UnsharedConcreteFlyweight implements Flyweight {
    private Map<Character, Flyweight> allState = new HashMap<>();

    void add(Character key , Flyweight fly){
        allState.put(key,fly);
    }

    @Override
    public void operation(String state) {
        Flyweight fly;
        for(Character o : allState.keySet()){
            fly = allState.get(o);
            fly.operation(state);
        }
    }
}