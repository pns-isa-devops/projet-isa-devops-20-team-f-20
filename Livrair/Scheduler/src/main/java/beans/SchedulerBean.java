package beans;

import core.DeliveryManager;
import core.PackageFinder;
import entities.*;
import entities.Package;
import interfaces.Availability;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
                return Optional.of(new Delivery(item, drone, deliveryDate));
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
