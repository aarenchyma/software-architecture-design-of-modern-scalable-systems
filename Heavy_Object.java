// Subject Interface - Proxy
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filename);
    }

    public void display() {
        System.out.println("Displaying image: " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);  // Loaded only once
        }
        realImage.display();
    }
}


public class Heavy_Object {

        public static void main(String[] args) {

        Image image1 = new ProxyImage("Large_Ad_Image.jpg");

        System.out.println("Image created, but not loaded yet!");
        System.out.println("Now displaying image...");
        image1.display();

        System.out.println("Displaying again (should not reload):");
        image1.display();
            
        }
    
}