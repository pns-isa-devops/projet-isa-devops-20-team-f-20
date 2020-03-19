package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Supplier implements Serializable {

    @Id
    private String name;

    private String address;

    public Supplier(){};

    public Supplier(String name, String address){
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name+" "+address;
    }
}
