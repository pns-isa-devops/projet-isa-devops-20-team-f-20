package arquillian;

import entities.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.lang.Package;


public abstract class AbstractLivrairTest {

    @Deployment
    public static WebArchive createDeployment(){
        return ShrinkWrap.create(WebArchive.class).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addPackage(Package.class.getPackage())
                .addPackage(Supplier.class.getPackage())
                .addPackage(Drone.class.getPackage())
                .addPackage(Delivery.class.getPackage())
                .addPackage(DailyPlanning.class.getPackage())
                .addPackage(Slot.class.getPackage())
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");


    }
}
