
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour createDeliveryResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="createDeliveryResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="is_planned" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createDeliveryResponse", propOrder = {
    "isPlanned"
})
public class CreateDeliveryResponse {

    @XmlElement(name = "is_planned")
    protected boolean isPlanned;

    /**
     * Obtient la valeur de la propriété isPlanned.
     * 
     */
    public boolean isIsPlanned() {
        return isPlanned;
    }

    /**
     * Définit la valeur de la propriété isPlanned.
     * 
     */
    public void setIsPlanned(boolean value) {
        this.isPlanned = value;
    }

}
