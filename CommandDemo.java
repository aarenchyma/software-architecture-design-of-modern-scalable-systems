interface Command {
    void execute();
    void unexecute(); 
}


class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void unexecute() {
        receiver.undoAction();
    }
}

class Receiver {
    public void action() {
        System.out.println("Receiver: Performing an action...");
    }

    public void undoAction() {
        System.out.println("Receiver: Undoing the action...");
    }
}
class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null)
            command.execute();
        else
            System.out.println("No command set!");
    }

    public void undoCommand() {
        if (command != null)
            command.unexecute();
        else
            System.out.println("No command to undo!");
    }
}


public class CommandDemo{

public static void main(String[] args) {
  
        Receiver r = new Receiver();


        Command c = new ConcreteCommand(r);

        Invoker w = new Invoker();

       
        w.setCommand(c);

        w.executeCommand();
        w.undoCommand();
    }
    
}