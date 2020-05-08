package webservices;

import core.InvoiceModifier;
import entities.Delivery;
import entities.Invoice;
import entities.InvoiceStatus;
import exceptions.InvoiceDoesNotExistException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebService;
import java.util.List;
import java.util.Optional;

@WebService(targetNamespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/invoice")
@Stateless(name = "InvoiceWS")
public class InvoiceWebServiceImpl implements InvoiceWebService {

    @EJB(name = "stateless-invoice")
    private InvoiceModifier invoiceModifier;

    @Override
    public void changeState(Invoice invoice, InvoiceStatus invoiceStatus) throws InvoiceDoesNotExistException {
        invoiceModifier.changeState(invoice, invoiceStatus);
    }

    @Override
    public boolean addItem(Delivery d) {
        return invoiceModifier.addItem(d.getId());
    }

    @Override
    public List<Invoice> getInvoices(){
        return invoiceModifier.getInvoices();
    }

    @Override
    public Optional<List<Invoice>> getInvoiceBySupplierName(String name){
        return invoiceModifier.getInvoiceBySupplierName(name);
    }
}
