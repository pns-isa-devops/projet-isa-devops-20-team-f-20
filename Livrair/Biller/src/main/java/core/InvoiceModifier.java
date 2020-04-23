package core;

import entities.*;
import entities.Package;
import exceptions.InvoiceDoesNotExistException;

import javax.ejb.Local;

@Local
public interface InvoiceModifier {

    void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException;

    boolean addItem(String id);
}
