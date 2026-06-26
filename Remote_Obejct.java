
// Proxy
interface Warehouse {
    void processOrder(String product, String location);
}


class RealWarehouse implements Warehouse {
    private String warehouseName;

    public RealWarehouse(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void processOrder(String product, String location) {
        System.out.println(warehouseName + " is processing order for " + product + " to " + location);
    }
}


class WarehouseProxy implements Warehouse {

    private RealWarehouse lahoreWarehouse = new RealWarehouse("Lahore Warehouse");
    private RealWarehouse karachiWarehouse = new RealWarehouse("Karachi Warehouse");
    private RealWarehouse islamabadWarehouse = new RealWarehouse("Islamabad Warehouse");

    public void processOrder(String product, String location) {
        if (location.equalsIgnoreCase("Punjab")) {
            lahoreWarehouse.processOrder(product, location);
        } else if (location.equalsIgnoreCase("Sindh")) {
            karachiWarehouse.processOrder(product, location);
        } else {
            islamabadWarehouse.processOrder(product, location);
        }
    }
}

public class Remote_Obejct {

     public static void main(String[] args) {
        Warehouse proxy = new WarehouseProxy();

        proxy.processOrder("Laptop", "Punjab");
        proxy.processOrder("TV", "Sindh");
        // proxy.processOrder("Book", "KPK");
    }
    
}