import entities.Drone;
import entities.DroneStatus;
import interfaces.Availability;
import interfaces.DroneModifier;

import javax.ejb.EJB;
import java.util.Set;

public class LogisticBean implements DroneModifier, Availability {


    public void changeState(Drone drone, DroneStatus droneStatus) {

    }

    public Set<Drone> getStatus() {
        return null;
    }
}
