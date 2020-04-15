package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class LogisticBean implements DroneModifier, Availability {

    private Set<Drone> drones = new HashSet<>();

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Set<Drone> getDrones() {
        //return drones;
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);
        criteria.select(root);
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return drones;
        }
    }

    @Override
    public Set<Drone> getAvailableDrones() {
        //return drones.stream().filter(drone -> drone.getStatus().equals(DroneStatus.AVAILABLE)).collect(Collectors.toSet());
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root =  criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("status"), DroneStatus.AVAILABLE));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre){
            return drones;
        }
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
    public boolean addDrone(String id) {
        /*if (checkIfDroneIdAlreadyExist(id)) {
            throw new DroneAlreadyExistsException();
        }*/
        //this.drones.add(new Drone(id));
        this.manager.persist(new Drone(id));
        return true;
    }

    @Override
    public void addDrone(String id, double chargeLevel, double flyingTime) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(id)) {
            throw new DroneAlreadyExistsException();
        }
        this.manager.persist(new Drone(id, chargeLevel, flyingTime));
    }

    @Override
    public void addDrone(Drone drone) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(drone.getId())) {
            throw new DroneAlreadyExistsException();
        }
        this.manager.persist(drone);
    }

    private boolean checkIfDroneIdAlreadyExist(String id) {
        return drones.stream().anyMatch(drone -> drone.getId().equalsIgnoreCase(id));
    }
}
