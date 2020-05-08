package webservices;

import entities.Drone;
import entities.DroneStatus;
import exceptions.DroneAlreadyExistsException;
import exceptions.DroneDoesNotExistException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.time.LocalDateTime;
import java.util.Set;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/transport")
public interface TransportWebService {

    @WebMethod
    @WebResult(name = "add_drone")
    boolean addDrone(@WebParam(name = "id") String id);


    @WebMethod
    @WebResult(name = "get_drones")
    Set<Drone> getDrones();

    @WebMethod
    @WebResult(name = "changed")
    boolean changeState(@WebParam(name = "id") String id, @WebParam(name = "droneStatus") DroneStatus droneStatus);


}
