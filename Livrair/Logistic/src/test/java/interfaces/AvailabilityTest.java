package interfaces;

import arquillian.AbstractLogisticTest;
import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class AvailabilityTest extends AbstractLogisticTest {


    @EJB
    private Availability availability;

    @EJB
    private DroneModifier modifier;

    private Drone drone;

    @Before
    public void setUp() {
        drone = new Drone("1");
    }

    @Test
    public void getAvailabilitiesDrone() {
        try {
            modifier.addDrone(drone);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertFalse(availability.getAvailableDrones().isEmpty());
        try {
            modifier.changeState(drone, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertTrue(availability.getAvailableDrones().isEmpty());
    }

    @Test
    public void changeStateByDrone() {
        try {
            modifier.addDrone(drone);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(DroneStatus.AVAILABLE, drone.getStatus());
        try {
            modifier.changeState(drone, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertNotEquals(DroneStatus.AVAILABLE, drone.getStatus());
    }

    @Test
    public void changeStateById() {
        try {
            modifier.addDrone(drone);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(DroneStatus.AVAILABLE, drone.getStatus());
        try {
            modifier.changeState("1", DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertNotEquals(DroneStatus.AVAILABLE, drone.getStatus());
    }

    @Test
    public void changeStateDroneDoesNotExist() {
        Throwable error = null;
        try {
            modifier.changeState("1", DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException ignored) {

        } catch (javax.ejb.EJBTransactionRolledbackException e2){
            error = e2.getCause().getCause();
        }
        assertNotNull(error);
        assertEquals(error.getClass(), DroneDoesNotExistException.class);
//        assertThrows(DroneDoesNotExistException.class, () -> modifier.changeState("1", DroneStatus.DELIVERING));
    }


    @Test
    public void changeStateDroneDoesNotExist2() {
        Throwable error = null;
        try {
            modifier.changeState(drone, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException ignored) {

        } catch (javax.ejb.EJBTransactionRolledbackException e2){
            error = e2.getCause().getCause();
        }
        assertNotNull(error);
        assertEquals(error.getClass(), DroneDoesNotExistException.class);
    }

    @Test
    public void addDroneId() {
        try {
            modifier.addDrone("1");
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, availability.getDrones().size());
    }

    @Test
    public void addDroneIdAndAttributes() {
        try {
            modifier.addDrone("1", 100, 0);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, availability.getDrones().size());
    }

    @Test
    public void addDroneDrone() {
        try {
            modifier.addDrone(new Drone("1"));
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, availability.getDrones().size());
    }

    @Test
    public void addDroneNotPossible() {
        try {
            modifier.addDrone("1");
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, availability.getDrones().size());
        assertThrows(DroneAlreadyExistsException.class, () -> modifier.addDrone("1"));
        assertEquals(1, availability.getDrones().size());
    }

}