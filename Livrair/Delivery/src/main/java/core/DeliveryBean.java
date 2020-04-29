package core;

import entities.Delivery;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import exceptions.ExternalPartnerException;
import org.apache.cxf.common.i18n.UncheckedException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


@Stateless
public class DeliveryBean implements PackageFinder, PackageInventory, DeliveryManager {

    private PackageSupplyAPI packageSupplyAPI;
    private boolean retrieved = false;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Package> findById(String id) {
        getAllPackages();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root = criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Package> findByCustomer(String customerName) {
        getAllPackages();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root = criteria.from(Package.class);
        criteria.select(root).where(builder.equal(root.get("customerName"), customerName));
        TypedQuery<Package> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Package>> findByStatus(PackageStatus status) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Package>> getAllPackages() {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(Package.class)));

        if (manager.createQuery(cq).getSingleResult() < 1) {
            retrieveIncomingPackages();
        }
        CriteriaQuery<Package> criteria = builder.createQuery(Package.class);
        Root<Package> root = criteria.from(Package.class);
        criteria.select(root);
        TypedQuery<Package> query = manager.createQuery(criteria);

        try {
            return Optional.of(query.getResultList());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public void retrieveIncomingPackages() {
        try {
            Properties prop = new Properties();
            prop.load(DeliveryBean.class.getResourceAsStream("/supplier.properties"));
            packageSupplyAPI = new PackageSupplyAPI(prop.getProperty("supplierHostName"),
                    prop.getProperty("supplierPortNumber"), "123", new Supplier("UPS", "Biot"));
        } catch (IOException e) {
//            log.log(Level.INFO, "Cannot read supplier.properties file", e);
            throw new UncheckedException(e);
        }
        try {
            packageSupplyAPI.retrievePackages().forEach((incomingPackage) -> {
                manager.persist(incomingPackage);
            });
        } catch (ExternalPartnerException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Optional<List<Delivery>> retrievePlannedDeliveries() {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Delivery> criteria = builder.createQuery(Delivery.class);
        Root<Delivery> root = criteria.from(Delivery.class);
        criteria.select(root);
        TypedQuery<Delivery> query = manager.createQuery(criteria);
        try {
            return Optional.of(query.getResultList());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

}
