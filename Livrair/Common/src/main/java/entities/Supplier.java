package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

    private String name;
    private String address;
    private Set<Package> packages;

    public Supplier() {
    }


    public Supplier(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Id
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //@NotNull
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "supplier")
    public Set<Package> getPackages() {
        return packages;
    }

    public void setPackages(Set<Package> packages) {
        this.packages = packages;
    }

    @Override
    public String toString() {
        return getName() + " " + getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return getName().equals(supplier.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
