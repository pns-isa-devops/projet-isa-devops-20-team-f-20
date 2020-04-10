package beans;

import entities.DailyPlanning;
import entities.Delivery;
import entities.Drone;
import entities.Package;
import interfaces.Availability;

import javax.ejb.EJB;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DeliveryScheduler {

    @EJB
    private static Availability availability;

    public static Optional<Delivery> planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) {
        DailyPlanning dailyPlanning = PlanningCreator.create(deliveries);

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            Set<Drone> drones = new HashSet<>();//availability.getAvailableDrones();  <= TODO : Needs Arquillian for testing with Availability
            drones.add(new Drone("1"));
            if (drones.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(DeliveryCreator.create(item, drones.iterator().next(), deliveryDate));
            }
        }
        return Optional.empty();
    }
}
