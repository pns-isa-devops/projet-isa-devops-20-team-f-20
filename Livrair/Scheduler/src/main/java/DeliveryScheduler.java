import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DeliveryScheduler {

    public static Optional<Delivery> planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) {
        DailyPlanning dailyPlanning = PlanningCreator.create(deliveries);

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            return Optional.of( new Delivery(item, null, deliveryDate)); // TODO Drone
        }
        return Optional.empty();
    }
}
