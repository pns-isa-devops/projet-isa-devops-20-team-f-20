package tests;

import arquillian.AbstractLogisticTest;
import core.DeliveryManager;
import core.PackageFinder;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class findByTest extends AbstractLogisticTest {

    @EJB
    private PackageFinder packageFinder;

    @EJB
    private DeliveryManager deliveryManager;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void setUp() {
        Supplier ups = new Supplier("UPS", "Cannes");
        Package pack1 = new Package("1", "testuser1",
                "210 avenue roumanille", ups);
        entityManager.persist(pack1);
        Package pack2 = new Package("2", "testuser1",
                "210 avenue das anglais", ups);
        entityManager.persist(pack2);
        Package pack3 = new Package("3", "testuser2",
                "9 rue de la touche", ups);
        entityManager.persist(pack3);
        Package pack4 = new Package("4", "testuser2",
                "9 rue de la touche", ups);
        entityManager.persist(pack4);
        Package pack5 = new Package("5", "testuser3",
                "10 promenade des anglais", ups);
        entityManager.persist(pack5);
        deliveryManager.changePackageStatut("5",PackageStatus.ASSIGNED);
    }

    @Test
    public void findByIdTest(){
        Supplier ups = new Supplier("UPS", "Cannes");
        Package packTest1 = new Package("2", "testuser1",
                "210 avenue roumanille", ups);
        assertTrue(packageFinder.findById("1").isPresent());
        assertEquals(packageFinder.findById("2").get(), packTest1);
        assertFalse(packageFinder.findById("999").isPresent());

    }

    @Test
    public void findByCustomerTest(){
        Supplier ups = new Supplier("UPS", "Cannes");
        Package packTest = new Package("3", "testuser2",
                "9 rue de la touche", ups);
        assertEquals(2, packageFinder.findByCustomer("testuser1").get().size());
        assertEquals(packageFinder.findByCustomer("testuser2").get().get(0), packTest);
    }

    @Test
    public void findByStatusTest(){
        Supplier ups = new Supplier("UPS", "Cannes");
        Package packTest  = new Package("1", "testuser1",
                "210 avenue roumanille", ups);
        Package packTestbis = new Package("5", "testuser3",
                "10 promenade des anglais", ups);
        packTestbis.setPackageStatus(PackageStatus.ASSIGNED);
         // TODO Fix
        assertEquals(4,packageFinder.findByStatus(PackageStatus.REGISTERED).get().size());
        assertEquals(packTest, packageFinder.findByStatus(PackageStatus.REGISTERED).get().get(0));
        assertEquals(1,packageFinder.findByStatus(PackageStatus.ASSIGNED).get().size());
        assertEquals(packTestbis, packageFinder.findByStatus(PackageStatus.ASSIGNED).get().get(0));
    }
}
