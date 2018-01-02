package pattern.action.command;

/**
 * 命令模式把一个请求或者操作封装到一个对象中。
 * 命令模式允许系统使用不同的请求把客户端参数化，
 * 对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 *
 * 可理解为三层结构 receiver最底层 command封装receiver一个功能 invoker封装所需command 实际使用invoker
 */
public class CommandClient {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker(command);

        invoker.action();
    }
}

/**
 * 接收者角色类
 * 真正执行命令的类
 */
class Receiver {
    void action() {
        System.out.println("执行命令");
    }
}

/**
 * 抽象命令角色类
 */
interface Command {
    void execute();
}

class ConcreteCommand implements Command {
    private Receiver receiver;

    ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}

/**
 * 请求者角色类
 */
class Invoker {
    private Command command;

    Invoker(Command command) {
        this.command = command;
    }

    void action() {
        this.command.execute();
    }
}