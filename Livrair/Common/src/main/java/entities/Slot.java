package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="slots")
public class Slot<T> implements Serializable {
    private int start;
    private int finish;

    private String id;

    private T t;
    private boolean available;

    public Slot() {

    }

    public Slot(int start, int finish) {
        this.start = start;
        this.finish = finish;
        this.available = true;
    }


    public void book() throws Exception {
        if (available == false)
            throw new Exception("Cannot book, already booked");
        this.available = false;
    }

    public void book(T t) throws Exception {
        if (available == false)
            throw new Exception("Cannot book, already booked");
        this.t = t;
        this.available = false;
    }

    public T unbook() throws Exception {
        if (available == true)
            throw new Exception("Cannot unbook, not booked");
        T t = this.t;
        this.t = null;
        this.available = true;
        return t;
    }

    public boolean matchThisHour(int hour) {
        return start <= hour && hour < finish;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @OneToOne
    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    public boolean hasBookingT() {
        return t != null;
    }

    @Column(columnDefinition="tinyint(1) default 1")
    public boolean getAvailable() {
        return available;
    }

    @XmlElement(name = "idSlot")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slot)) return false;
        Slot<?> slot = (Slot<?>) o;
        return getStart() == slot.getStart() &&
                getFinish() == slot.getFinish() &&
                getAvailable() == slot.getAvailable() &&
                Objects.equals(t, slot.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getFinish(), t, getAvailable());
    }

    @Override
    public String toString() {
        return "STATUS DU SLOT" + getAvailable();
    }
}
