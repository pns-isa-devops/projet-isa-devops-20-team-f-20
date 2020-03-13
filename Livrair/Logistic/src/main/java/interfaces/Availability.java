package interfaces;

import entities.Drone;

import javax.ejb.Local;
import java.util.Set;

@Local
public interface Availability {
    Set<Drone> getStatus();

    String hello(String hello_param);
}
