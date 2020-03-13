package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {

    @Id
    private String name;

    private String address;

    public Supplier(String name, String address){
        this.name = name;
        this.address = address;
    }

}
