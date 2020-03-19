package interfaces;

import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;

import javax.ejb.Local;
import java.time.LocalDateTime;
import java.util.List;

@Local
public interface PlanningInterface {

    Delivery planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries);

    DailyPlanning getPlanning(List<Delivery> deliveries);
}
