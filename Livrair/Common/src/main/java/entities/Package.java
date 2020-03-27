package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="packages")
public class Package implements Serializable {

    private String id;
    private String customerName;
    private PackageStatus packageStatus;
    private String address;
    private Supplier supplier;


    @Id
    public String getId() {
        return id;
    }

    @NotNull
    public String getCustomerName() {
        return customerName;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public PackageStatus getPackageStatus() {
        return packageStatus;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    //@NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST})
    public Supplier getSupplier() {
        return supplier;
    }


    public Package() {
        // Necessary for JPA instantiation process
    }

    public Package(String id, String customerName, PackageStatus packageStatus, String address, Supplier supplier) {
        this.id = id;
        this.customerName = customerName;
        this.packageStatus = packageStatus;
        this.address = address;
        this.supplier = supplier;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPackageStatus(PackageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        String s = "Package n°" + getId() + " :\n";
        s += "\tReceived from : " + getSupplier().toString() + "\n";
        s += "\tDeliver to : " + getCustomerName() + "\n";
        s += "\tAt " + getAddress() + "\n";
        s += "\t\tSTATUS : " + getPackageStatus().toString() + "\n";
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return getId().equals(aPackage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
