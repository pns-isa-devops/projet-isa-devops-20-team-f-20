package beans;

import core.DeliveryManager;
import core.PackageFinder;
import entities.Package;
import entities.*;
import interfaces.Availability;
import interfaces.PlanningInterface;
import org.joda.time.tz.UTCProvider;

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
import java.time.LocalTime;
import java.time.ZoneOffset;
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

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Optional<Delivery> planDelivery(String id, LocalDateTime deliveryDate) throws Exception {
        Package item;
        try {
            item = packageFinder.findById(id).get();
        } catch (NoSuchElementException e) {
            return Optional.empty(); // TODO exception specifique ?
        }

        if (!item.getPackageStatus().equals(PackageStatus.REGISTERED))
            return Optional.empty(); // TODO exception specifique ?

        DailyPlanning planning = getPlanning(deliveryDate.toLocalDate());
        Optional<Slot> tmp = planning.availableSlotForGivenDate(deliveryDate.getHour());
        if(tmp.isPresent()){
            Delivery delivery = new Delivery(item, tmp.get().getDrone(), deliveryDate);
            item.setPackageStatus(PackageStatus.ASSIGNED);
            manager.persist(delivery);
            tmp.get().book(delivery);
            return Optional.of(delivery);
        }
        // Sinon return empty

        return Optional.empty(); // TODO exception specifique ?
    }


    @Override
    public DailyPlanning getPlanning(LocalDate date) throws Exception {
        // TODO c'est ok ?
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root).where(builder.equal(root.get("planningDateTS"), String.valueOf(LocalDateTime.of(date, LocalTime.of(0,0)).toEpochSecond(ZoneOffset.UTC))));
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            DailyPlanning dP = new DailyPlanning(initSlots(availability.getDrones()), LocalDateTime.of(date, LocalTime.of(0,0)));
            manager.persist(dP); //Todo This persistence probably shouldnt be here
            return dP;
        }

    }

    @Override
    public List<DailyPlanning> getAllPlanning() throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root);
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Slot> getAllSlot() throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Slot> criteria = builder.createQuery(Slot.class);
        Root<Slot> root = criteria.from(Slot.class);
        criteria.select(root);
        TypedQuery<Slot> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    /**
     * Initialize default slot of one day
     */
    private List<Slot> initSlots(Set<Drone> flotte) {
        List<Slot> slots = new ArrayList<>();

        for(Drone d : flotte){
            Slot s1 = new Slot(8, 11, d);
            //manager.persist(s1);
            slots.add(s1);
            Slot s2 = new Slot(11, 14, d);
            //manager.persist(s2);
            slots.add(s2);
            Slot s3 = new Slot(14, 17, d);
            //manager.persist(s3);
            slots.add(s3);
            Slot s4 = new Slot(17, 20, d);
            //manager.persist(s4);
            slots.add(s4);
        }

        return slots;
    }

    @Override
    public List<DailyPlanning> getPlanning(LocalDate from, LocalDate to) throws Exception {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root);
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);

        List<DailyPlanning> tmp = query.getResultList();
        return tmp.stream().filter((dailyPlanning -> (from.toEpochDay() <= dailyPlanning.getPlanningDate().toLocalDate().toEpochDay() && dailyPlanning.getPlanningDate().toLocalDate().toEpochDay() <= to.toEpochDay()))).collect(Collectors.toList());
    }
}
