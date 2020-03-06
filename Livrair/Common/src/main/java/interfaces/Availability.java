package interfaces;

import entities.Drone;

import java.util.Set;

public interface Availability {
    Set<Drone> getStatus();
}
