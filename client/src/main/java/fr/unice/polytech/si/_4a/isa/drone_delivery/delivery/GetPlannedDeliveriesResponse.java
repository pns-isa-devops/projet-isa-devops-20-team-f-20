
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getPlannedDeliveriesResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getPlannedDeliveriesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="planned_packages" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}delivery" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPlannedDeliveriesResponse", propOrder = {
    "plannedPackages"
})
public class GetPlannedDeliveriesResponse {

    @XmlElement(name = "planned_packages")
    protected List<Delivery> plannedPackages;

    /**
     * Gets the value of the plannedPackages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plannedPackages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlannedPackages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Delivery }
     * 
     * 
     */
    public List<Delivery> getPlannedPackages() {
        if (plannedPackages == null) {
            plannedPackages = new ArrayList<Delivery>();
        }
        return this.plannedPackages;
    }

}
