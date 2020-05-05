package persistence;

import arquillian.AbstractLivrairTest;
import entities.*;
import entities.Package;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StorageTest extends AbstractLivrairTest {
    @PersistenceContext
    private EntityManager entityManager;

    private Package pack;
    private Supplier supplier;
    private Drone drone;

    @Before
    public void setUp(){
        supplier = new Supplier("SupplierTest", "AddressSupplierTest");
        pack = new Package("66", "customerTest", PackageStatus.REGISTERED, "AddressTest", supplier);
        drone = new Drone("test");
    }

    @Test
    public void storingPackage() throws Exception {
        entityManager.persist(pack);

        Package stored = entityManager.find(Package.class, pack.getId());
        assertEquals(stored, pack);
    }

    @Test
    public void storingSupplier() throws Exception {
        entityManager.persist(supplier);
        Supplier stored = entityManager.find(Supplier.class, supplier.getName());
        assertEquals(stored, supplier);
    }

    @Test
    public void storingDrone() throws Exception {
        entityManager.persist(drone);
        Drone stored = entityManager.find(Drone.class, drone.getId());
        assertEquals(stored, drone);
    }

    @Test
    public void storingDelivery() throws Exception {
        Delivery d = new Delivery(pack, drone, LocalDateTime.now());
        entityManager.persist(d);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Delivery> criteria = builder.createQuery(Delivery.class);
        Root<Delivery> root = criteria.from(Delivery.class);
        criteria.select(root);

        Delivery stored = entityManager.createQuery(criteria).getSingleResult();

        assertEquals(stored, d);
        assertEquals(stored.getaPackage(), pack);
        assertEquals(stored.getDrone(), drone);
    }

}
