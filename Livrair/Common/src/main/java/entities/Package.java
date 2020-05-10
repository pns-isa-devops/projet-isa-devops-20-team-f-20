package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "packages")
public class Package implements Serializable {

    private String id;
    private String customerName;
    private PackageStatus packageStatus;
    private String address;
    private Supplier supplier;

    public Package() {
        // Necessary for JPA instantiation process
    }

    public Package(String id, String customerName, String address, Supplier supplier) {
        this.id = id;
        this.customerName = customerName;
        this.packageStatus = PackageStatus.REGISTERED;
        this.address = address;
        this.supplier = supplier;
    }

    @XmlElement(name = "idPackage")
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @NotNull
    @XmlElement(name = "statusPackage")
    @Enumerated(EnumType.STRING)
    public PackageStatus getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(PackageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //@NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    public Supplier getSupplier() {
        return supplier;
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
