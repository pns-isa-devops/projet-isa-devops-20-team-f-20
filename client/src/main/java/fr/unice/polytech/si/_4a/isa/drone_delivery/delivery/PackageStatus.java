
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for packageStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="packageStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="REGISTERED"/&gt;
 *     &lt;enumeration value="WAITING"/&gt;
 *     &lt;enumeration value="ASSIGNED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "packageStatus")
@XmlEnum
public enum PackageStatus {

    REGISTERED,
    WAITING,
    ASSIGNED;

    public String value() {
        return name();
    }

    public static PackageStatus fromValue(String v) {
        return valueOf(v);
    }

}
