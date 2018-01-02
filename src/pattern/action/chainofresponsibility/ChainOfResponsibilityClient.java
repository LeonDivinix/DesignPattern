package pattern.action.chainofresponsibility;

/**
 * 责任链
 * 当前对象保存上一级对象,一旦条件不符合,则传递消息到上一级,直到有一个对象处理它为止
 */
public class ChainOfResponsibilityClient {

    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setSuccessor(handler2);
        //提交请求
        System.out.println("提交请求500");
        handler1.handleRequest(500);
        System.out.println("提交请求1000");
        handler1.handleRequest(1000);
        System.out.println("提交请求10000");
        handler1.handleRequest(10000);
    }
}


abstract class Handler {
    private  Handler successor;

    abstract void handleRequest(Integer max);

    Handler getSuccessor() {
        return successor;
    }

    void setSuccessor(Handler successor) {
        this.successor = successor;
    }
}

class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(Integer max) {
        System.out.println(this.toString());
        if (max <= 500) {
            System.out.println("处理请求成功");
        }
        else {
            if (getSuccessor() != null) {
                System.out.println("转交到上一级");
                getSuccessor().handleRequest(max);
            }
            else {
                System.out.println("没有上一级");
            }
        }
    }
}

class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(Integer max) {
        System.out.println(this.toString());
        if (max <= 1000) {
            System.out.println("处理请求成功");
        }
        else {
            if (getSuccessor() != null) {
                System.out.println("提交到上一级");
                getSuccessor().handleRequest(max);
            }
            else {
                System.out.println("没有上一级,处理请求失败");
            }
        }
    }
}