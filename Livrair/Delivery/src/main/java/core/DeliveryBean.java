package core;

import entities.Package;
import entities.PackageStatus;
import exceptions.ExternalPartnerException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryBean implements PackageFinder, PackageInventory, DeliveryManager {

    private PackageSupplyAPI packageSupplyAPI;
    private List<Package> myPackages;

    @PersistenceContext
    private EntityManager manager;

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
    public void retrieveIncomingPackages() {
        myPackages = new ArrayList<>();
        try {
            packageSupplyAPI.retrievePackages().forEach((incomingPackage)->{
                //if(!findById(incomingPackage.getId()).isPresent())
                  //  manager.persist(incomingPackage);
                myPackages.add(incomingPackage); // To remove with persistency
            });
        } catch (ExternalPartnerException e) {
            e.printStackTrace();
        }

    }
}
