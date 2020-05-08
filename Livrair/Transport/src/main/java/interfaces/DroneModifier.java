package interfaces;

import entities.DroneStatus;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Local;

@Local
public interface DroneModifier {

    boolean changeState(String droneId, DroneStatus droneStatus);

    boolean addDrone(String id);

}
