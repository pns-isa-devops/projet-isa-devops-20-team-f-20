package webservices;

import exceptions.DroneAlreadyExistsException;
import interfaces.Availability;
import interfaces.DroneModifier;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/transport")
@Stateless(name = "TransportWS")
public class TransportWebServiceImpl implements TransportWebService {

    @EJB(name = "stateless-transport")
    private Availability availability;
    @EJB
    private DroneModifier modifier;

    @Override

    public boolean addDrone(String id) {
        try {
            modifier.addDrone(id);
            return true;
        } catch (DroneAlreadyExistsException e) {
            e.printStackTrace();
            return false;
        }
    }
}