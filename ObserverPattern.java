interface Observer {
    void update(String state);
}


interface Subject {
    void registerObserver(Observer o);
    void unregisterObserver(Observer o);
    void notifyObservers();
}


class Blog implements Subject {
    private Observer[] observers; 
    private int count;            
    private String state;         

    public Blog(int maxObservers) {
        observers = new Observer[maxObservers];
        count = 0;
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public String getState() {
        return state;
    }

    @Override
    public void registerObserver(Observer o) {
        if (count < observers.length) {
            observers[count++] = o;
            System.out.println("Subscriber added!");
        } else {
            System.out.println("Cannot add more subscribers!");
        }
    }

    @Override
    public void unregisterObserver(Observer o) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (observers[i] == o) {
                
                for (int j = i; j < count - 1; j++) {
                    observers[j] = observers[j + 1];
                }
                observers[count - 1] = null;
                count--;
                found = true;
                System.out.println("Subscriber removed!");
                break;
            }
        }
        if (!found) {
            System.out.println("Subscriber not found!");
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < count; i++) {
            observers[i].update(state);
        }
    }
}


class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String state) {
        System.out.println(name + " received update: " + state);
    }
}

public class ObserverPattern {

public static void main(String[] args) {
        
        Blog blog = new Blog(5);

      
        Observer s1 = new Subscriber("Alice");
        Observer s2 = new Subscriber("Bob");
        Observer s3 = new Subscriber("Charlie");

     
        blog.registerObserver(s1);
        blog.registerObserver(s2);
        blog.registerObserver(s3);

       
        blog.setState("New post: Understanding the Observer Pattern!");

       
        blog.unregisterObserver(s2);

        
        blog.setState("Another post: The Decorator Pattern Explained!");
    }
    
}