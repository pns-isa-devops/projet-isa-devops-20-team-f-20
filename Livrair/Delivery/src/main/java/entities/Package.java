package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Package implements Serializable {

    @Id
    private String id;

    @NotNull
    private String customerName;

    @NotNull
    private PackageStatus packageStatus;

    @NotNull
    private String address;

    @NotNull
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
}
