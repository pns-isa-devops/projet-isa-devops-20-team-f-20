
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour droneStatus.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="droneStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="AVAILABLE"/&gt;
 *     &lt;enumeration value="DELIVERING"/&gt;
 *     &lt;enumeration value="CHARGING"/&gt;
 *     &lt;enumeration value="REVIEW"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "droneStatus")
@XmlEnum
public enum DroneStatus {

    AVAILABLE,
    DELIVERING,
    CHARGING,
    REVIEW;

    public String value() {
        return name();
    }

    public static DroneStatus fromValue(String v) {
        return valueOf(v);
    }

}
