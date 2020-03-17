package webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/logistic")
public interface LogisticWebService {

//
//    @WebMethod
//    @Interceptors({ItemVerifier.class})
//    void removeItemToCustomerCart(@WebParam(name = "customer_name") String customerName,
//                                  @WebParam(name = "item") Item it)
//            throws UnknownCustomerException;
//
//    @WebMethod
//    @WebResult(name = "cart_contents")
//    Set<Item> getCustomerCartContents(@WebParam(name = "customer_name") String customerName)
//            throws UnknownCustomerException;

    @WebMethod
    @WebResult(name = "hello_return")
    String hello(@WebParam(name = "hello_param") String hello_param);
}
