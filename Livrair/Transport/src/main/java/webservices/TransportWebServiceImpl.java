package webservices;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;
import interfaces.Availability;
import interfaces.DroneModifier;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/transport")
@Stateless(name = "TransportWS")
public class TransportWebServiceImpl implements TransportWebService {

    @EJB(name = "stateless-transport")
    private Availability availability;

    @EJB
    private DroneModifier modifier;

    @Override
    public void addDrone(String id) {
        try {
            modifier.addDrone(id);
        } catch (DroneAlreadyExistsException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void changeState(String droneId, DroneStatus droneStatus) {
        try {
            modifier.changeState(droneId, droneStatus);
        } catch (DroneDoesNotExistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Drone> getDrones(){
        return availability.getDrones();
    }



}
