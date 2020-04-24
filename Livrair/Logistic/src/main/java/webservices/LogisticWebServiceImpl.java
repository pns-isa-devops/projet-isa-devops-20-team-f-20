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
    public void addDrone(String id) throws DroneAlreadyExistsException {
        modifier.addDrone(id);
    }
}
