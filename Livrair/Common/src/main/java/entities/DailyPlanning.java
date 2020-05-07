package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "dailyplannings")
public class DailyPlanning<T> implements Serializable {

    private String id;

    private List<Slot> slots;

    private String planningDateTS;

    public DailyPlanning() {

    }


    public DailyPlanning(Set<Drone> flotte) {
        setDate(LocalDate.now());
        initSlots(flotte);
    }

    public DailyPlanning(Set<Drone> flotte, LocalDate date)  {
        setDate(date);
        initSlots(flotte);
    }


    public static HashMap<Delivery, Integer> fromDeliveries(List<Delivery> deliveries) {
        HashMap<Delivery, Integer> hashMap = new HashMap<>();

        for (Delivery d : deliveries)
            hashMap.put(d, d.getDeliveryDate().getHour());

        return hashMap;
    }

    /**
     * List all slot free to be booked for delivery
     *
     * @return The list of all slots available
     */
    public List<Slot> getAvailabilities() {
        return slots.stream().filter((slot) -> slot.getIsAvailable()).collect(Collectors.toList());
    }

    /**
     * Return true if the slot is available for the given date
     *
     * @param date Hour
     * @return true if the slot is available for the given date, false otherwise
     */

    public Optional<Slot> availableSlotForGivenDate(int date) {
        for (Slot s : getAvailabilities())
            if (s.matchThisHour(date))
                return Optional.of(s);

        return Optional.empty();
    }

    /**
     * Get the closest slot available given the date
     *
     * @param date Hour
     * @return The closest slot available given the date
     */
    public Slot getBestMatchingSlotOf(int date) {

        return getAvailabilities().get(0);
    } // TODO Improve Matching : bonus?


    /**
     * Initialize default slot of one day
     */
    private void initSlots(Set<Drone> flotte) {
        slots = new ArrayList<>();

        for(Drone d : flotte){
            slots.add(new Slot(8, 11, d));
            slots.add(new Slot(11, 14, d));
            slots.add(new Slot(14, 17, d));
            slots.add(new Slot(17, 20, d));
        }
    }

    public void book(T t, LocalDateTime date) throws Exception {
        if (!availableSlotForGivenDate(date.getHour()).isPresent())
            throw new Exception("Cannot book"); // TODO custom exception

        for (Slot s :  getAvailabilities())
            if (s.matchThisHour(date.getHour()))
                s.book(t);

    }


    @XmlElementWrapper(name = "slots")
    @XmlElement(name = "slot")
    @OneToMany(cascade = {CascadeType.ALL})
    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public LocalDate getDate() {
        return LocalDate.ofEpochDay(Long.valueOf(planningDateTS));
    }

    public void setDate(LocalDate date) {
        this.planningDateTS = String.valueOf(date.toEpochDay());
    }

    @Id
    @XmlElement(name = "planningDate")
    public String getPlanningDateTS() {
        return planningDateTS;
    }

    public void setPlanningDateTS(String planningDateTS) {
        this.planningDateTS = planningDateTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyPlanning)) return false;
        DailyPlanning<?> that = (DailyPlanning<?>) o;
        return Objects.equals(getSlots(), that.getSlots()) &&
                Objects.equals(getPlanningDateTS(), that.getPlanningDateTS());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlots().hashCode(), getPlanningDateTS());
    }

}
