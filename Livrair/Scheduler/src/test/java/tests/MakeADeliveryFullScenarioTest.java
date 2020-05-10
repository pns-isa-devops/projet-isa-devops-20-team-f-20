package tests;

import arquillian.AbstractSchedulerTest;
import core.DeliveryManager;
import core.PackageFinder;
import core.PackageInventory;
import core.PackageSupplyAPI;
import entities.*;
import entities.Package;
import gherkin.lexer.Pa;
import interfaces.Availability;
import interfaces.DroneModifier;
import interfaces.PlanningInterface;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.COMMIT)
public class MakeADeliveryFullScenarioTest extends AbstractSchedulerTest {

    @EJB
    private PlanningInterface scheduler;

    @EJB
    private Availability availability;

    @EJB
    private DroneModifier droneModifier;

    @EJB
    private PackageInventory packageInventory;

    @EJB
    private PackageFinder packageFinder;

    @EJB
    private DeliveryManager deliveryManager;

    @PersistenceContext
    private EntityManager entityManager;

    private Package packTest, packTest2;
    private Supplier supp;

    private Drone drone;
    private Delivery delivery;

    @Before
    public void setUp() throws Exception{
        supp = new Supplier("Supp", "SuppAdr");
        entityManager.persist(supp);
        packTest  = new Package("1", "testuser1", "210 avenue roumanille", supp);
        packTest2 = new Package("2", "testuser1", "10 promenade des anglais", supp);

        List<Package> tmp = new ArrayList<>();
        tmp.add(packTest);
        tmp.add(packTest2);

        PackageSupplyAPI mockedAPI = mock(PackageSupplyAPI.class);
        packageInventory.useSupplierReference(mockedAPI);
        when(mockedAPI.retrievePackages()).thenReturn(tmp);
    }

    public void getAndListPakcages() {
        assertEquals(0, packageFinder.getAllPackages().get().size());
        //Mocked packages should be persisted now
        packageInventory.retrieveIncomingPackages();
        assertEquals(2, packageFinder.getAllPackages().get().size());

        assertEquals(packageFinder.findById(packTest.getId()).get(), packTest);
        assertEquals(PackageStatus.REGISTERED, packageFinder.findById(packTest.getId()).get().getPackageStatus());
    }

    public void addADroneAndPlanADelivery() {
        getAndListPakcages();


        droneModifier.addDrone("1");
        assertEquals(1, availability.getDrones().size());

        drone = (Drone) availability.getAvailableDrones().toArray()[0];

        Optional<Delivery> optionalDelivery = null ;
        try {
            optionalDelivery = scheduler.planDelivery("1", LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
        } catch (Exception e) {
            assert(false);
        }

        assert(optionalDelivery != null);
        assert(optionalDelivery.isPresent());

        delivery = optionalDelivery.get();

        assertEquals(DeliveryStatus.READY, delivery.getStatus());
        assertEquals(PackageStatus.ASSIGNED, delivery.getaPackage().getPackageStatus());
        assertEquals(DroneStatus.AVAILABLE, drone.getStatus());


    }

    public void startTheDelivery() {
        addADroneAndPlanADelivery();
        deliveryManager.getDeliveryById(delivery.getId()).get().start();

        assertEquals(DeliveryStatus.SENT, delivery.getStatus());
        assertEquals(PackageStatus.WAITING, delivery.getaPackage().getPackageStatus());
        assertEquals(DroneStatus.DELIVERING, drone.getStatus());
    }

    @Test
    public void finishTheDelivery() {
        startTheDelivery();
        deliveryManager.getDeliveryById(delivery.getId()).get().finish();

        assertEquals(DeliveryStatus.DONE, delivery.getStatus());
        assertEquals(PackageStatus.DELIVERED, delivery.getaPackage().getPackageStatus());
        assertEquals(DroneStatus.AVAILABLE, drone.getStatus());

        // TODO check la facture
    }
}
