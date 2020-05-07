package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Local;

@Local
public interface DroneModifier {

    void changeState(String droneId, DroneStatus droneStatus) throws DroneDoesNotExistException;

    void addDrone(String id);

}
