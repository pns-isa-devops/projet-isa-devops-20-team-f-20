package beans;

import entities.*;
import entities.Package;
import interfaces.Availability;
import interfaces.PlanningInterface;
import org.apache.openjpa.persistence.ArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Stateless
public class SchedulerBean implements PlanningInterface {

    @EJB private Availability availability;

    @Override
    public Optional<Delivery> planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) throws Exception {
        DailyPlanning dailyPlanning = new DailyPlanning(deliveries);;

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
        return Optional.empty();
    }

    @Override
    public DailyPlanning getPlanning(List<Delivery> deliveries) throws Exception {
        if(deliveries == null)
            throw new IllegalAccessException("Delivery is null");
        return new DailyPlanning(deliveries);
    }
}
