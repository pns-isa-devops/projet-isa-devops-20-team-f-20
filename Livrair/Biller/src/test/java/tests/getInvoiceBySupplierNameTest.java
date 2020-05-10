package tests;

import arquillian.AbstractBillerTest;
import core.InvoiceModifier;
import entities.*;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class getInvoiceBySupplierNameTest extends AbstractBillerTest {

    @EJB
    public InvoiceModifier invoiceModifier;

    private List<Invoice> invoices = new ArrayList<>();

    private List<Delivery> deliveries = new ArrayList<>();

    @PersistenceContext
    private EntityManager entityManager;
    Supplier supplier1;
    Supplier supplier2;



    @Before
    public void setup(){
        entityManager.persist(new Drone("1"));
        entityManager.persist(new Drone("2"));
        entityManager.persist(new Drone("3"));
        entityManager.persist(new Drone("4"));
        supplier1 = new Supplier("UPS", "Cannes");
        supplier2 = new Supplier("CHRONOPOST", "Pegomas");
        entityManager.persist(supplier1);
        entityManager.persist(supplier2);
        entities.Package pack1 = new entities.Package("0", "testuser0", "210 avenue roumanille", supplier1);
        Delivery firstDelivery = new Delivery(pack1, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)));
        entityManager.persist(firstDelivery);
        deliveries.add(firstDelivery);
        entities.Package pack2 = new entities.Package("1", "testuser1", "9 rue de la touche", supplier2);
        Delivery secondDelivery = new Delivery(pack2, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)));
        entityManager.persist(secondDelivery);
        deliveries.add(secondDelivery);
        entities.Package pack3 = new entities.Package("2", "testuser2", "412 promenade des anglais", supplier2);
        Delivery newDayDelivery = new Delivery(pack3, null, LocalDateTime.of(LocalDate.now().plus(1, ChronoUnit.DAYS), LocalTime.of(8, 0)));
        entityManager.persist(newDayDelivery);
        deliveries.add(newDayDelivery);
        invoiceModifier.addItem(deliveries.get(0).getId());
        invoiceModifier.addItem(deliveries.get(1).getId());
        invoiceModifier.addItem(deliveries.get(2).getId());

    }

    @Test
    public void getInvoicesBySupplierTest(){
        assertEquals(1,invoiceModifier.getInvoiceBySupplierName("UPS").get().size());
        assertEquals(2,invoiceModifier.getInvoiceBySupplierName("CHRONOPOST").get().size());


    }
}
