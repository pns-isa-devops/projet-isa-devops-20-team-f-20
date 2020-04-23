package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice {

    @Id
    private String id;

    @NotNull
    private Supplier supplier;

    private List<Delivery> deliveries;

    private double price;

    private LocalDateTime date;

    private InvoiceStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public Invoice(Supplier supplier, LocalDateTime date) {
        this.supplier = supplier;
        this.date = date;
        this.deliveries = new ArrayList<>();
        this.status = InvoiceStatus.DRAFT;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }
}
