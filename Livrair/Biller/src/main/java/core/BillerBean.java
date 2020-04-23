package core;

import entities.*;
import exceptions.InvoiceDoesNotExistException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class BillerBean implements InvoiceModifier {

    private Set<Invoice> invoices = new HashSet<>();

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException {
        if (!checkIfInvoiceIdAlreadyExistById(invoice.getId())) {
            throw new InvoiceDoesNotExistException();
        }
        invoice.setStatus(invoiceStatus);
    }

    @Override
    public boolean addItem(String id) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Delivery> criteria = builder.createQuery(Delivery.class);
        Root<Delivery> root =  criteria.from(Delivery.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Delivery> query = manager.createQuery(criteria);
        Optional<Invoice>tmp = getInvoiceBydate(query.getSingleResult().getDeliveryDate().format(DateTimeFormatter.ISO_DATE));
        if (tmp.isPresent()) {
            tmp.get().addDeliveries(query.getSingleResult());
            return true;
        } else {
            Invoice invoice = new Invoice(query.getSingleResult().getaPackage().getSupplier(), query.getSingleResult().getDeliveryDate());
            invoices.add(invoice);
            invoice.addDeliveries(query.getSingleResult());
            return true;
        }
    }

    private Optional<Invoice> getInvoiceBydate(String day) {
        for (Invoice invoice : invoices) {
            if (invoice.getDate().format(DateTimeFormatter.ISO_DATE).equals(day)){
                return Optional.of(invoice);
            }
        }
        return Optional.empty();
    }

    private boolean checkIfInvoiceIdAlreadyExistById(String id) {
        return invoices.stream().anyMatch(invoice -> invoice.getId().equalsIgnoreCase(id));
    }
}
