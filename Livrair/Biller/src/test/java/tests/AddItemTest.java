package tests;

import arquillian.AbstractBillerTest;
import core.DeliveryManager;
import core.InvoiceModifier;
import entities.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


    @RunWith(Arquillian.class)
    @Transactional(TransactionMode.ROLLBACK)
    public class AddItemTest extends AbstractBillerTest {
        @EJB
        public InvoiceModifier invoiceModifier;

        public DeliveryManager deliveryManager;

        private List<Invoice> invoices = new ArrayList<>();

        private List<Delivery> deliveries = new ArrayList<>();

        @PersistenceContext
        private EntityManager entityManager;
        Supplier supplier;

        @Before
        public void setUp() {
            entityManager.persist(new Drone("1"));
            entityManager.persist(new Drone("2"));
            entityManager.persist(new Drone("3"));
            supplier = new Supplier("UPS", "Cannes");
            entityManager.persist(supplier);
            entities.Package pack1 = new entities.Package("0", "testuser0", "210 avenue roumanille", supplier);
            Delivery firstDelivery = new Delivery(pack1, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
            entityManager.persist(firstDelivery);
            deliveries.add(firstDelivery);
            entities.Package pack2 = new entities.Package("1", "testuser1", "9 rue de la touche", supplier);
            Delivery secondDelivery = new Delivery(pack2, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
            entityManager.persist(secondDelivery);
            deliveries.add(secondDelivery);
            entities.Package pack3 = new entities.Package("2", "testuser2", "310 promenade des anglais", supplier);
            Delivery newDayDelivery = new Delivery(pack3, null, LocalDateTime.of(LocalDate.now().plus(1, ChronoUnit.DAYS), LocalTime.of(15, 0)));
            entityManager.persist(newDayDelivery);
            deliveries.add(newDayDelivery);

        }

        @Test
        public void AddItem(){
            invoiceModifier.addItem(deliveries.get(0).getId());
            Invoice invoice = invoiceModifier.getInvoices().get(0);
            assertTrue(invoiceModifier.getInvoices().size() == 1);
            assertTrue(invoice.getDeliveries().size() == 1);
            invoiceModifier.addItem(deliveries.get(1).getId());
            assertTrue(invoiceModifier.getInvoices().size() == 1);
            assertTrue(invoice.getDeliveries().size() == 2);
            invoiceModifier.addItem(deliveries.get(2).getId());
            Invoice invoice2 = invoiceModifier.getInvoices().get(1);
            assertTrue(invoiceModifier.getInvoices().size() == 2);
            assertTrue(invoice.getDeliveries().size() == 2);
            assertTrue(invoice2.getDeliveries().size() == 1);



        }

}
