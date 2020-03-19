
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour drone complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="drone"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="chargelevel" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="flyingTime" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}droneStatus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drone", propOrder = {
    "chargelevel",
    "flyingTime",
    "id",
    "status"
})
public class Drone {

    protected double chargelevel;
    protected double flyingTime;
    protected String id;
    @XmlSchemaType(name = "string")
    protected DroneStatus status;

    /**
     * Obtient la valeur de la propriété chargelevel.
     * 
     */
    public double getChargelevel() {
        return chargelevel;
    }

    /**
     * Définit la valeur de la propriété chargelevel.
     * 
     */
    public void setChargelevel(double value) {
        this.chargelevel = value;
    }

    /**
     * Obtient la valeur de la propriété flyingTime.
     * 
     */
    public double getFlyingTime() {
        return flyingTime;
    }

    /**
     * Définit la valeur de la propriété flyingTime.
     * 
     */
    public void setFlyingTime(double value) {
        this.flyingTime = value;
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
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link DroneStatus }
     *     
     */
    public DroneStatus getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link DroneStatus }
     *     
     */
    public void setStatus(DroneStatus value) {
        this.status = value;
    }

}
