package webservices;

import interfaces.Availability;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/logistic")
@Stateless(name = "LogisticWS")

public class LogisticWebServiceImpl implements LogisticWebService {

//    @EJB(name="stateless-cart") private CartModifier cart;
//    @EJB(name="stateless-cart") private CartProcessor processor;
    @EJB(name="stateless-logistic") private Availability availability;
//    @EJB private CustomerFinder finder;


//    @Override
//    public void removeItemToCustomerCart(String customerName, Item it)
//            throws UnknownCustomerException {
//        cart.remove(readCustomer(customerName), it);
//    }
//
//    @Override
//    public Set<Item> getCustomerCartContents(String customerName)
//            throws UnknownCustomerException {
//        return processor.contents(readCustomer(customerName));
//    }

    @Override
    public String hello(String hello_param) {
        return availability.hello(hello_param);
    }
}
