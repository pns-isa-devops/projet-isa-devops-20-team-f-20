package interfaces;

import entities.DailyPlanning;
import entities.Delivery;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.Optional;

@Local
public interface PlanningInterface {

    Optional<Delivery> planDelivery(String id, LocalDateTime deliveryDate) throws Exception;

    DailyPlanning getPlanning() throws Exception;
}
