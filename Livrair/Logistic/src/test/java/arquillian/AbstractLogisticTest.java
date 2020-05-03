package arquillian;

import core.DeliveryManager;
import core.LogisticBean;
import core.PackageFinder;
import core.PackageInventory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class AbstractLogisticTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(PackageFinder.class.getPackage())
                .addPackage(PackageInventory.class.getPackage())
                .addPackage(DeliveryManager.class.getPackage())
                .addPackage(LogisticBean.class.getPackage());
    }
}
