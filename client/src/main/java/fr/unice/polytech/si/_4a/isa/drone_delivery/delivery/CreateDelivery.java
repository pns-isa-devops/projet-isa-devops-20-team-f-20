
package fr.unice.polytech.si._4a.isa.drone_delivery.delivery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour createDelivery complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="createDelivery"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="jour" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="mois" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="annee" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="heure" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="minute" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createDelivery", propOrder = {
    "id",
    "jour",
    "mois",
    "annee",
    "heure",
    "minute"
})
public class CreateDelivery {

    protected String id;
    protected int jour;
    protected int mois;
    protected int annee;
    protected int heure;
    protected int minute;

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
     * Obtient la valeur de la propriété jour.
     * 
     */
    public int getJour() {
        return jour;
    }

    /**
     * Définit la valeur de la propriété jour.
     * 
     */
    public void setJour(int value) {
        this.jour = value;
    }

    /**
     * Obtient la valeur de la propriété mois.
     * 
     */
    public int getMois() {
        return mois;
    }

    /**
     * Définit la valeur de la propriété mois.
     * 
     */
    public void setMois(int value) {
        this.mois = value;
    }

    /**
     * Obtient la valeur de la propriété annee.
     * 
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Définit la valeur de la propriété annee.
     * 
     */
    public void setAnnee(int value) {
        this.annee = value;
    }

    /**
     * Obtient la valeur de la propriété heure.
     * 
     */
    public int getHeure() {
        return heure;
    }

    /**
     * Définit la valeur de la propriété heure.
     * 
     */
    public void setHeure(int value) {
        this.heure = value;
    }

    /**
     * Obtient la valeur de la propriété minute.
     * 
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Définit la valeur de la propriété minute.
     * 
     */
    public void setMinute(int value) {
        this.minute = value;
    }

}
