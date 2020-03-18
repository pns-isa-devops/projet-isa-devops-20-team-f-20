
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPackageByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the matchingPackage property.
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
     * Sets the value of the matchingPackage property.
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
