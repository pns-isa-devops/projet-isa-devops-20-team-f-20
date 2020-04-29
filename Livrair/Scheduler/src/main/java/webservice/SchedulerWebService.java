package webservice;


import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/scheduler")
public interface SchedulerWebService {


    @WebMethod
    @WebResult(name = "is_planned")
    boolean planDelivery(@WebParam(name = "id") String id, @WebParam(name = "jour") int jour, @WebParam(name = "mois") int mois, @WebParam(name = "annee") int annee, @WebParam(name = "heure") int heure, @WebParam(name = "minute") int minute);

    @WebMethod
    @WebResult(name = "planning")
    DailyPlanning getPlanning();

}
