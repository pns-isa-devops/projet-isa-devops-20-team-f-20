package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "deliveries")
public class Delivery implements Serializable {

    private String id;

    private Package aPackage;

    private Drone drone;

    private LocalDateTime liftOffDate;


    private String deliveryDateTS;

    private LocalDateTime previsionalReturnDate;

    private DeliveryStatus status;

    public Delivery(Package aPackage, Drone drone, LocalDateTime deliveryDate) {
        this.aPackage = aPackage;
        this.drone = drone;
        setdeliveryDate(deliveryDate);
        this.status = DeliveryStatus.READY;

    }

    public Delivery() {

    }

    @XmlElement(name = "idDelivery")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(cascade = {CascadeType.PERSIST})
    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST})
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

    @XmlElement(name = "deliveryDate")
    public String getDeliveryDateTS() {
        return deliveryDateTS;
    }

    public void setDeliveryDateTS(String deliveryDateTS) {
        this.deliveryDateTS = deliveryDateTS;
    }

    //@XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getDeliveryDate() {
        return LocalDateTime.ofEpochSecond(Long.valueOf(deliveryDateTS), 0, ZoneOffset.UTC);
        //return deliveryDate;
    }

    public void setdeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDateTS = String.valueOf(deliveryDate.toEpochSecond(ZoneOffset.UTC));
        //this.deliveryDate = deliveryDate;
    }

    public LocalDateTime getPrevisionalReturnDate() {
        return previsionalReturnDate;
    }

    public void setPrevisionalReturnDate(LocalDateTime previsionalReturnDate) {
        this.previsionalReturnDate = previsionalReturnDate;
    }

    @XmlElement(name = "statusDelivery")
    @Enumerated(EnumType.STRING)
    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String s = "Delivery n°" + getId() + " :\n";
        s += "\tSchedule for : " + getDeliveryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n";
        s += "\tAssign to drone : " + getDrone() + "\n";
        s += "\tAssign to package : " + getaPackage().toString() + "\n";
        s += "\t\tSTATUS : " + getStatus().toString() + "\n\n";
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery)) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(getaPackage().getId(), delivery.getaPackage().getId()) &&
                Objects.equals(getDrone(), delivery.getDrone()) &&
                Objects.equals(getDeliveryDateTS(), delivery.getDeliveryDateTS()) &&
                getStatus() == delivery.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getaPackage().getId(), getDrone(), getDeliveryDateTS(), getStatus());
    }

    public void start() {
        getaPackage().setPackageStatus(PackageStatus.WAITING);
        setStatus(DeliveryStatus.SENT);
        getDrone().setStatus(DroneStatus.DELIVERING);
    }

    public void finish() {
        getaPackage().setPackageStatus(PackageStatus.DELIVERED);
        setStatus(DeliveryStatus.DONE);
        getDrone().setStatus(DroneStatus.AVAILABLE);
    }

    public void fail() {
        setStatus(DeliveryStatus.FAILED);
        // TODO more actions
    }
}
