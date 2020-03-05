package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Supplier {

    @Id
    private String name;

    private String address;

}
