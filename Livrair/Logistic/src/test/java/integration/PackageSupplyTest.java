package integration;

import arquillian.AbstractLogisticTest;
import core.PackageFinder;
import core.PackageInventory;
import core.PackageSupplyAPI;
import entities.Package;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;



@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PackageSupplyTest extends AbstractLogisticTest {

    @EJB
    private PackageInventory packageInventory;

    @EJB
    private PackageFinder packageFinder;

    @PersistenceContext
    private EntityManager manager;

    private Package packTest, packTest2;
    private Supplier supp;

    @Before
    public void setUp() throws Exception{
        supp = new Supplier("Supp", "SuppAdr");
        packTest  = new Package("1", "testuser1", "210 avenue roumanille", supp);
        packTest2 = new Package("2", "testuser1", "10 promenade des anglais", supp);

        List<Package> tmp = new ArrayList<>();
        tmp.add(packTest);
        tmp.add(packTest2);

        PackageSupplyAPI mockedAPI = mock(PackageSupplyAPI.class);
        packageInventory.useSupplierReference(mockedAPI);
        when(mockedAPI.retrievePackages()).thenReturn(tmp);
    }


    @Test
    public void retrieveIncomingPackagesTest(){
        assertEquals(0, packageFinder.getAllPackages().get().size());
        //Mocked packages should be persisted now
        packageInventory.retrieveIncomingPackages();

        assertEquals(2, packageFinder.getAllPackages().get().size());
    }

    @Test
    public void retrieveIncomingPackagesAgainTest(){
        packageInventory.retrieveIncomingPackages();
        assertEquals(2, packageFinder.getAllPackages().get().size());

        packageInventory.retrieveIncomingPackages();
        //Asking for incoming packages again, none of them should be persisted for a second time
        assertEquals(2, packageFinder.getAllPackages().get().size());
    }


    @Test
    public void onlyMissingIncomingPackagesPersistedTest(){
        packageInventory.retrieveIncomingPackages();
        assertEquals(2, packageFinder.getAllPackages().get().size());

        manager.remove(packTest);
        //Removing one of the package, only one should remain in the DB
        assertEquals(1, packageFinder.getAllPackages().get().size());
        assertEquals(packTest2.getAddress(), packageFinder.getAllPackages().get().get(0).getAddress());

        //Asking for incoming packages, the missing package only should be added to the DB
        packageInventory.retrieveIncomingPackages();
        assertEquals(2, packageFinder.getAllPackages().get().size());

    }



}
