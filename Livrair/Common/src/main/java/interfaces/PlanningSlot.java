package interfaces;

import entities.Delivery;

public interface PlanningSlot {
    void getPlanning() ;
    void planDelivery(Delivery delivery);
}
