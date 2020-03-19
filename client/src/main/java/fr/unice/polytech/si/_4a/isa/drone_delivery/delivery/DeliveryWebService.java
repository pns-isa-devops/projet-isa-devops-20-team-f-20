package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2020-03-19T18:57:52.606+01:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "DeliveryWebService")
@XmlSeeAlso({ObjectFactory.class})
public interface DeliveryWebService {

    @WebMethod
    @RequestWrapper(localName = "getAllPackages", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetAllPackages")
    @ResponseWrapper(localName = "getAllPackagesResponse", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetAllPackagesResponse")
    @WebResult(name = "package", targetNamespace = "")
    public java.util.List<fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package> getAllPackages();

    @WebMethod
    @RequestWrapper(localName = "getPlannedDeliveries", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetPlannedDeliveries")
    @ResponseWrapper(localName = "getPlannedDeliveriesResponse", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetPlannedDeliveriesResponse")
    @WebResult(name = "planned_packages", targetNamespace = "")
    public java.util.List<fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Delivery> getPlannedDeliveries();

    @WebMethod
    @RequestWrapper(localName = "getPackageById", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetPackageById")
    @ResponseWrapper(localName = "getPackageByIdResponse", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.GetPackageByIdResponse")
    @WebResult(name = "matching_package", targetNamespace = "")
    public fr.unice.polytech.si._4a.isa.drone_delivery.delivery.Package getPackageById(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );

    @WebMethod
    @RequestWrapper(localName = "createDelivery", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.CreateDelivery")
    @ResponseWrapper(localName = "createDeliveryResponse", targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", className = "fr.unice.polytech.si._4a.isa.drone_delivery.delivery.CreateDeliveryResponse")
    @WebResult(name = "is_planned", targetNamespace = "")
    public boolean createDelivery(
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "jour", targetNamespace = "")
        int jour,
        @WebParam(name = "mois", targetNamespace = "")
        int mois,
        @WebParam(name = "annee", targetNamespace = "")
        int annee,
        @WebParam(name = "heure", targetNamespace = "")
        int heure,
        @WebParam(name = "minute", targetNamespace = "")
        int minute
    );
}
