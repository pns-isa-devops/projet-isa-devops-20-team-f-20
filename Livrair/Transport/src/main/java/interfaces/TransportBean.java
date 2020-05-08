package interfaces;

import entities.Drone;
import entities.DroneStatus;
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
    public boolean changeState(String id, DroneStatus droneStatus) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Drone> query = manager.createQuery(criteria);
        try {
            query.getSingleResult().setStatus(droneStatus); // TODO logic switch drone statut
            return true;
        } catch (NoResultException nre) {
//            throw new DroneDoesNotExistException();
            return false;
        }
    }

    @Override
    public boolean addDrone(String id) {
        this.manager.persist(new Drone(id));
        // TODO droneAlredayExist
        return true;
    }

}
