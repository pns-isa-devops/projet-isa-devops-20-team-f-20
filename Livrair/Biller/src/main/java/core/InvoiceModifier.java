package core;

import entities.Invoice;
import entities.InvoiceStatus;
import exceptions.InvoiceDoesNotExistException;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Local
public interface InvoiceModifier {

    void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException;

    boolean addItem(String id);

    List<Invoice> getInvoices();

    Optional<Invoice> getInvoiceBySupplierName(String name);
}
