package arquillian;

import beans.SchedulerBean;
import core.DeliveryBean;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import interfaces.Availability;
import interfaces.DroneModifier;
import interfaces.LogisticBean;
import interfaces.PlanningInterface;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;


public abstract class AbstractSchedulerTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(PlanningInterface.class.getPackage())
                .addPackage(SchedulerBean.class.getPackage())
                .addPackage(DeliveryBean.class.getPackage());
    }
}
