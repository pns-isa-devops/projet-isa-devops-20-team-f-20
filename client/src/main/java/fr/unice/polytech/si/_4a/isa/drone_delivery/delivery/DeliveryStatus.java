
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour deliveryStatus.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="deliveryStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="READY"/&gt;
 *     &lt;enumeration value="SENT"/&gt;
 *     &lt;enumeration value="DONE"/&gt;
 *     &lt;enumeration value="FAILED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "deliveryStatus")
@XmlEnum
public enum DeliveryStatus {

    READY,
    SENT,
    DONE,
    FAILED;

    public String value() {
        return name();
    }

    public static DeliveryStatus fromValue(String v) {
        return valueOf(v);
    }

}
