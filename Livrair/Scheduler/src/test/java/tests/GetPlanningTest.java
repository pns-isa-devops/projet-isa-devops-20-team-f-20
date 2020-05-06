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


@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class GetPlanningTest extends AbstractSchedulerTest {
    @EJB
    private PlanningInterface scheduler;

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager.persist(new Drone("1"));
        entityManager.persist(new Drone("2"));
        Supplier ups = new Supplier("UPS", "Cannes");
        entityManager.persist(new Package("2t", "testuser", PackageStatus.REGISTERED,
                "210 avenue roumanille", ups ));
        entityManager.persist(new Package("3t", "testuser", PackageStatus.REGISTERED,
                "210 avenue roumanille", ups));
    }

    @Test
    public void getPlanning() {
        Delivery d1 = null, d2 = null;
        try {
            d1 = scheduler.planDelivery("3t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0))).get();
            d2 = scheduler.planDelivery("2t",
                    LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0))).get();
        } catch (Exception e) {
            e.printStackTrace();
            assert (false);
        }
        deliveries.add(d1);
        deliveries.add(d2);

        try {
            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(LocalDate.now()));
            assert (planning.isPresent());
            assert (planning.get().getAvailabilities().size() == 2); // TODO + de slots
        } catch (IllegalAccessException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void getPlanningEmpty() {
        deliveries = new ArrayList<>();
        try {
            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(LocalDate.now()));
            assert (planning.isPresent());
            assert (planning.get().getAvailabilities().size() == 4);// TODO + de slots
        } catch (IllegalAccessException e) {
            assert (false);
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void getPlanningNull() {
        try {
            scheduler.getPlanning(LocalDate.now());
        } catch (IllegalAccessException e) {
            System.out.println("Exception catched successfully");
            assert (true);
        } catch (Exception e) {
            assert (false);
        }
    }
}
