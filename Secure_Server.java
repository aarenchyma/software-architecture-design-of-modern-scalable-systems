// Proxy

// Subject Interface
interface Server {
    void access(String userRole);
}

// Real Subject
class RealServer implements Server {
    public void access(String userRole) {
        System.out.println("Access granted to the secure server for role: " + userRole);
    }
}

// Proxy
class ServerProxy implements Server {
    private RealServer realServer = new RealServer();

    public void access(String userRole) {
        if (userRole.equalsIgnoreCase("Admin") || userRole.equalsIgnoreCase("Manager")) {
            realServer.access(userRole);
        } else {
            System.out.println("Access denied for role: " + userRole);
        }
    }
}


// Client
public class Secure_Server {

        public static void main(String[] args) {
               Server server = new ServerProxy();

        server.access("Admin");
        server.access("Guest");
        server.access("Manager");
        server.access("Intern");
            
        }
    
}