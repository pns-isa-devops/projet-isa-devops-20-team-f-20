package persistence;

import arquillian.AbstractLivrairTest;
import entities.Package;
import entities.PackageStatus;
import entities.Supplier;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class StorageTest extends AbstractLivrairTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void storingPackage() throws Exception {
        Package p = new Package("66", "customerTest", PackageStatus.REGISTERED, "AddressTest", new Supplier("SupplierTest", "AddressSupplierTest"));
        entityManager.persist(p);

        Package stored = entityManager.find(Package.class, p.getId());
        assertEquals(stored, p);
    }

    @Test
    public void storingSupplier() throws Exception {
        Supplier s = new Supplier("SupplierTest", "AddressSupplierTest");
        entityManager.persist(s);

        Supplier stored = entityManager.find(Supplier.class, s.getName());
        assertEquals(stored, s);
    }

}
