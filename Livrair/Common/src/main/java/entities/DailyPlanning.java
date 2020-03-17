package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DailyPlanning {

    private List<Delivery> deliveries;
    public List<Slot> slots;


    public DailyPlanning(List<Delivery> deliveries) {
        build(deliveries);
        this.deliveries = deliveries;
    }

    /**
     * Build the planning slots with the given deliveries
     *
     * @param deliveries Deliveries of the day
     */
    private void build(List<Delivery> deliveries) {
        initSlots();
        for (Delivery d : deliveries) {
            int hour = d.getDeliveryDate().getHour();
            for (Slot s : slots) {
                if (s.isAvailable && s.matchThisHour(hour))
                    s.book(d);
            }
        }
    }

    /**
     * Initialize default slot of one day
     */
    private void initSlots() {
        slots = new ArrayList<>();

        slots.add(new Slot(8, 11));
        slots.add(new Slot(11, 14));
        slots.add(new Slot(14, 17));
        slots.add(new Slot(17, 20));
    }

    /**
     * List all slot free to be booked for delivery
     *
     * @return The list of all slots available
     */
    public List<Slot> getAvailabilities() {
        return slots.stream().filter((slot) -> slot.isAvailable).collect(Collectors.toList());
    }

    public boolean availableSlotForGivenDate(int deliveryDate) {
        for (Slot s :
                getAvailabilities()) {
            if (s.matchThisHour(deliveryDate))
                return true;
        }
        return false;
    }

    /**
     * Get the closest slot available given the deliveryDate
     *
     * @param deliveryDate Hour of the wished delivery
     * @return The closest slot available given the deliveryDate
     */
    public Slot getBestMatchingSlotOf(int deliveryDate) {
        return null;
    } // TODO bonus?
}
