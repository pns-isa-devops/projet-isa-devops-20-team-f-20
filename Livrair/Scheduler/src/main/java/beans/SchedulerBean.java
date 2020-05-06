package beans;

import core.DeliveryManager;
import core.PackageFinder;
import entities.Package;
import entities.*;
import interfaces.Availability;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
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
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class SchedulerBean implements PlanningInterface {

    @EJB
    private Availability availability;

    @EJB
    private DeliveryManager deliveryManager;

    @EJB
    private PackageFinder packageFinder;

    @PersistenceContext  private EntityManager manager;


    @Override
    public Optional<Delivery> planDelivery(String id, LocalDateTime deliveryDate) throws Exception {
        Package item;
        try {
            item = packageFinder.findById(id).get();
        } catch (NoSuchElementException e) {
            return Optional.empty(); // TODO exception specifique ?
        }

        if(!item.getPackageStatus().equals(PackageStatus.REGISTERED))
          return Optional.empty(); // TODO exception specifique ?

        //Todo Replace For BestMatchingDailyPlanning eventually ?
        for(DailyPlanning dailyPlanning : getDailyPlanningsOf(LocalDate.of(deliveryDate.getYear(), deliveryDate.getMonth(), deliveryDate.getDayOfMonth()))){
            if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
                Set<Drone> drones = availability.getAvailableDrones(deliveryDate);
                if (drones.isEmpty()) {
                    return Optional.empty();
                } else {
                    Drone drone = drones.iterator().next();
                    drone.setStatus(DroneStatus.DELIVERING);
                    Delivery delivery = new Delivery(item, drone, deliveryDate);
                    item.setPackageStatus(PackageStatus.ASSIGNED);
                    manager.persist(delivery);
                    //Todo Replace by the right DailyPlanning in case it already exists...
                    drone.getDailyPlannings().add(dailyPlanning);
                    dailyPlanning.getBestMatchingSlotOf(deliveryDate.getHour()).book(delivery);
                    return Optional.of(delivery);
                }
            }
        }

        return Optional.empty(); // TODO exception specifique ?
    }

    private List<DailyPlanning> getDailyPlanningsOf(LocalDate date) throws Exception  {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root).where(builder.equal(root.get("planningDateTS"), String.valueOf(date.toEpochDay())));
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
            DailyPlanning dP = new DailyPlanning(DailyPlanning.fromDeliveries(deliveryManager.retrievePlannedDeliveries().orElse(new ArrayList<>())),date);
            manager.persist(dP); //Todo This persistence probably shouldnt be here
            List<DailyPlanning> tmp = new ArrayList<>();
            tmp.add(dP);
            return tmp;
        }
    }

    @Override
    public DailyPlanning getPlanning(LocalDate date) throws Exception {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root).where(builder.equal(root.get("planningDateTS"), String.valueOf(date.toEpochDay())));
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            DailyPlanning dP = new DailyPlanning(DailyPlanning.fromDeliveries(deliveryManager.retrievePlannedDeliveries().orElse(new ArrayList<>())),date);
            manager.persist(dP); //Todo This persistence probably shouldnt be here
            return dP;
        }

    }

    @Override
    public List<DailyPlanning> getPlanning(LocalDate from, LocalDate to) throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root);
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);

        List<DailyPlanning> tmp = query.getResultList();
        return tmp.stream().filter((dailyPlanning -> (from.toEpochDay() <= dailyPlanning.getDate().toEpochDay() && dailyPlanning.getDate().toEpochDay() <= to.toEpochDay()))).collect(Collectors.toList());
    }
}
