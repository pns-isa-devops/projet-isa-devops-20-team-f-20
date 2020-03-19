package core;

import javax.ejb.Local;
import java.time.LocalDateTime;

@Local
public interface DeliveryManager {
    boolean createDelivery(String id, LocalDateTime desiredTime);
}
