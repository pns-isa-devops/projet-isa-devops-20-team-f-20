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
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

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

        List<Delivery> deliveries;
        Package item;

        deliveries = deliveryManager.retrievePlannedDeliveries().get().orElse(new ArrayList<>())));

        try {
            item = packageFinder.findById(id).get();
        } catch (NoSuchElementException e) {
            return Optional.empty(); // TODO exception specifique ?
        }

        if(!item.getPackageStatus().equals(PackageStatus.REGISTERED))
          return Optional.empty(); // TODO exception specifique ?

        DailyPlanning dailyPlanning = new DailyPlanning(DailyPlanning.fromDeliveries(deliveries));

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            Set<Drone> drones = availability.getAvailableDrones();
            if (drones.isEmpty()) {
                return Optional.empty();
            } else {
                Drone drone = drones.iterator().next();
                drone.setStatus(DroneStatus.DELIVERING);
                Delivery delivery = new Delivery(item, drone, deliveryDate);
                item.setPackageStatus(PackageStatus.ASSIGNED);
                manager.persist(delivery);
                return Optional.of(delivery);
            }
        }
        return Optional.empty(); // TODO exception specifique ?
    }

    @Override
    public DailyPlanning getPlanning() throws Exception {

        return new DailyPlanning(DailyPlanning.fromDeliveries(deliveryManager.retrievePlannedDeliveries().orElse(new ArrayList<>())));

    }
}
