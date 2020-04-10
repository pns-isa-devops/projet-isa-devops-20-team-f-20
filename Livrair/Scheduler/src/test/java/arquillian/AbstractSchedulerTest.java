package arquillian;

import beans.DeliveryCreator;
import beans.DeliveryScheduler;
import beans.PlanningCreator;
import beans.SchedulerBean;
import entities.Delivery;
import entities.Supplier;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import interfaces.Availability;
import interfaces.DroneModifier;
import interfaces.LogisticBean;
import interfaces.PlanningInterface;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;


public abstract class AbstractSchedulerTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(PlanningInterface.class.getPackage())
                .addPackage(SchedulerBean.class.getPackage())
                .addPackage(Availability.class.getPackage())
                .addPackage(LogisticBean.class.getPackage())
                .addPackage(DroneModifier.class.getPackage())
                .addPackage(DroneAlreadyExistsException.class.getPackage())
                .addPackage(DroneDoesNotExistException.class.getPackage())
                .addPackage(DeliveryCreator.class.getPackage())
                .addPackage(PlanningCreator.class.getPackage())
                .addPackage(DeliveryScheduler.class.getPackage());


    }
}
