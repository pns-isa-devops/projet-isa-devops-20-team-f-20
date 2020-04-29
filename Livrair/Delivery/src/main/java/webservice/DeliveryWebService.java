package webservice;


import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery")
public interface DeliveryWebService {

    @WebMethod
    @WebResult(name = "matching_package")
    Package getPackageById(@WebParam(name = "id") String id);

    @WebMethod
    @WebResult(name = "planned_packages")
    List<Delivery> getPlannedDeliveries();

    @WebMethod
    @WebResult(name = "package")
    List<Package> getAllPackages();

}
