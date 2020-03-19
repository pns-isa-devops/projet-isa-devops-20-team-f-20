package entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class Package implements Serializable {

    @Id
    private String id;

    private String customerName;

    private PackageStatus packageStatus;

    private String address;

    private Supplier supplier;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public PackageStatus getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(PackageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
}
