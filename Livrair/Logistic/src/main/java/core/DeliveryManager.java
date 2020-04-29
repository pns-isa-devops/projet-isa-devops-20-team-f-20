package core;

import entities.Delivery;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DeliveryManager {

    Optional<List<Delivery>> retrievePlannedDeliveries();

    // TODO suivi des deliveries
}
