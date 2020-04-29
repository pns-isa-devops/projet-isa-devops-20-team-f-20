package arquillian;

import beans.SchedulerBean;
import core.LogisticBean;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import interfaces.Availability;
import interfaces.DroneModifier;
import interfaces.TransportBean;
import interfaces.PlanningInterface;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;


public abstract class AbstractSchedulerTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(PlanningInterface.class.getPackage())
                .addPackage(SchedulerBean.class.getPackage())
                .addPackage(LogisticBean.class.getPackage());
    }
}
