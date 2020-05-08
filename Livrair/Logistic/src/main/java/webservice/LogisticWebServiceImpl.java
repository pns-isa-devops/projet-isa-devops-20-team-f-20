package webservice;

import core.DeliveryManager;
import core.PackageFinder;
import core.PackageInventory;
import entities.Delivery;
import entities.Package;
import entities.PackageStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic")
@Stateless(name = "LogisticWS")
public class LogisticWebServiceImpl implements LogisticWebService {

    @EJB(name = "stateless-package")
    private PackageFinder finder;
    @EJB(name = "stateless-deliveries")
    private DeliveryManager delivery;

    @EJB
    private PackageInventory inventory;

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

    @Override
    public void retrieveIncomingPackages() {
        inventory.retrieveIncomingPackages();
    }

    @Override
    public boolean changePackageStatut(String id, PackageStatus packageStatus) {
        return delivery.changePackageStatut(id, packageStatus);
    }

    @Override
    public boolean startDelivery(String id) {
        return delivery.startDelivery(id);
    }

    @Override
    public Delivery getDeliveryById(String id) {
        return delivery.getDeliveryById(id).get();
    }


}
