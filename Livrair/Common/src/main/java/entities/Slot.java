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
    private boolean isAvailable;

    public Slot() {

    }

    public Slot(int start, int finish) {
        this.start = start;
        this.finish = finish;
        this.isAvailable = true;
    }


    public void book() throws Exception {
        if (isAvailable == false)
            throw new Exception("Cannot book, already booked");
        this.isAvailable = false;
    }

    public void book(T t) throws Exception {
        if (isAvailable == false)
            throw new Exception("Cannot book, already booked");
        this.t = t;
        this.isAvailable = false;
    }

    public T unbook() throws Exception {
        if (isAvailable == true)
            throw new Exception("Cannot unbook, not booked");
        T t = this.t;
        this.t = null;
        this.isAvailable = true;
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

    public boolean isAvailable() {
        return isAvailable;
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
                isAvailable() == slot.isAvailable() &&
                Objects.equals(t, slot.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getFinish(), t, isAvailable());
    }
}
