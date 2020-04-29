package webservice;

import core.DeliveryManager;
import entities.DailyPlanning;
import entities.Delivery;
import entities.Package;
import core.PackageFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic")
@Stateless(name="LogisticWS")
public class DeliveryWebServiceImpl implements LogisticWebService {

    @EJB(name="stateless-package") private PackageFinder finder;
    @EJB(name="stateless-deliveries") private DeliveryManager delivery;

    @Override
    public Package getPackageById(String id) {
        return finder.findById(id).get();
    }

    @Override
    public List<Delivery> getPlannedDeliveries() {
        return delivery.retrievePlannedDeliveries().get();
    }

    @Override
    public List<Package> getAllPackages() {
        return finder.getAllPackages().get();
    }


}
