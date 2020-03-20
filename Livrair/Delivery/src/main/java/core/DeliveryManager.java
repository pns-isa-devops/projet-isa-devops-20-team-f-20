package core;

import entities.Delivery;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Local
public interface DeliveryManager {
    boolean createDelivery(String id, LocalDateTime desiredTime);

    Optional<List<Delivery>> retrievePlannedDeliveries();
}
