
import entities.Delivery;
import entities.Package;
import interfaces.Availability;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Date;
import java.util.List;

@Stateless
public class SchedulerBean implements PlanningInterface {


//    @PersistenceContext private EntityManager entityManager;
//    @Resource private ConnectionFactory connectionFactory;
//    @Resource(name = "KitchenPrinter") private Queue printerQueue;
//    @EJB CookieScheduler scheduler;
    @EJB private Availability availability;


    @Override
    public Delivery planDelivery(Package item, int deliveryDate) {
        return null;
    }

    @Override
    public String getPlanning(List<Delivery> deliveries) {
        return null;
    }
}
