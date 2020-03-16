package entities;

public class Slot {
    public int start;
    public int finish;

    public Delivery delivery;
    public boolean isAvailable;

    public Slot(int start, int finish) {
        this.start = start;
        this.finish = finish;
        this.isAvailable = true;
    }

    public void book(Delivery delivery) {
        // TODO Exception
        this.delivery = delivery;
        this.isAvailable = false;
    }

    public Delivery unbook() {
        // TODO Exception
        Delivery d = delivery;
        this.delivery = null;
        this.isAvailable = true;
        return d;
    }
}
