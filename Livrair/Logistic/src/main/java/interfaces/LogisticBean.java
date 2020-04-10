package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Stateful;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Stateful
public class LogisticBean implements DroneModifier, Availability {

    private Set<Drone> drones = new HashSet<>();


    @Override
    public Set<Drone> getDrones() {
        return drones;
    }

    @Override
    public Set<Drone> getAvailableDrones() {
        return drones.stream().filter(drone -> drone.getStatus().equals(DroneStatus.AVAILABLE)).collect(Collectors.toSet());
    }


    @Override
    public void changeState(Drone drone, DroneStatus droneStatus) throws DroneDoesNotExistException {
        if (!checkIfDroneIdAlreadyExist(drone.getId())) {
            throw new DroneDoesNotExistException();
        }
        drone.setStatus(droneStatus);
    }

    @Override
    public void changeState(String droneId, DroneStatus droneStatus) throws DroneDoesNotExistException {
        if (!checkIfDroneIdAlreadyExist(droneId)) {
            throw new DroneDoesNotExistException();
        }
        drones.stream().filter(drone -> drone.getId().equalsIgnoreCase(droneId)).findFirst().ifPresent(drone -> drone.setStatus(droneStatus));
    }

    @Override
    public void addDrone(String id) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(id)) {
            throw new DroneAlreadyExistsException();
        }
        this.drones.add(new Drone(id));
    }

    @Override
    public void addDrone(String id, double chargeLevel, double flyingTime) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(id)) {
            throw new DroneAlreadyExistsException();
        }
        this.drones.add(new Drone(id, chargeLevel, flyingTime));
    }

    @Override
    public void addDrone(Drone drone) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(drone.getId())) {
            throw new DroneAlreadyExistsException();
        }
        this.drones.add(drone);
    }

    private boolean checkIfDroneIdAlreadyExist(String id) {
        return drones.stream().anyMatch(drone -> drone.getId().equalsIgnoreCase(id));
    }
}
