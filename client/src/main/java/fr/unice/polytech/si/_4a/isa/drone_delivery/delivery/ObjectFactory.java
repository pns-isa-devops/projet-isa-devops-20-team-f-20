
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

    private final static QName _CreateDelivery_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "createDelivery");
    private final static QName _CreateDeliveryResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "createDeliveryResponse");
    private final static QName _GetAllPackages_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getAllPackages");
    private final static QName _GetAllPackagesResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getAllPackagesResponse");
    private final static QName _GetPackageById_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPackageById");
    private final static QName _GetPackageByIdResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPackageByIdResponse");
    private final static QName _GetPlannedDeliveries_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPlannedDeliveries");
    private final static QName _GetPlannedDeliveriesResponse_QNAME = new QName("http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", "getPlannedDeliveriesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.polytech.si._4a.isa.drone_delivery.delivery
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateDelivery }
     * 
     */
    public CreateDelivery createCreateDelivery() {
        return new CreateDelivery();
    }

    /**
     * Create an instance of {@link CreateDeliveryResponse }
     * 
     */
    public CreateDeliveryResponse createCreateDeliveryResponse() {
        return new CreateDeliveryResponse();
    }

    /**
     * Create an instance of {@link GetAllPackages }
     * 
     */
    public GetAllPackages createGetAllPackages() {
        return new GetAllPackages();
    }

    /**
     * Create an instance of {@link GetAllPackagesResponse }
     * 
     */
    public GetAllPackagesResponse createGetAllPackagesResponse() {
        return new GetAllPackagesResponse();
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
     * Create an instance of {@link GetPlannedDeliveries }
     * 
     */
    public GetPlannedDeliveries createGetPlannedDeliveries() {
        return new GetPlannedDeliveries();
    }

    /**
     * Create an instance of {@link GetPlannedDeliveriesResponse }
     * 
     */
    public GetPlannedDeliveriesResponse createGetPlannedDeliveriesResponse() {
        return new GetPlannedDeliveriesResponse();
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
     * Create an instance of {@link Delivery }
     * 
     */
    public Delivery createDelivery() {
        return new Delivery();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link Drone }
     * 
     */
    public Drone createDrone() {
        return new Drone();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDelivery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "createDelivery")
    public JAXBElement<CreateDelivery> createCreateDelivery(CreateDelivery value) {
        return new JAXBElement<CreateDelivery>(_CreateDelivery_QNAME, CreateDelivery.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDeliveryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "createDeliveryResponse")
    public JAXBElement<CreateDeliveryResponse> createCreateDeliveryResponse(CreateDeliveryResponse value) {
        return new JAXBElement<CreateDeliveryResponse>(_CreateDeliveryResponse_QNAME, CreateDeliveryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPackages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getAllPackages")
    public JAXBElement<GetAllPackages> createGetAllPackages(GetAllPackages value) {
        return new JAXBElement<GetAllPackages>(_GetAllPackages_QNAME, GetAllPackages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPackagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getAllPackagesResponse")
    public JAXBElement<GetAllPackagesResponse> createGetAllPackagesResponse(GetAllPackagesResponse value) {
        return new JAXBElement<GetAllPackagesResponse>(_GetAllPackagesResponse_QNAME, GetAllPackagesResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlannedDeliveries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getPlannedDeliveries")
    public JAXBElement<GetPlannedDeliveries> createGetPlannedDeliveries(GetPlannedDeliveries value) {
        return new JAXBElement<GetPlannedDeliveries>(_GetPlannedDeliveries_QNAME, GetPlannedDeliveries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPlannedDeliveriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery", name = "getPlannedDeliveriesResponse")
    public JAXBElement<GetPlannedDeliveriesResponse> createGetPlannedDeliveriesResponse(GetPlannedDeliveriesResponse value) {
        return new JAXBElement<GetPlannedDeliveriesResponse>(_GetPlannedDeliveriesResponse_QNAME, GetPlannedDeliveriesResponse.class, null, value);
    }

}
