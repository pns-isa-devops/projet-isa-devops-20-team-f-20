package interfaces;

import entities.DailyPlanning;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<Drone> getAvailableDrones(LocalDateTime date) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Drone> criteria = builder.createQuery(Drone.class);
        Root<Drone> root = criteria.from(Drone.class);
        criteria.select(root);
        TypedQuery<Drone> query = manager.createQuery(criteria);
        Set<Drone> result = new HashSet<>(query.getResultList());
        Set<Drone> tmp = new HashSet<>();
        for( Drone d : tmp){
            for(DailyPlanning dP : d.getDailyPlannings()){ //Todo If the daily of the date doesn't exist, then drone is available
                if(dP.availableSlotForGivenDate(date.getHour())){
                    tmp.add(d);
                }
            }
        }
        return tmp;
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
