package entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {

    private Slot slot;

    @Before
    public void setUp() {
        this.slot = new Slot(10, 13, null);
    }

    @Test
    public void book() {
        Delivery d = new Delivery(new Package("5", "Baptiste",
                PackageStatus.REGISTERED, "1 rue de la paix", new Supplier("UPS", "2 rue de la paix")), null, LocalDateTime.now());
        try {
            slot.book(d);
        } catch (Exception e) {
            assert (false);
        }
        assertFalse(slot.getIsAvailable());
    }

    @Test
    public void unbook() {
        book();
        try {
            slot.unbook();
        } catch (Exception e) {
            assert (false);
        }
        assertTrue(slot.getIsAvailable());
        assertNull(slot.get());
    }

    @Test
    public void matchThisHourTrue() {
        int hour = 11;
        assertTrue(slot.matchThisHour(hour));
    }

    @Test
    public void matchThisHourFalse() {
        int hour = 17;
        assertFalse(slot.matchThisHour(hour));

    }
}
