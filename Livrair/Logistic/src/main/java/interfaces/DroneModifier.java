package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Local;

@Local
public interface DroneModifier {
    void changeState(Drone drone, DroneStatus droneStatus) throws DroneDoesNotExistException;

    void changeState(String droneId, DroneStatus droneStatus) throws DroneDoesNotExistException;

    boolean addDrone(String id);

    void addDrone(String id, double chargeLevel, double flyingTime) throws DroneAlreadyExistsException;

    void addDrone(Drone drone) throws DroneAlreadyExistsException;

}
