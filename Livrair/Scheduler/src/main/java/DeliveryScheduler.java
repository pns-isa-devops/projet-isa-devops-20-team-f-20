import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryScheduler {

    public static Delivery planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) {
        DailyPlanning dailyPlanning = PlanningCreator.create(deliveries);

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            return new Delivery(item, null, deliveryDate); // TODO Drone
        }
        else {
            // TODO not available
            return null;
        }
    }
}
