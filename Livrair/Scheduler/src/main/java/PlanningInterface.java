import entities.Delivery;
import entities.Package;
import entities.DailyPlanning;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface PlanningInterface {

    Delivery planDelivery(Package item, Date deliveryDate, List<Delivery> deliveries);

    DailyPlanning getPlanning(List<Delivery> deliveries);
}
