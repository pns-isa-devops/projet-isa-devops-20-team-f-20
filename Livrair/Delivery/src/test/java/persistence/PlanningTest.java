package persistence;

import entities.Package;

import arquillian.AbstractLivrairTest;
import entities.PackageStatus;
import entities.Supplier;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class PlanningTest extends AbstractLivrairTest {
    @PersistenceContext private EntityManager entityManager;

    @Test
    public void getPlanningTest() throws Exception {
        assertTrue(true);
    }
}
