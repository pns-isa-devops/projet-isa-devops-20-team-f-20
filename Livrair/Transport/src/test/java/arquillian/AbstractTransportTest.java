package arquillian;

import exceptions.DroneAlreadyExistsException;
import interfaces.Availability;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;


public abstract class AbstractTransportTest {

    @Deployment
    public static WebArchive createDeployment(){
        WebArchive res = AbstractLivrairTest.createDeployment();
        return res.addPackage(Availability.class.getPackage())
                .addPackage(DroneAlreadyExistsException.class.getPackage());
    }
}
