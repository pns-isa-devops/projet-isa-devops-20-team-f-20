package webservices;

import exceptions.DroneAlreadyExistsException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/logistic")
public interface LogisticWebService {

    @WebMethod
    @WebResult(name = "add_drone")
    boolean addDrone(@WebParam(name = "id") String id) throws DroneAlreadyExistsException;
}
