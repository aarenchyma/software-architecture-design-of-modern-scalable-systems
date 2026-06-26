interface Revenue_Component {

    double Get_Rev();
    void Show_Details();
}

class Emp implements Revenue_Component {

    private String name;
    private double Salary;

    public Emp(String name, double Salary) {
        this.name = name;
        this.Salary = Salary;
    }

    public double Get_Rev() {
        return Salary;
    }

    public void Show_Details() {
        System.out.println("Employee: " + name + ", Salary: " + Salary);
    }
}

class Department implements Revenue_Component {

    private String name;
    private int size;
    private Revenue_Component[] members;
    private int count = 0;

    public Department(String name, int size) {
        this.name = name;
        this.size = size;
        members = new Revenue_Component[size];
    }

    void Add_member(Revenue_Component member) {
        if (count < size) {
            members[count++] = member;
        }
    }

    public double Get_Rev() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += members[i].Get_Rev();
        }
        return total;
    }

    public void Show_Details() {
        System.out.println("Department: " + name);
        System.out.println("Total Revenue: " + Get_Rev());
        for (int i = 0; i < count; i++) {
            members[i].Show_Details();
        }
    }
}

public class Composite {

    public static void main(String[] args) {

        Emp e1 = new Emp("Ahmad", 20000);
        Emp e2 = new Emp("Sajid", 10000);
        Emp e3 = new Emp("Wali", 8000);
        Emp e4 = new Emp("Wajid", 5000);

        Department d1 = new Department("FCSE", 5);

        d1.Add_member(e1);
        d1.Add_member(e2);
        d1.Add_member(e3);
        d1.Add_member(e4);

        d1.Show_Details();
    }
}
