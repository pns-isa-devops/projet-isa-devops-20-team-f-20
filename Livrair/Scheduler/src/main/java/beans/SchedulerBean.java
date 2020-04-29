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

        try {
            deliveries = deliveryManager.retrievePlannedDeliveries().get();
        } catch (NoSuchElementException e) {
            deliveries = new ArrayList<>();
        }

        try {
            item = packageFinder.findById(id).get();
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }

        DailyPlanning dailyPlanning = new DailyPlanning(DailyPlanning.fromDeliveries(deliveries));

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            Set<Drone> drones = availability.getAvailableDrones();
            if (drones.isEmpty()) {
                return Optional.empty();
            } else {
                Drone drone = drones.iterator().next();
                drone.setStatus(DroneStatus.DELIVERING);
                Delivery delivery = new Delivery(item, drone, deliveryDate);
                manager.persist(delivery);
                return Optional.of(delivery);
            }
        }
        return Optional.empty(); // TODO exception specifique ?
    }

    @Override
    public DailyPlanning getPlanning() throws Exception {
        List<Delivery> deliveries = null;

        try {
            deliveries = deliveryManager.retrievePlannedDeliveries().get();
            return new DailyPlanning(DailyPlanning.fromDeliveries(deliveries));
        } catch (NoSuchElementException e) {
            deliveries = new ArrayList<>();
            return new DailyPlanning(DailyPlanning.fromDeliveries(deliveries));

        }
    }
}
