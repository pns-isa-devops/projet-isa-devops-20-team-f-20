package core;

import entities.Delivery;
import entities.Package;
import entities.PackageStatus;
import exceptions.ExternalPartnerException;
import interfaces.PlanningInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Stateless
public class DeliveryBean implements PackageFinder, PackageInventory, DeliveryManager {

    private PackageSupplyAPI packageSupplyAPI;
    private List<Package> myPackages;
    private List<Delivery> myDeliveries;
    private boolean retrieved = false;

    @EJB
    private PlanningInterface schedulder;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Package> findById(String id) {
        if(!retrieved){
            retrieveIncomingPackages();
            retrieved = true;
        }
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
        /*
        if (myPackages == null)
            retrieveIncomingPackages();
        return myPackages.stream().filter(p -> p.getId().equals(id)).findFirst();*/
    }

    @Override
    public Optional<Package> findByCustomer(String customerName) {
        if(!retrieved){
            retrieveIncomingPackages();
            retrieved = true;
        }
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("customerName"), customerName));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Package>> findByStatus(PackageStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Package>> getAllPackages() {
        if(!retrieved){
            retrieveIncomingPackages();
            retrieved = true;
        }
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root =  criteria.from(Package.class);
        criteria.select(root);
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getResultList());
        } catch (NoResultException nre){
            return Optional.empty();
        }
    }

    @Override
    public void retrieveIncomingPackages() {

        packageSupplyAPI = new PackageSupplyAPI();
        myPackages = new ArrayList<>();
        try {
            packageSupplyAPI.retrievePackages().forEach((incomingPackage) -> {
                manager.persist(incomingPackage);
                    //myPackages.add(incomingPackage); // To remove with persistency
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
