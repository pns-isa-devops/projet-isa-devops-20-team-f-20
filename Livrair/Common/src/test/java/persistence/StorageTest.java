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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StorageTest extends AbstractLivrairTest {
    @PersistenceContext
    private EntityManager entityManager;

    private Package pack;
    private Supplier supplier;
    private Drone drone;
    private Delivery delivery;

    @Before
    public void setUp(){
        supplier = new Supplier("SupplierTest", "AddressSupplierTest");
        pack = new Package("66", "customerTest", PackageStatus.REGISTERED, "AddressTest", supplier);
        drone = new Drone("test");
        delivery = new Delivery(pack, drone, LocalDateTime.of(2020, 05 , 02 , 8, 10));

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
        entityManager.persist(delivery);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Delivery> criteria = builder.createQuery(Delivery.class);
        Root<Delivery> root = criteria.from(Delivery.class);
        criteria.select(root);

        Delivery stored = entityManager.createQuery(criteria).getSingleResult();

        assertEquals(stored, delivery);
        assertEquals(stored.getaPackage(), pack);
        assertEquals(stored.getDrone(), drone);
    }


    @Test
    public void storingDailyPlanning() throws Exception {
        List<Delivery> tmp = new ArrayList<>();
        tmp.add(delivery);
        DailyPlanning dailyPlanning = new DailyPlanning(new HashSet<>(), LocalDate.of(delivery.getDeliveryDate().getYear(), delivery.getDeliveryDate().getMonth(), delivery.getDeliveryDate().getDayOfMonth()));
        entityManager.persist(dailyPlanning);

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DailyPlanning> criteria = builder.createQuery(DailyPlanning.class);
        Root<DailyPlanning> root = criteria.from(DailyPlanning.class);
        criteria.select(root);

        DailyPlanning stored = entityManager.createQuery(criteria).getSingleResult();
        assertEquals(stored, dailyPlanning);
        assertEquals(dailyPlanning.getSlots().size(), stored.getSlots().size());
    }


}
