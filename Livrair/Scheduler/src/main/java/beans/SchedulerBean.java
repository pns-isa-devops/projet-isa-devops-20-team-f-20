package beans;

import entities.*;
import entities.Package;
import interfaces.Availability;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Stateless
public class SchedulerBean implements PlanningInterface {


//    @PersistenceContext private EntityManager entityManager;
//    @Resource private ConnectionFactory connectionFactory;
//    @Resource(name = "KitchenPrinter") private Queue printerQueue;
//    @EJB CookieScheduler scheduler;
    @EJB private Availability availability;

    @Override
    public Optional<Delivery> planDelivery(Package item, LocalDateTime deliveryDate, List<Delivery> deliveries) {
        DailyPlanning dailyPlanning = new DailyPlanning(deliveries);;

        if (dailyPlanning.availableSlotForGivenDate(deliveryDate.getHour())) {
            Set<Drone> drones = availability.getAvailableDrones();
            //drones.add(new Drone("1"));
            if (drones.isEmpty()) {
                return Optional.empty();
            } else {
                Drone drone = drones.iterator().next();
                drone.setStatus(DroneStatus.DELIVERING);
                return Optional.of(new Delivery(item, drone, deliveryDate));
            }
        }
        return Optional.empty();
    }

    @Override
    public DailyPlanning getPlanning(List<Delivery> deliveries) {
        return new DailyPlanning(deliveries);
    }
}
