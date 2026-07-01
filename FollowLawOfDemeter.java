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

    public void drive() {

        // Rule 1: A method can call other methods of the same class (itself)
        checkFuel();

        // Rule 2: A method can call methods of its own instance variables
        engine.start();

        System.out.println("Car is driving...");
    }

    private void checkFuel() {
        System.out.println("Fuel OK.");
    }

    public void allowDriver(Driver driver) {

        // Rule 3: A method can call methods on objects passed as parameters
      
        driver.sit();
    }

    public void washCar() {

        // Rule 4: A method can call methods on objects it creates
        Cleaner cleaner = new Cleaner();

        
        cleaner.wash();
    }
}

public class FollowLawOfDemeter {

    public static void main(String[] args) {

       
        Car car = new Car();

        car.drive();

        Driver d = new Driver();
        car.allowDriver(d);

        car.washCar();
    }
}
