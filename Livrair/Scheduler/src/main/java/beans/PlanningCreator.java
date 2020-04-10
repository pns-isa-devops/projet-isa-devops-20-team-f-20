package beans;

import entities.DailyPlanning;
import entities.Delivery;

import java.util.List;

public class PlanningCreator {
    public static DailyPlanning create(List<Delivery> deliveries) {
        return new DailyPlanning(deliveries);
    }
}
