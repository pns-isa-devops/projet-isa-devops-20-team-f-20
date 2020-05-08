package tests;

import arquillian.AbstractLogisticTest;
import core.DeliveryManager;
import entities.*;
import entities.Package;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class retrievePlannedDeliveriesTest extends AbstractLogisticTest {
    @EJB
    private DeliveryManager deliveryManager;

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;
    Supplier supplier;

    @Before
    public void setup(){
        entityManager.persist(new Drone("1"));
        entityManager.persist(new Drone("2"));
        entityManager.persist(new Drone("3"));
        supplier = new Supplier("UPS", "Cannes");
        entities.Package pack1 = new entities.Package("0", "testuser0", PackageStatus.REGISTERED, "210 avenue roumanille", supplier);
        Delivery firstDelivery = new Delivery(pack1, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        deliveries.add(firstDelivery);
        entityManager.persist(firstDelivery);
        entities.Package pack2 = new entities.Package("1", "testuser1", PackageStatus.REGISTERED, "9 rue de la touche", supplier);
        Delivery secondDelivery = new Delivery(pack2, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
        deliveries.add(secondDelivery);
        entityManager.persist(secondDelivery);
        entities.Package pack3 = new Package("2", "testuser2", PackageStatus.REGISTERED, "310 promenade des anglais", supplier);
        Delivery newDayDelivery = new Delivery(pack3, null, LocalDateTime.of(LocalDate.now().plus(1, ChronoUnit.DAYS), LocalTime.of(15, 0)));
        deliveries.add(newDayDelivery);
        entityManager.persist(newDayDelivery);
    }

    @Test
    public void retrievePlannedDeliveriesTest(){
        assertEquals(deliveries.get(0),deliveryManager.retrievePlannedDeliveries().get().get(0));
        assertEquals(deliveries.get(1),deliveryManager.retrievePlannedDeliveries().get().get(1));
        assertEquals(deliveries.get(2),deliveryManager.retrievePlannedDeliveries().get().get(2));

    }




}
