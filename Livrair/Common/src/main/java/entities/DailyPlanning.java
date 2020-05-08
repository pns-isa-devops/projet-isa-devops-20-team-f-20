package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "dailyplannings")
public class DailyPlanning<T> implements Serializable {


    private List<Slot> slots;

    private String planningDateTS;

    public DailyPlanning() {

    }



    public DailyPlanning(List<Slot> slots, LocalDateTime date)  {
        setplanningDate(date);
        this.slots = slots;
    }



    /**
     * List all slot free to be booked for delivery
     *
     * @return The list of all slots available
     */
    public List<Slot> getAvailabilities() {
        return slots.stream().filter((slot) -> slot.getisAvailable()).collect(Collectors.toList());
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




    public void book(T t, LocalDateTime date) throws Exception {
        if (!availableSlotForGivenDate(date.getHour()).isPresent())
            throw new Exception("Cannot book"); // TODO custom exception

        for (Slot s :  getAvailabilities())
            if (s.matchThisHour(date.getHour()))
                s.book(t);

    }


    @XmlElementWrapper(name = "slots")
    @XmlElement(name = "slot")
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @XmlElement(name = "planningDate")
    public String getPlanningDateTS() {
        return planningDateTS;
    }

    public void setPlanningDateTS(String deliveryDateTS) {
        this.planningDateTS = deliveryDateTS;
    }

    public LocalDateTime getPlanningDate() {
        return LocalDateTime.ofEpochSecond(Long.valueOf(planningDateTS), 0, ZoneOffset.UTC);
    }

    public void setplanningDate(LocalDateTime deliveryDate) {
        this.planningDateTS = String.valueOf(deliveryDate.toEpochSecond(ZoneOffset.UTC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyPlanning)) return false;
        DailyPlanning<?> that = (DailyPlanning<?>) o;
        return getPlanningDateTS().equals(((DailyPlanning<?>) o).getPlanningDateTS());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlanningDateTS());
    }

}
