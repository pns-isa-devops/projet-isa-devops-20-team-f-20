package webservice;


import entities.Delivery;
import entities.Package;
import entities.PackageStatus;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic")
public interface LogisticWebService {

    @WebMethod
    @WebResult(name = "matching_package")
    Package getPackageById(@WebParam(name = "id") String id);

    @WebMethod
    @WebResult(name = "delivery")
    List<Delivery> getPlannedDeliveries();

    @WebMethod
    @WebResult(name = "package")
    List<Package> getAllPackages();

    @WebMethod
    void retrieveIncomingPackages();

    @WebMethod
    @WebResult(name = "changed")
    boolean changePackageStatut(@WebParam(name = "id") String id, @WebParam(name = "packageStatus") PackageStatus packageStatus);

    @WebMethod
    @WebResult(name = "started")
    boolean startDelivery(@WebParam(name = "id") String id);

    @WebMethod
    @WebResult(name = "finished")
    boolean finishDelivery(@WebParam(name = "id") String id);

    @WebMethod
    @WebResult(name = "delivery")
    Delivery getDeliveryById(@WebParam(name = "id") String id);

}
