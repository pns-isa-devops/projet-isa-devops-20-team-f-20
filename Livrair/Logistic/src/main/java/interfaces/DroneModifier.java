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

    void addDrone(String id) throws DroneAlreadyExistsException;

    void addDrone(String id, double chargeLevel, double flyingTime) throws DroneAlreadyExistsException;

    void addDrone(Drone drone) throws DroneAlreadyExistsException;

}
