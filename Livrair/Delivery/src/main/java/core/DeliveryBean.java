package core;

import entities.Delivery;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import exceptions.ExternalPartnerException;
import interfaces.PlanningInterface;
import org.apache.cxf.common.i18n.UncheckedException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@Stateless
public class DeliveryBean implements PackageFinder, PackageInventory, DeliveryManager {

    private PackageSupplyAPI packageSupplyAPI;
    private List<Package> myPackages;
    private List<Delivery> myDeliveries;

    @EJB
    private PlanningInterface schedulder;

    //@PersistenceContext
    //private EntityManager manager;

    @Override
    public Optional<Package> findById(String id) {
        /*CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }*/

        if (myPackages == null)
            retrieveIncomingPackages();
        return myPackages.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Package> findByCustomer(String customerName) {
        /*CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("customerName"), customerName));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }*/
        if (myPackages == null)
            retrieveIncomingPackages();
        return myPackages.stream().filter(p -> p.getCustomerName().equals(customerName)).findFirst();
    }

    @Override
    public Optional<List<Package>> findByStatus(PackageStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Package>> getAllPackages() {
        if (myPackages == null)
            retrieveIncomingPackages();
        return Optional.of(myPackages);
    }

    @Override
    public void retrieveIncomingPackages() {

//        packageSupplyAPI = new PackageSupplyAPI();
        try {
            Properties prop = new Properties();
            prop.load(DeliveryBean.class.getResourceAsStream("/supplier.properties"));
            packageSupplyAPI = new PackageSupplyAPI(prop.getProperty("supplierHostName"),
                    prop.getProperty("supplierPortNumber"), "123", new Supplier("UPS", "Biot"));
        } catch(IOException e) {
//            log.log(Level.INFO, "Cannot read supplier.properties file", e);
            throw new UncheckedException(e);
        }
        myPackages = new ArrayList<>();
        try {
            packageSupplyAPI.retrievePackages().forEach((incomingPackage) -> {
                //if(!findById(incomingPackage.getId()).isPresent())
                //  manager.persist(incomingPackage);
                myPackages.add(incomingPackage); // To remove with persistency
            });
        } catch (ExternalPartnerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<List<Delivery>> retrievePlannedDeliveries() {
        return Optional.of(myDeliveries);
    }

    @Override
    public boolean createDelivery(String id, LocalDateTime desiredTime) {
        if (myDeliveries == null) {
            myDeliveries = new ArrayList<>();
        }
        Optional<Package> pack = this.findById(id);
        if (pack.isPresent()) {
            Optional<Delivery> tmp = schedulder.planDelivery(pack.get(), desiredTime, myDeliveries);
            if (tmp.isPresent()) {
                pack.get().setPackageStatus(PackageStatus.ASSIGNED);
                myDeliveries.add(tmp.get());
                return true;
            }
        }
        return false;
    }
}
