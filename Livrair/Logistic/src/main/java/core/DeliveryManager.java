package core;

import entities.DailyPlanning;
import entities.Delivery;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Local
public interface DeliveryManager {

    Optional<List<Delivery>> retrievePlannedDeliveries();

    // TODO suivi des deliveries
}
