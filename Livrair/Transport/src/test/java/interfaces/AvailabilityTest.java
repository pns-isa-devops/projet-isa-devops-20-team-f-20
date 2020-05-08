package interfaces;

import arquillian.AbstractTransportTest;
import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneDoesNotExistException;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AvailabilityTest extends AbstractTransportTest {


    @EJB
    private Availability availability;

    @EJB
    private DroneModifier modifier;

    private Drone drone;
    private Drone dronebis;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        entityManager.persist(drone = new Drone("1"));
    }

    @Test
    public void getAvailabilitiesDrone() {
        modifier.addDrone("3");

        assertFalse(availability.getAvailableDrones().isEmpty());
        assertTrue(modifier.changeState("3", DroneStatus.DELIVERING));
        assertTrue(modifier.changeState("1", DroneStatus.DELIVERING));
        assertTrue(availability.getAvailableDrones().isEmpty());
    }

    @Test
    public void changeStateById() {
        dronebis = new Drone("2");
        entityManager.persist(dronebis);
        assertEquals(DroneStatus.AVAILABLE, dronebis.getStatus());
        assertTrue(modifier.changeState("2", DroneStatus.DELIVERING));
        assertNotEquals(DroneStatus.AVAILABLE, dronebis.getStatus());
    }

    @Test
    public void changeStateDroneDoesNotExist() {
        assertFalse(modifier.changeState("4", DroneStatus.DELIVERING));
    }


/*    @Test
    public void changeStateDroneDoesNotExist2() {
        Throwable error = null;
        try {
            modifier.changeState(drone, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException ignored) {

        } catch (javax.ejb.EJBTransactionRolledbackException e2) {
            error = e2.getCause().getCause();
        }
        assertNotNull(error);
        assertEquals(error.getClass(), DroneDoesNotExistException.class);
    }*/

    @Test
    public void addDroneId() {
        assertEquals(1, availability.getDrones().size());

        modifier.addDrone("2");

        assertEquals(2, availability.getDrones().size());
    }


    @Test
    public void addDroneNotPossible() {
        modifier.addDrone("1");

        assertEquals(1, availability.getDrones().size());
    }
}
