package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "drones")
public class Drone {

    private String id;

    private double chargelevel;

    private double flyingTime;

    private DroneStatus status;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getChargelevel() {
        return chargelevel;
    }

    public void setChargelevel(double chargelevel) {
        this.chargelevel = chargelevel;
    }

    public double getFlyingTime() {
        return flyingTime;
    }

    public void setFlyingTime(double flyingTime) {
        this.flyingTime = flyingTime;
    }

    @Enumerated(EnumType.STRING)
    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }

    public Drone() {
    }

    public Drone(String id) {
        this.id = id;
        this.chargelevel=100;
        this.flyingTime = 0;
        this.status = DroneStatus.AVAILABLE;
    }

    public Drone(String id, double chargelevel, double flyingTime) {
        this.id = id;
        this.chargelevel = chargelevel;
        this.flyingTime = flyingTime;
        this.status = DroneStatus.AVAILABLE;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drone)) return false;
        Drone drone = (Drone) o;
        return Double.compare(drone.getChargelevel(), getChargelevel()) == 0 &&
                Double.compare(drone.getFlyingTime(), getFlyingTime()) == 0 &&
                drone.getId().equals(getId()) &&
                getStatus() == drone.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),getChargelevel(), getFlyingTime(), getStatus());
    }
}
