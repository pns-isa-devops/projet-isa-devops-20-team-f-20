import entities.Delivery;
import interfaces.Availability;
import interfaces.DeliveryAssigner;
import interfaces.PlanningSlot;

import javax.ejb.EJB;

public class SchedulerBean implements PlanningSlot {

    @EJB
    protected Availability availability;
    @EJB
    protected DeliveryAssigner deliveryAssigner;


    public void getPlanning() {

    }

    public void planDelivery(Delivery delivery) {

    }
}
