import core.PackageSupplyAPI;
import entities.Package;
import entities.Supplier;
import exceptions.ExternalPartnerException;
import org.apache.cxf.common.i18n.UncheckedException;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HelloDelivery {

    void hello(){
        System.out.println("Hello Delivery");
    }

    public static void main(String[] args) {

        PackageSupplyAPI api ;//= new PackageSupplyAPI("localhost", "9090", "123", new Supplier("UPS", "Biot"));
        try {
            Properties prop = new Properties();
            prop.load(HelloDelivery.class.getResourceAsStream("/supplier.properties"));
            api = new PackageSupplyAPI(prop.getProperty("supplierHostName"),
                    prop.getProperty("supplierPortNumber"), "123", new Supplier("UPS", "Biot"));
        } catch(IOException e) {
//            log.log(Level.INFO, "Cannot read supplier.properties file", e);
            throw new UncheckedException(e);
        }


        try {
            List<Package> tmp = api.retrievePackages();
            System.out.println(tmp.get(0).getCustomerName());
        } catch (ExternalPartnerException e) {
            e.printStackTrace();
        }
    }
}
