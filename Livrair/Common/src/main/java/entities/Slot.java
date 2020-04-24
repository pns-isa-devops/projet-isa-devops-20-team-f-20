package entities;

import java.io.Serializable;

public class Slot implements Serializable {
    private int start;
    private int finish;

    private Delivery delivery;
    private boolean isAvailable;

    public Slot() {

    }

    public Slot(int start, int finish) {
        this.start = start;
        this.finish = finish;
        this.isAvailable = true;
    }

    public void book(Delivery delivery) throws Exception {
        if( isAvailable == false)
            throw new Exception("Cannot book, already booked");
        this.delivery = delivery;
        this.isAvailable = false;
    }

    public Delivery unbook() throws Exception {
        if( isAvailable == true)
            throw new Exception("Cannot unbook, not booked");
        Delivery d = delivery;
        this.delivery = null;
        this.isAvailable = true;
        return d;
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

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
