package interfaces;

import entities.Drone;
import entities.DroneStatus;
import interfaces.Availability;
import interfaces.DroneModifier;

import java.util.Set;

public class LogisticBean implements DroneModifier, Availability {


//    public void changeState(Drone drone, DroneStatus droneStatus) {
//
//    }
//
//    public Set<Drone> getStatus() {
//        return null;
//    }


//    @EJB
//    protected Payment cashier;

    @Override
    public Set<Drone> getStatus() {
        return null;
    }

    //    @Interceptors({CartCounter.class})
    @Override
    public String hello(String param){
        String rep = "Hello From Logistic Comp, your param is " + param;
        System.out.println(rep);
        return rep;
    }

    @Override
    public void changeState(Drone drone, DroneStatus droneStatus) {

    }
}
