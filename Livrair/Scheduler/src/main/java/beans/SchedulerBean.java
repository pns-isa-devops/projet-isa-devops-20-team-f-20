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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

        for (Drone drone : availability.getAvailableDrones()) {
            DailyPlanning currentPlanning = drone.getDailyPlannings().getPlanningOfDate(deliveryDate.toLocalDate());
            if (currentPlanning == null) {
                drone.getDailyPlannings().addPlanning(deliveryDate.toLocalDate());
            }

            if (currentPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
                Delivery delivery = new Delivery(item, drone, deliveryDate);
                item.setPackageStatus(PackageStatus.ASSIGNED);
                manager.persist(delivery);
                currentPlanning.book(delivery, deliveryDate);

                // Soccupe du rechargement et de la revision
                chargeHandler(currentPlanning);
//               revisionHandler(currentPlanning); // TODO revision
                return Optional.of(delivery);
            }
        }
        // Sinon return empty

        return Optional.empty(); // TODO exception specifique ?
    }

    /**
     * If drone will need charge for next slots, plan charge
     *
     * @param currentPlanning drone planning's
     */
    private void chargeHandler(DailyPlanning currentPlanning) {
        int flightsCount = 0;
        List<Slot> slots = currentPlanning.getSlots();

        for (Slot s : slots ) {
            if (!s.isAvailable() && s.get() instanceof Delivery)
                flightsCount++;

            // TODO do smtg with that
        }
    }

    @Override
    public DailyPlanning getPlanning(LocalDate date) throws Exception {
        // TODO c'est ok ?
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root).where(builder.equal(root.get("date"), String.valueOf(date.toEpochDay())));
        TypedQuery<DailyPlanning> query = manager.createQuery(criteria);
        try {
            return query.getSingleResult();
        } catch (NoResultException nre) {
            DailyPlanning dP = new DailyPlanning(DailyPlanning.fromDeliveries(deliveryManager.retrievePlannedDeliveries().orElse(new ArrayList<>())), date);
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
