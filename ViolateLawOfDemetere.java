class Engine {
    public void start() {
        System.out.println("Engine starting...");
    }
}

class Driver {
    public void sit() {
        System.out.println("Driver sits in the car.");
    }
}

class Cleaner {
    public void wash() {
        System.out.println("Cleaning the car...");
    }
}

class Car {

    private Engine engine = new Engine();
    private Cleaner cleaner = new Cleaner();
    private Driver driver = new Driver();

    //  Problem: These getter methods expose internal objects.
    // This breaks encapsulation and increases coupling.
    // Now external classes can directly access Car's internal components.

    public Engine getEngine() {
        return engine;
    }

    public Cleaner getCleaner() {
        return cleaner;
    }

    public Driver getDriver() {
        return driver;
    }

    private void checkFuel() {
        System.out.println("Fuel OK.");
    }
}

public class ViolateLawOfDemetere {

    public static void main(String[] args) {

        Car car = new Car();

        // LAW OF DEMETER VIOLATION:
        // main() is calling a method on an object returned by another method.
        // This is a "friend of a friend" access.
        // main -> car -> engine -> start()
        // main should not know that Car has an Engine internally.
        car.getEngine().start();

        // Another violation:
        // main now knows that Car contains a Driver.
        // This increases dependency and tight coupling.
        car.getDriver().sit();

        //  Another violation:
        // main directly accesses Cleaner through Car.
        // If Car changes its internal structure, main will break.
        car.getCleaner().wash();
    }
}
