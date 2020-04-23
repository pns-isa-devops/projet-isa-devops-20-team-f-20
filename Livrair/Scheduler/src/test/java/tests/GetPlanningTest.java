package tests;

import arquillian.AbstractLivrairTest;
import arquillian.AbstractSchedulerTest;
import beans.SchedulerBean;
import entities.*;
import entities.Package;
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

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.*;


@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class GetPlanningTest extends AbstractSchedulerTest  {

    @EJB
    private PlanningInterface scheduler;

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager.persist(new Drone("1"));
        entityManager.persist(new Drone("2"));
    }

    @Test
    public void getPlanning() {
        deliveries.add(scheduler.planDelivery(new Package("3", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), deliveries).get());
        deliveries.add(scheduler.planDelivery(new Package("2", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)), deliveries).get());

        try {
            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(deliveries));
            assert(planning.isPresent());
            assert(planning.get().getAvailabilities().size() == 2); // TODO + de slots
        } catch (IllegalAccessException e) {
            assert (false);
        }
    }

    @Test
    public void getPlanningEmpty() {
        deliveries = new ArrayList<>();
        try {
            Optional<DailyPlanning> planning = Optional.of(scheduler.getPlanning(deliveries));
            assert(planning.isPresent());
            assert(planning.get().getAvailabilities().size() == 4);// TODO + de slots
        } catch (IllegalAccessException e) {
            assert (false);
        }
    }

    @Test
    public void getPlanningNull() {
        try {
            scheduler.getPlanning(null);
            assert(false);
        } catch (IllegalAccessException e) {
            assert(true);
        }
    }
}
