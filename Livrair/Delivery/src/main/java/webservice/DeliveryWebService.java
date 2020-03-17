package webservice;


import entities.Package;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery")
public interface DeliveryWebService {

    @WebMethod
    @WebResult(name = "matching_package")
    public Package getPackageById(@WebParam(name = "id") String id);

}
