package webservices;

import exceptions.DroneAlreadyExistsException;
import interfaces.Availability;
import interfaces.DroneModifier;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/logistic")
@Stateless(name = "LogisticWS")
public class LogisticWebServiceImpl implements LogisticWebService {

    @EJB(name="stateless-logistic") private Availability availability;
    @EJB private DroneModifier modifier;

    @Override
    public boolean addDrone(String id) {
        try {
            return modifier.addDrone(id);
        } catch (DroneAlreadyExistsException e) {
            e.printStackTrace();
            return false;
        }
    }
}
