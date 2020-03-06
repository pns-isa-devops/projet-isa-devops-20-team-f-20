import entities.Drone;
import entities.DroneStatus;

import java.util.Set;

public class LogisticBean implements DroneModifier, Availability {


    public void changeState(Drone drone, DroneStatus droneStatus) {

    }

    public Set<Drone> getStatus() {
        return null;
    }
}
