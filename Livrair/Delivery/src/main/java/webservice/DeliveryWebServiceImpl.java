package webservice;

import entities.Package;
import core.PackageFinder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(targetNamespace = "http://www.delivrair.fr/backend/delivery")
@Stateless(name="DeliveryWS")
public class DeliveryWebServiceImpl implements DeliveryWebService {

    @EJB(name="stateless-delivery") private PackageFinder finder;


    @Override
    public Package getPackageById(String id) {
        return finder.findById(id).get();
    }
}
