package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "invoices")
public class Invoice {

    private String id;

    private Supplier supplier;

    private List<Delivery> deliveries;

    private double price;

    private Date date;

    private InvoiceStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST})
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @OneToMany(cascade = {CascadeType.PERSIST})
    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void addDeliveries(Delivery delivery){
        this.deliveries.add(delivery);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @XmlElement(name="invoiceDate")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Enumerated(EnumType.STRING)
    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Invoice(){
    }

    public Invoice(Supplier supplier, Date date) {
        this.supplier = supplier;
        this.date = date;
        this.deliveries = new ArrayList<>();
        this.status = InvoiceStatus.DRAFT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return  Objects.equals(getSupplier(), invoice.getSupplier()) &&
                Objects.equals(getDate(), invoice.getDate()) &&
                getStatus() == invoice.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSupplier().hashCode(), getDate(), getStatus());
    }
}

