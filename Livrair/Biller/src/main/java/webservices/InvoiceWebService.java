package webservices;

import entities.Delivery;
import entities.Invoice;
import entities.InvoiceStatus;
import exceptions.InvoiceDoesNotExistException;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/invoice")
public interface InvoiceWebService {

    @WebMethod
    @WebResult( name = "change_invoice_state")
    public void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException;

    @WebMethod
    @WebResult( name = "add_item")
    public boolean addItem(String id);


}