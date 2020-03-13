package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Drone {

    @Id
    private String id;

    private double chargelevel;

    private double flyingTime;

    private DroneStatus status;

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

    public DroneStatus getStatus() {
        return status;
    }

    public void setStatus(DroneStatus status) {
        this.status = status;
    }
}