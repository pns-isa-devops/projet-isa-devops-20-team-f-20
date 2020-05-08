package tests;

import arquillian.AbstractBillerTest;
import core.InvoiceModifier;
import entities.*;
import entities.Package;
import exceptions.InvoiceDoesNotExistException;
import org.apache.openejb.testing.PersistenceRootUrl;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class ChangeStateTest extends AbstractBillerTest {
    @EJB
    public InvoiceModifier invoiceModifier;

    private List<Invoice> invoices = new ArrayList<>();

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;
    Supplier supplier;

    @Before
    public void setUp() {
        entityManager.persist(new Drone("1"));
        supplier = new Supplier("UPS", "Cannes");
        entities.Package pack1 = new Package("0", "testuser0", PackageStatus.REGISTERED, "210 avenue roumanille", supplier);
        Delivery firstDelivery = new Delivery(pack1, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        deliveries.add(firstDelivery);
        entityManager.persist(firstDelivery);
        invoiceModifier.addItem("1");
    }

    @Test
    public void theStateChanged() throws InvoiceDoesNotExistException {
        Invoice invoice = invoiceModifier.getInvoices().get(0);
        assertTrue(invoice.getStatus() == InvoiceStatus.DRAFT);
        invoiceModifier.changeState(invoice,InvoiceStatus.SENT);
        assertTrue(invoice.getStatus() == InvoiceStatus.SENT);
        invoiceModifier.changeState(invoice,InvoiceStatus.PAID);
        assertTrue(invoice.getStatus() == InvoiceStatus.PAID);
    }
}
