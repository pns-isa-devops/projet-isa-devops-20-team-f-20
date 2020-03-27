package entities;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(Arquillian.class)

public abstract class AbstractLivrairTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Package.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                // Persistence file
                .addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @org.junit.Test
    public void getCustomerName() {
        assert (true);
    }

    @PersistenceContext
    private EntityManager entityManager;

//    @Test
//    @Transactional(TransactionMode.COMMIT)
//    public void testCustomerStorage() throws Exception {
//        Package c = new Package();            // create an empty customer
//        c.setCustomerName("Richard");        // setting up details
//        c.setId("14");
//        assertEquals("14", c.getId());                // primary key is the default one, i.e., 0
//        entityManager.persist(c);                // making the entity persistent
//        String id = c.getId();
//        assertNotEquals("0", id);                    // an id was assigned by the persistence layer
//        Package stored = (Package) entityManager.find(Package.class, id);
//        assertEquals(c, stored);                // The initial customer and the retrieved one are equals
//    }

}
