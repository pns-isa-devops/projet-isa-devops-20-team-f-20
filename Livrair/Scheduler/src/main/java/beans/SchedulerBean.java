package beans;

import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;
import interfaces.Availability;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class SchedulerBean implements PlanningInterface {


//    @PersistenceContext private EntityManager entityManager;
//    @Resource private ConnectionFactory connectionFactory;
//    @Resource(name = "KitchenPrinter") private Queue printerQueue;
//    @EJB CookieScheduler scheduler;
    @EJB private Availability availability;

    @Override
    public Optional<Delivery> planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) {
        return DeliveryScheduler.planDelivery(item, deliveryDate, deliveries);
    }

    @Override
    public DailyPlanning getPlanning(List<Delivery> deliveries) {
        return PlanningCreator.create(deliveries);
    }
}
