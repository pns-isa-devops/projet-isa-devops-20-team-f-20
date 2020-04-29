package tests;

import arquillian.AbstractSchedulerTest;
import entities.Package;
import entities.*;
import interfaces.PlanningInterface;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanDeliveryTest extends AbstractSchedulerTest {

    @EJB
    private PlanningInterface scheduler;

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;
    Supplier supplier;

    @Before
    public void setUp() {
        supplier = new Supplier("UPS", "Cannes");
        Package pack = new Package("1", "testuser", PackageStatus.REGISTERED, "210 avenue roumanille", supplier);
        Delivery existingDelivery = new Delivery(pack, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        deliveries.add(existingDelivery);
        entityManager.persist(new Drone("1"));

        entityManager.persist(existingDelivery);

        entityManager.persist(new Package("2t", "testuser", PackageStatus.REGISTERED,
                "210 avenue roumanille", new Supplier("UPS", "Cannes")));
        entityManager.persist(new Package("3t", "testuser", PackageStatus.REGISTERED,
                "210 avenue roumanille", new Supplier("UPS", "Cannes")));
    }

    @Test
    public void planDeliveryAvailable() {
        Optional<Delivery> d = null;

        try {
            d = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
        } catch (Exception e) {
            assert (false);
        }
        System.out.println(d);
        assertTrue(d.isPresent());
        deliveries.add(d.get());

    }

    @Test
    public void planDeliveryNotAvailable() {
        Optional<Delivery> d = null;
        try {
            d = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        } catch (Exception e) {
            assert (false);
        }
        System.out.println(d);
        assertFalse(d.isPresent());
    }
}
