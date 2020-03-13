package interfaces;

import entities.Drone;
import entities.DroneStatus;

public interface DroneModifier {
    void changeState(Drone drone, DroneStatus droneStatus);
}
