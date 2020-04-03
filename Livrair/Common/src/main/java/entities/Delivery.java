package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Entity
public class Delivery implements Serializable {
    @Id
    private String id;

    private Package aPackage;

    private Drone drone;

    private LocalDateTime liftOffDate;

    private LocalDateTime deliveryDate;

    private LocalDateTime previsionalReturnDate;

    private DeliveryStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public LocalDateTime getLiftOffDate() {
        return liftOffDate;
    }

    public void setLiftOffDate(LocalDateTime liftOffDate) {
        this.liftOffDate = liftOffDate;
    }

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setdeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getPrevisionalReturnDate() {
        return previsionalReturnDate;
    }

    public void setPrevisionalReturnDate(LocalDateTime previsionalReturnDate) {
        this.previsionalReturnDate = previsionalReturnDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public Delivery(Package aPackage, Drone drone, LocalDateTime deliveryDate) {
        this.aPackage = aPackage;
        this.drone = drone;
        this.deliveryDate = deliveryDate;
        this.status = DeliveryStatus.READY;

        // TODO
    }

    public Delivery(){

    }

    @Override
    public String toString() {
        String s = "Delivery n°" + getId() + " :\n";
        s += "\tSchedule for : " + getDeliveryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n";
        s += "\tAssign to drone : " + getDrone() + "\n";
        s += "\tAssign to package : " + getaPackage().toString() + "\n";
        s += "\t\tSTATUS : " + getStatus().toString()+ "\n\n";
        return s;
    }
}

class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    public String marshal(LocalDateTime v) throws Exception {
        return String.valueOf(v.toEpochSecond(ZoneOffset.UTC));
    }
}