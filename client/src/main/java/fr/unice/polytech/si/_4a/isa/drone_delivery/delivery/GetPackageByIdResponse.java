
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getPackageByIdResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getPackageByIdResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="matching_package" type="{http://www.polytech.unice.fr/si/4a/isa/drone-delivery/delivery}package" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPackageByIdResponse", propOrder = {
    "matchingPackage"
})
public class GetPackageByIdResponse {

    @XmlElement(name = "matching_package")
    protected Package matchingPackage;

    /**
     * Obtient la valeur de la propriété matchingPackage.
     * 
     * @return
     *     possible object is
     *     {@link Package }
     *     
     */
    public Package getMatchingPackage() {
        return matchingPackage;
    }

    /**
     * Définit la valeur de la propriété matchingPackage.
     * 
     * @param value
     *     allowed object is
     *     {@link Package }
     *     
     */
    public void setMatchingPackage(Package value) {
        this.matchingPackage = value;
    }

}
