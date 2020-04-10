package beans;

import entities.Delivery;
import entities.Drone;
import entities.DroneStatus;
import entities.Package;

import java.time.LocalDateTime;

public class DeliveryCreator {

    public static Delivery create(Package item, Drone drone, LocalDateTime deliveryDate) {
        drone.setStatus(DroneStatus.DELIVERING);
        return new Delivery(item, drone, deliveryDate);
    }
}
