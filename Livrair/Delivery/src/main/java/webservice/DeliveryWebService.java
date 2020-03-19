package webservice;


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
    public Package getPackageById(@WebParam(name = "id") String id);

    @WebMethod
    @WebResult(name = "planned_packages")
    public List<Delivery> getPlannedDeliveries();

    @WebMethod
    @WebResult(name = "is_planned")
    public boolean createDelivery(@WebParam(name="id") String id, @WebParam(name="jour") int jour, @WebParam(name="mois") int mois, @WebParam(name="annee") int annee, @WebParam(name="heure") int heure, @WebParam(name="minute") int minute);

}
