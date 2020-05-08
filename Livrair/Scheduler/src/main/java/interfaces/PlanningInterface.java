package interfaces;

import entities.DailyPlanning;
import entities.Delivery;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Local
public interface PlanningInterface {

    Optional<Delivery> planDelivery(String id, LocalDateTime deliveryDate) throws Exception;

    DailyPlanning getPlanning(LocalDate date) throws Exception;

    List<DailyPlanning> getPlanning(LocalDate from, LocalDate to) throws Exception;
}
