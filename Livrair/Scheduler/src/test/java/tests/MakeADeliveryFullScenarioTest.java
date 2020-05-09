package tests;

import arquillian.AbstractSchedulerTest;
import interfaces.PlanningInterface;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class MakeADeliveryFullScenarioTest extends AbstractSchedulerTest {


    @EJB
    private PlanningInterface scheduler;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void getAndListPakcages() {
        // TODO: 09/05/2020  retrieve + getPackage
    }

    @Test
    public void addADroneAndPlanADelivery() {
        // TODO: 09/05/2020  addDrone + getDrone + plan delivery + checks status etc
    }

    @Test
    public void startTheDelivery() {
        // TODO: 09/05/2020  start + checks statuts
    }

    @Test
    public void finishTheDelivery() {
        // TODO: 09/05/2020  finish delivery + checks status + check bill
    }
}
