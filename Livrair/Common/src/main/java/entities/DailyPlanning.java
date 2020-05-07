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

    private String date;

    public DailyPlanning() {
    }


    public DailyPlanning(HashMap<T, Integer> hashT) throws Exception {
        setDate(LocalDate.now());
        initSlots();
        build(hashT);
    }

    public DailyPlanning(LocalDate date) {
        setDate(date);
        initSlots();
    }

    public DailyPlanning(HashMap<T, Integer> hashT, LocalDate date) throws Exception {
        setDate(date);
        initSlots();
        build(hashT);
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
        return slots.stream().filter((slot) -> slot.isAvailable()).collect(Collectors.toList());
    }

    /**
     * Return true if the slot is available for the given date
     *
     * @param date Hour
     * @return true if the slot is available for the given date, false otherwise
     */
    public boolean availableSlotForGivenDate(int date) {
        for (Slot s : getAvailabilities())
            if (s.matchThisHour(date))
                return true;

        return false;
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
     * Build the planning slots with the given deliveries
     *
     * @param hashT Hashtable Hour <-> T
     */
    private void build(HashMap<T, Integer> hashT) throws Exception {
        if (getSlots() == null || getSlots().isEmpty())
            throw new Exception("Slots null or empty");

        int booked = 0;

        for (Map.Entry<T, Integer> entry : hashT.entrySet()) {
            for (Slot s : slots) {
                if (s.isAvailable() && s.matchThisHour(entry.getValue())) {
                    s.book(entry.getKey());
                    booked++;
                }
            }
        }

        if (booked < hashT.size())
            throw new Exception("Error : " + (hashT.size() - booked) + "/" + hashT.size() + " object(s) not booked");
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

    public void book(T t, LocalDateTime date) throws Exception {
        if (!availableSlotForGivenDate(date.getHour()))
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

    @XmlElement(name = "dailyPlanningDate")
    public LocalDate getDate() {
        return LocalDate.ofEpochDay(Long.valueOf(date));
    }

    public void setDate(LocalDate date) {
        this.date = String.valueOf(date.toEpochDay());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyPlanning<?> planning = (DailyPlanning<?>) o;
        return Objects.equals(getSlots(), planning.getSlots()) &&
                Objects.equals(getDate(), planning.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSlots().hashCode(), getDate());
    }

    @XmlElement(name = "idDailyPlanning")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
