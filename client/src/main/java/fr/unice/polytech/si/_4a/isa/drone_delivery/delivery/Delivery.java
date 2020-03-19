
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour delivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="delivery"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="deliveryDate" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="drone" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}drone" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="liftOffDate" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="previsionalReturnDate" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}localDateTime" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}deliveryStatus" minOccurs="0"/&gt;
 *         &lt;element name="aPackage" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}package" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "delivery", propOrder = {
    "deliveryDate",
    "drone",
    "id",
    "liftOffDate",
    "previsionalReturnDate",
    "status",
    "aPackage"
})
public class Delivery {

    protected LocalDateTime deliveryDate;
    protected Drone drone;
    protected String id;
    protected LocalDateTime liftOffDate;
    protected LocalDateTime previsionalReturnDate;
    @XmlSchemaType(name = "string")
    protected DeliveryStatus status;
    protected Package aPackage;

    /**
     * Obtient la valeur de la propriété deliveryDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Définit la valeur de la propriété deliveryDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setDeliveryDate(LocalDateTime value) {
        this.deliveryDate = value;
    }

    /**
     * Obtient la valeur de la propriété drone.
     * 
     * @return
     *     possible object is
     *     {@link Drone }
     *     
     */
    public Drone getDrone() {
        return drone;
    }

    /**
     * Définit la valeur de la propriété drone.
     * 
     * @param value
     *     allowed object is
     *     {@link Drone }
     *     
     */
    public void setDrone(Drone value) {
        this.drone = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété liftOffDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getLiftOffDate() {
        return liftOffDate;
    }

    /**
     * Définit la valeur de la propriété liftOffDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setLiftOffDate(LocalDateTime value) {
        this.liftOffDate = value;
    }

    /**
     * Obtient la valeur de la propriété previsionalReturnDate.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getPrevisionalReturnDate() {
        return previsionalReturnDate;
    }

    /**
     * Définit la valeur de la propriété previsionalReturnDate.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setPrevisionalReturnDate(LocalDateTime value) {
        this.previsionalReturnDate = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryStatus }
     *     
     */
    public DeliveryStatus getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryStatus }
     *     
     */
    public void setStatus(DeliveryStatus value) {
        this.status = value;
    }

    /**
     * Obtient la valeur de la propriété aPackage.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getAPackage() {
        return aPackage;
    }

    /**
     * Définit la valeur de la propriété aPackage.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setAPackage(Package value) {
        this.aPackage = value;
    }

}
