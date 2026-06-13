import java.util.List;

class Employee {
    private String employeeId;
    private String name;
    private double salary;
    private String department;
    private String email;
    
    public void setEmployeeId(String employeeId) {
        if (employeeId == null || employeeId.isBlank()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (!employeeId.startsWith("EMP")) {
            throw new IllegalArgumentException("Employee ID must start with 'EMP'");
        }
        this.employeeId = employeeId;
    }
    
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("name cannot be less than 3 letters");
        }
        this.name = name;
    }
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be less than 0");
        }
        if (salary > 1000000) {
            throw new IllegalArgumentException("salary cannot be greater than 1,000,000");
        }
        this.salary = salary;
    }
    public void setDepartment(String department) {
        List<String> validDepartments = List.of("HR", "IT", "Finance", "Sales");
        if (!validDepartments.contains(department)) {
            throw new IllegalArgumentException("Department must be one of: HR, IT, Finance, Sales");
        }
        this.department = department;
    }
    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must contain '@' and '.'");
        }
        this.email = email;
    }
    
    // getters
    
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    
    
    public void increaseSalary(double percentage) {
        if (percentage <= 0) {
            throw new IllegalArgumentException("Percentage must be greater than 0");
        }
        if (percentage > 50) {
            throw new IllegalArgumentException("Salary increase cannot exceed 50%");
        }
        this.salary += this.salary * (percentage / 100);
    }

    public void displayEmployeeDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
    }
    
    public static void main(String[] args) {
        Employee emp = new Employee();
        
        try {
            emp.setEmployeeId("EMP001");
            emp.setName("Alice Johnson");
            emp.setSalary(50000);
            emp.setDepartment("IT");
            emp.setEmail("alice@company.com");
            emp.displayEmployeeDetails();
            
            System.out.println("\nApplying 20% salary increase...");
            emp.increaseSalary(20);
            System.out.println("New salary: " + emp.getSalary());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println();
        // invalid cases
        
        Employee emp2 = new Employee();
        
        try {
            emp2.setEmployeeId("ABC123");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            emp2.setName("Al");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            emp2.setSalary(-500);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            emp2.setDepartment("Marketing");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            emp2.setEmail("invalidemail");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            emp.increaseSalary(80);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}