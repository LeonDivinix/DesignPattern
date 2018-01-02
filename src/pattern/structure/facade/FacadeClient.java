package pattern.structure.facade;

/**
 * 为复杂的子系统提供简洁的入口
 * 将所需的对象方法用单一的门面类封装,提供简单的方法
 * 门面类最好是单例类
 */
public class FacadeClient {
    public static void main(String[] args) {
        Facade obj = new Facade();
        System.out.println("simple:");
        obj.simpleHospital();
        System.out.println("");
        System.out.println("common:");
        obj.commonHospital();
    }
}
class Facade {
    void simpleHospital() {
        Register r = new Register();
        r.select();
        r.pay();

        Diagnose d = new Diagnose();
        d.ask();

        Medicine m = new Medicine();
        m.pay();
        m.gain();
    }

    void commonHospital() {
        Register r = new Register();
        r.card();
        r.select();
        r.pay();

        Diagnose d = new Diagnose();
        d.ask();
        d.blood();
        d.ct();

        Medicine m = new Medicine();
        m.pay();
        m.gain();
    }
}


class Register {
    void card() {
        System.out.println("    card");
    }

    void select() {
        System.out.println("    select");
    }

    void pay() {
        System.out.println("    pay register");
    }
}

class Diagnose {
    void ask() {
        System.out.println("    ask");
    }

    void blood() {
        System.out.println("    blood");
    }

    void ct() {
        System.out.println("    ct");
    }
}

class Medicine {
    void pay() {
        System.out.println("    pay medicine");
    }

    void gain() {
        System.out.println("    gain");
    }
}
