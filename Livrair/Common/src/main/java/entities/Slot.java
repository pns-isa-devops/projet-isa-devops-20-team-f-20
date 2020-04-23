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
}
