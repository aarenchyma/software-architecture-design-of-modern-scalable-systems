interface Webpage {
    void display();
}

// Concrete Component
class SimpleWebpage implements Webpage {
    public void display() {
        System.out.println("This is Simple Webpage");
    }
}

// Base Decorator
abstract class Decorator1 implements Webpage {
    protected Webpage webpage;

    public Decorator1(Webpage webpage) {
        this.webpage = webpage;
    }

    public void display() {
        webpage.display();
    }
}

// Concrete Decorator 1
class Authenticate_User extends Decorator1 {

    public Authenticate_User(Webpage webpage) {
        super(webpage);
    }

    public void display() {
        super.display();
        authentication();
    }

    private void authentication() {
        System.out.println("Authenticating user credentials...");
    }
}

// Concrete Decorator 2
class Authorize_User extends Decorator1 {

    public Authorize_User(Webpage webpage) {
        super(webpage);
    }

    public void display() {
        super.display();
        authorization();
    }

    private void authorization() {
        System.out.println("Authorizing user access rights...");
    }
}

// Concrete Decorator 3
class Log_Activity extends Decorator1 {

    public Log_Activity(Webpage webpage) {
        super(webpage);
    }

    public void display() {
        super.display();
        log();
    }

    private void log() {
        System.out.println("Logging webpage access activity...");
    }
}

// Concrete Decorator 4
class Compress_Content extends Decorator1 {

    public Compress_Content(Webpage webpage) {
        super(webpage);
    }

    public void display() {
        super.display();
        compress();
    }

    private void compress() {
        System.out.println("Compressing webpage content...");
    }
}

// Client
public class Decorator {
    public static void main(String[] args) {

        Webpage page = new Log_Activity(new Compress_Content(new Authenticate_User(new SimpleWebpage())));

        page.display();
    }
}


// git remote set-url origin https://aarenchyma@github.com/aarenchyma/software-architecture-design-of-modern-scalable-systems.git
// git push