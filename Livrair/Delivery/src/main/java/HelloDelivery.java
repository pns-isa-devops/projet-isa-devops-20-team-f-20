import core.PackageSupplyAPI;
import entities.Package;
import entities.Supplier;
import exceptions.ExternalPartnerException;

import java.util.List;

public class HelloDelivery {

    void hello(){
        System.out.println("Hello Delivery");
    }

    public static void main(String[] args) {
        PackageSupplyAPI api = new PackageSupplyAPI("192.168.99.100", "1880", "123", new Supplier("UPS", "Biot"));

        try {
            List<Package> tmp = api.retrievePackages();
            System.out.println(tmp.get(1).getCustomerName());
        } catch (ExternalPartnerException e) {
            e.printStackTrace();
        }
    }
}
