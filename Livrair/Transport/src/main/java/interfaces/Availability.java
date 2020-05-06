package interfaces;

import entities.Drone;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Local
public interface Availability {
    Set<Drone> getDrones();

    Set<Drone> getAvailableDrones();

    Set<Drone> getAvailableDrones(LocalDateTime date);


}
