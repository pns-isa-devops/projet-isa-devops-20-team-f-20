package tests;

import arquillian.AbstractLivrairTest;
import arquillian.AbstractSchedulerTest;
import beans.SchedulerBean;
import entities.Delivery;
import entities.PackageStatus;
import entities.Supplier;
import entities.Package;
import interfaces.PlanningInterface;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanDeliveryTest extends AbstractSchedulerTest {

    @EJB
    private PlanningInterface scheduler;

    private List<Delivery> deliveries = new ArrayList<>();

    @Before
    public void setUp() {
        Supplier supplier = new Supplier("UPS", "Cannes");
        Package pack = new Package("1", "testuser", PackageStatus.REGISTERED, "210 avenue roumanille", supplier);
        Delivery existingDelivery = new Delivery(pack, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        deliveries.add(existingDelivery);
    }

    @Test
    public void planDeliveryAvailable() {
        Optional<Delivery> d = scheduler.planDelivery(new Package("2", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)), deliveries);
        System.out.println(d);
        assertTrue(d.isPresent());
    }

    @Test
    public void planDeliveryNotAvailable(){
        Optional<Delivery> d = scheduler.planDelivery(new Package("2", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), deliveries);
        System.out.println(d);
        assertFalse(d.isPresent());
    }
}