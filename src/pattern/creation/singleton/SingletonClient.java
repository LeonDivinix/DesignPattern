package pattern.creation.singleton;

public class SingletonClient {
    public static void main(String[] args) {
        EnumSingleton s = EnumSingleton.uniqueInstance;
        s.setTest("a");
        System.out.println(s.getTest());
    }
}

class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {}

    public static synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/**
 * 饿汉式
 */
class EagerSingleton {
    private static EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}

/**
 * 双重检查加锁
 */
class Singleton {
    private static Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (null == instance) {
            synchronized(Singleton.class) {
                if (null == instance) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * Lazy initialization holder class模式
 * 使用了Java的类级内部类和多线程缺省同步锁的知识，很巧妙地同时实现了延迟加载和线程安全。
 *
 */
class HolderSingleton {
    private HolderSingleton() {}

    private static class SingletonHolder {
        private static HolderSingleton instance = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return SingletonHolder.instance;
    }
}

/**
 * 按照《高效Java 第二版》中的说法：单元素的枚举类型已经成为实现Singleton的最佳方法
 */
enum EnumSingleton {
    uniqueInstance;

    // -- 以下为测试用，序列单例只有 uniqueInstance(自定义)
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}