package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class TransportBean implements DroneModifier, Availability {


    @PersistenceContext
    private EntityManager manager;


    @Override
    public Set<Drone> getDrones() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root);
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre) {
            return new HashSet<>();
        }
    }

    @Override
    public Set<Drone> getAvailableDrones() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("status"), DroneStatus.AVAILABLE));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            return new HashSet<>(query.getResultList());
        } catch (NoResultException nre) {
            return new HashSet<>();
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
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("id"), droneId));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            query.getSingleResult().setStatus(droneStatus);
        } catch (NoResultException nre) {
            throw new DroneDoesNotExistException();
        }
    }

    @Override
    public void addDrone(String id) throws DroneAlreadyExistsException {
        if (checkIfDroneIdAlreadyExist(id)) {
            throw new DroneAlreadyExistsException();
        }
        this.manager.persist(new Drone(id));
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
        Set<Drone> toCheck;
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("status"), DroneStatus.AVAILABLE));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            toCheck = new HashSet<>(query.getResultList());
        } catch (NoResultException nre) {
            return false;
        }
        return toCheck.stream().anyMatch(drone -> drone.getId().equalsIgnoreCase(id));
    }
}
