package tests;

import arquillian.AbstractSchedulerTest;
import entities.Drone;
import entities.Package;
import entities.Supplier;
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


@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class GetPlanningTest extends AbstractSchedulerTest {
    @EJB
    private PlanningInterface scheduler;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        // Add & persist a Supplier
        Supplier supplier = new Supplier("UPS", "Cannes");
        entityManager.persist(supplier);

        // Add & persist a Drone
        entityManager.persist(new Drone("1"));

        // Add & persist 2 Packages
        entityManager.persist(new Package("1t", "testuser1", "210 avenue roumanille", supplier));
        entityManager.persist(new Package("2t", "testuser2", "45 blv manouch", supplier));
        entityManager.persist(new Package("3t", "testuser3", "18 rue delal", supplier));
    }


    @Test
    public void getPlanning() {
        // TODO
//        Delivery d1 = null, d2 = null;
//        try {
//            d1 = scheduler.planDelivery("3t",
//                    LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0))).get();
//            d2 = scheduler.planDelivery("2t",
//                    LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0))).get();
//        } catch (Exception e) {
//            e.printStackTrace();
//            assert (false);
//        }
//        deliveries.add(d1);
//        deliveries.add(d2);
//
//        try {
//            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(LocalDate.now()));
//            assert (planning.isPresent());
//            assert (planning.get().getAvailabilities().size() == 2); // TODO + de slots
//        } catch (IllegalAccessException e) {
//            assert (false);
//        } catch (Exception e) {
//            assert (false);
//        }
    }

    @Test
    public void getPlanningEmpty() {
        // TODO
//        deliveries = new ArrayList<>();
//        try {
//            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(LocalDate.now()));
//            assert (planning.isPresent());
//            assert (planning.get().getAvailabilities().size() == 4);// TODO + de slots
//        } catch (IllegalAccessException e) {
//            assert (false);
//        } catch (Exception e) {
//            assert (false);
//        }
    }

    @Test
    public void getPlanningNull() {
        // TODO
//        try {
//            scheduler.getPlanning(LocalDate.now());
//        } catch (IllegalAccessException e) {
//            System.out.println("Exception catched successfully");
//            assert (true);
//        } catch (Exception e) {
//            assert (false);
//        }
    }
}
