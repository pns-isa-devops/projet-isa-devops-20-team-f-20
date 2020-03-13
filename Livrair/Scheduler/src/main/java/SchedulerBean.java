import entities.Delivery;
import interfaces.Availability;

import javax.ejb.EJB;

public class SchedulerBean implements PlanningDelivery {

    @EJB
    protected Availability availability;


    public void getPlanning() {

    }

    public void planDelivery(Delivery delivery) {

    }
}
