package core;

import entities.Delivery;
import entities.PackageStatus;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DeliveryManager {

    Optional<List<Delivery>> retrievePlannedDeliveries();

    boolean changePackageStatut(String id, PackageStatus packageStatus);

    boolean startDelivery(String id);

    Optional<Delivery> getDeliveryById(String id);
    // TODO suivi des deliveries
}
