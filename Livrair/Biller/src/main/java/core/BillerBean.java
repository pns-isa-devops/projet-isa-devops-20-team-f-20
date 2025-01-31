package core;

import entities.Delivery;
import entities.Invoice;
import entities.InvoiceStatus;
import entities.Supplier;
import exceptions.InvoiceDoesNotExistException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Stateless
public class BillerBean implements InvoiceModifier {

    @PersistenceContext
    private EntityManager manager;

    SimpleDateFormat yearMonthDayPattern = new SimpleDateFormat("yyyy/MM/dd");

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
        Root<Delivery> root = criteria.from(Delivery.class);
        criteria.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Delivery> query = manager.createQuery(criteria);
        LocalDateTime tmpDate = query.getSingleResult().getDeliveryDate();
        Date now = Date.from( tmpDate.atZone(ZoneId.systemDefault()).toInstant());
        Supplier sup = query.getSingleResult().getaPackage().getSupplier();
        Optional<Invoice>tmp = getInvoiceBydateAndsup(now,sup);
        if (tmp.isPresent()) {
            tmp.get().addDeliveries(query.getSingleResult());
            return true;
        } else {
            Supplier tmpSupplier = query.getSingleResult().getaPackage().getSupplier();
            Invoice invoice = new Invoice(tmpSupplier, now);
            this.manager.persist(invoice);
            invoice.addDeliveries(query.getSingleResult());
            return true;
        }
    }

    @Override
    public List<Invoice> getInvoices(){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteria = builder.createQuery(Invoice.class);
        Root<Invoice> root =  criteria.from(Invoice.class);
        criteria.select(root);
        TypedQuery<Invoice> query = manager.createQuery(criteria);
        return query.getResultList();
    }

    private Optional<Invoice> getInvoiceBydateAndsup(Date day, Supplier sup) {
        for (Invoice invoice : getInvoices()) {
            if (yearMonthDayPattern.format(invoice.getDate()).equals(yearMonthDayPattern.format(day)) && invoice.getSupplier().equals(sup)){
                return Optional.of(invoice);
            }
        }
        return Optional.empty();
    }

    private boolean checkIfInvoiceIdAlreadyExistById(String id) {
        return getInvoices().stream().anyMatch(invoice -> invoice.getId().equalsIgnoreCase(id));
    }

    public Optional<List<Invoice>> getInvoiceBySupplierName(String name){
        List<Invoice> invoiceList = getInvoices();
        List<Invoice> supplierInvoicesList = new ArrayList<>();
        for (Invoice i : invoiceList) {
            if (i.getSupplier().getName() == name){
                supplierInvoicesList.add(i);
            }
        }
        return Optional.of(supplierInvoicesList);

    }
}
