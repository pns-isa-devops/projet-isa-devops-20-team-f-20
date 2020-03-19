package entities;

public class Slot {
    public Delivery delivery;
    private int start;
    private int finish;
    private boolean isAvailable;

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

    public boolean isAvailable() {
        return isAvailable;
    }

    public Delivery unbook() {
        // TODO Exception
        Delivery d = delivery;
        this.delivery = null;
        this.isAvailable = true;
        return d;
    }

    public boolean matchThisHour(int hour) {
        return start <= hour && hour < finish;
    }
}
