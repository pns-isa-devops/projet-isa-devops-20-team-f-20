
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.polytech.si._4a.isa.drone_delivery.delivery package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPackageById_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPackageById");
    private final static QName _GetPackageByIdResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPackageByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.si._4a.isa.drone_delivery.delivery
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPackageById }
     * 
     */
    public GetPackageById createGetPackageById() {
        return new GetPackageById();
    }

    /**
     * Create an instance of {@link GetPackageByIdResponse }
     * 
     */
    public GetPackageByIdResponse createGetPackageByIdResponse() {
        return new GetPackageByIdResponse();
    }

    /**
     * Create an instance of {@link Package }
     * 
     */
    public Package createPackage() {
        return new Package();
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPackageById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getPackageById")
    public JAXBElement<GetPackageById> createGetPackageById(GetPackageById value) {
        return new JAXBElement<GetPackageById>(_GetPackageById_QNAME, GetPackageById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPackageByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getPackageByIdResponse")
    public JAXBElement<GetPackageByIdResponse> createGetPackageByIdResponse(GetPackageByIdResponse value) {
        return new JAXBElement<GetPackageByIdResponse>(_GetPackageByIdResponse_QNAME, GetPackageByIdResponse.class, null, value);
    }

}
