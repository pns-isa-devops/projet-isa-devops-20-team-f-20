package tests;

import arquillian.AbstractSchedulerTest;
import entities.Delivery;
import entities.Drone;
import entities.Package;
import entities.Supplier;
import interfaces.PlanningInterface;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanDeliveryTest extends AbstractSchedulerTest {

    Supplier supplier;

    @EJB
    private PlanningInterface scheduler;

    @PersistenceContext
    private EntityManager entityManager;

//    @BeforeEach
    @Before
    public void setUp() {
        // Add & persist a Supplier
        entityManager.persist(new Supplier("UPS", "Cannes"));

        // Add & persist a Drone
        entityManager.persist(new Drone("1"));

        // Add & persist 2 Packages
        entityManager.persist(new Package("1t", "testuser1", "210 avenue roumanille", supplier));
        entityManager.persist(new Package("2t", "testuser2", "45 blv manouch", supplier));
        entityManager.persist(new Package("3t", "testuser3", "18 rue delal", supplier));
    }

//    @AfterEach
//    public void cleanUp() {
//        entityManager.rem
//    }

    @Test
    public void planSingleDelivery() {
        Optional<Delivery> d = null;

        try {
            d = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d.isPresent());
    }

    @Test
    public void planDeliveryAtHourThatDoesntExist() {
        Optional<Delivery> d = null;

        try {
            d = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0)));
        } catch (Exception e) {
            assert (false);
        }

        assertFalse(d.isPresent());
    }

    @Test
    public void plan2DeliveryAtTheSameSlot() {
        Optional<Delivery> d1 = null, d2 = null;

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertFalse(d2.isPresent());
    }

    @Test
    public void plan2DeliveryAt2DifferentsSlots() {
        Optional<Delivery> d1 = null, d2 = null;

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
    }

    @Test
    public void plan3DeliveryAt2DifferentsSlots() {
        Optional<Delivery> d1 = null, d2 = null, d3 = null;

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));

            d3 = scheduler.planDelivery("1t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 5)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
        assertFalse(d3.isPresent());
    }

    @Test
    public void plan3DeliveryAt2DifferentsSlotsWith2Drones() {
        Optional<Delivery> d1 = null, d2 = null, d3 = null;

        entityManager.persist(new Drone("2"));

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));

            d3 = scheduler.planDelivery("1t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 5)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
        assertTrue(d3.isPresent());
    }

    @Test
    public void plan2DeliveryAtTheSameSlotWith2Drones() {
        Optional<Delivery> d1 = null, d2 = null;
        entityManager.persist(new Drone("2"));

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
    }

    @Test
    public void plan3DeliveryAtTheSameSlotWith2Drones() {
        Optional<Delivery> d1 = null, d2 = null, d3 = null;

        entityManager.persist(new Drone("2"));

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

            d3 = scheduler.planDelivery("1t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 5)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
        assertFalse(d3.isPresent());
    }

    @Test
    public void plan3DeliveryAtTheSameSlotWith3Drones() {
        Optional<Delivery> d1 = null, d2 = null, d3 = null;

        entityManager.persist(new Drone("2"));
        entityManager.persist(new Drone("3"));

        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

            d3 = scheduler.planDelivery("1t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 5)));
        } catch (Exception e) {
            assert (false);
        }

        assertTrue(d1.isPresent());
        assertTrue(d2.isPresent());
        assertTrue(d3.isPresent());
    }
}
