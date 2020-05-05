package features;

import arquillian.AbstractLogisticTest;
import core.PackageFinder;
import core.PackageInventory;
import core.PackageSupplyAPI;
import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(CukeSpace.class)
@CucumberOptions(features = "src/test/resources")
public class retrievePackages extends AbstractLogisticTest {

    @EJB
    private PackageInventory packageInventory;

    @EJB
    private PackageFinder packageFinder;

    @PersistenceContext
    private EntityManager manager;

    private Supplier supp;

    private List<Package> packagesRetrieved;

    @Given("^(\\d+) packages from the supplier \"([^\"]*)\"$")
    public void packagesFromTheSupplier(int amount, String supplierName) throws Throwable {
        supp = new Supplier(supplierName, "SuppAdr");

        List<Package> tmp = new ArrayList<>();

        for(int i =0 ; i < amount ; i++){
            tmp.add(new Package(String.valueOf(i), "testuser"+String.valueOf(i), PackageStatus.REGISTERED,"address"+String.valueOf(i), supp));
        }

        PackageSupplyAPI mockedAPI = mock(PackageSupplyAPI.class);
        packageInventory.useSupplierReference(mockedAPI);
        when(mockedAPI.retrievePackages()).thenReturn(tmp);

        packageInventory.retrieveIncomingPackages();
    }

    @When("^Marcel asks for the list of packages$")
    public void marcelAsksForTheListOfPackages() throws Throwable {
        packagesRetrieved = packageFinder.getAllPackages().get();
    }

    @Then("^there are (\\d+) packages in the list$")
    public void thereArePackagesInTheList(int nb) throws Throwable {
        assertEquals(nb, packagesRetrieved.size());
    }


}
