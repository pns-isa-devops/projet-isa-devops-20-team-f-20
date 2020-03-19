package core;

import entities.Package;
import entities.PackageStatus;
import exceptions.ExternalPartnerException;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Stateless
public class DeliveryBean implements PackageFinder, PackageInventory, DeliveryManager {

    private PackageSupplyAPI packageSupplyAPI;
    private List<Package> myPackages;

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
        retrieveIncomingPackages();

        return Optional.of(myPackages.get(0));
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
        return null;
    }

    @Override
    public Optional<List<Package>> findByStatus(PackageStatus status) {
        return Optional.empty();
    }

    @Override
    public void retrieveIncomingPackages() {
        packageSupplyAPI = new PackageSupplyAPI();
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
    public boolean createDelivery(String id, LocalDateTime desiredTime) {
        Optional<Package> pack = this.findById(id);
        if (pack.isPresent()) {
            schedulder.planDelivery(pack.get(), desiredTime, null);
        } else {
            return false;
        }
        return false;
    }
}
