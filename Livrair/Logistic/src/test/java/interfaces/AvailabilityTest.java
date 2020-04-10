package interfaces;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AvailabilityTest {


    private LogisticBean logisticBean;

    @BeforeEach
    public void setUp() {
        logisticBean = new LogisticBean();
    }

    @Test
    public void getAvailabilitiesDrone() {
        Drone d = new Drone("1");
        try {
            logisticBean.addDrone(d);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertFalse(logisticBean.getAvailableDrones().isEmpty());
        try {
            logisticBean.changeState(d, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertTrue(logisticBean.getAvailableDrones().isEmpty());
    }

    @Test
    public void changeStateByDrone() {
        Drone d = new Drone("1");
        try {
            logisticBean.addDrone(d);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(DroneStatus.AVAILABLE, d.getStatus());
        try {
            logisticBean.changeState(d, DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertNotEquals(DroneStatus.AVAILABLE, d.getStatus());
    }

    @Test
    public void changeStateById() {
        Drone d = new Drone("1");
        try {
            logisticBean.addDrone(d);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(DroneStatus.AVAILABLE, d.getStatus());
        try {
            logisticBean.changeState("1", DroneStatus.DELIVERING);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
        assertNotEquals(DroneStatus.AVAILABLE, d.getStatus());
    }

    @Test
    public void changeStateDroneDoesNotExist() {
        assertThrows(DroneDoesNotExistException.class, () -> logisticBean.changeState("1", DroneStatus.DELIVERING));
    }

    @Test
    public void changeStateDroneDoesNotExist2() {
        Drone d = new Drone("1");
        assertThrows(DroneDoesNotExistException.class, () -> logisticBean.changeState(d, DroneStatus.DELIVERING));
    }

    @Test
    public void addDroneId() {
        try {
            logisticBean.addDrone("1");
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, logisticBean.getDrones().size());
    }

    @Test
    public void addDroneIdAndAttributes() {
        try {
            logisticBean.addDrone("1", 100, 0);
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, logisticBean.getDrones().size());
    }

    @Test
    public void addDroneDrone() {
        try {
            logisticBean.addDrone(new Drone("1"));
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, logisticBean.getDrones().size());
    }

    @Test
    public void addDroneNotPossible() {
        try {
            logisticBean.addDrone("1");
        } catch (DroneAlreadyExistsException droneAlreadyExists) {
            droneAlreadyExists.printStackTrace();
        }
        assertEquals(1, logisticBean.getDrones().size());
        assertThrows(DroneAlreadyExistsException.class, () -> logisticBean.addDrone("1"));
        assertEquals(1, logisticBean.getDrones().size());
    }


}