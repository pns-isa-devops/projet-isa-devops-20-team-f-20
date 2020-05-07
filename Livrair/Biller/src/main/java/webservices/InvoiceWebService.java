package webservices;

import entities.Delivery;
import entities.Invoice;
import entities.InvoiceStatus;
import exceptions.InvoiceDoesNotExistException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/invoice")
public interface InvoiceWebService {

    @WebMethod
    @WebResult(name = "change_invoice_state")
    void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException;

    @WebMethod
    @WebResult(name = "add_item")
    public boolean addItem(Delivery d);

    @WebMethod
    @WebResult( name = "get_invoices")
    public List<Invoice> getInvoices();

    @WebMethod
    @WebResult(name = "matching_invoice")
    public Invoice getInvoiceBySupplierName(@WebParam(name = "name") String name);
}
