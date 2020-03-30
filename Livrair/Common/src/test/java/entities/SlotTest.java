package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SlotTest {

    private Slot slot;

    @BeforeEach
    void setUp(){
        this.slot = new Slot(10,13);
    }

    @Test
    void book() {
        Delivery d = new Delivery(new Package("5", "Baptiste",
                PackageStatus.REGISTERED, "1 rue de la paix", new Supplier("UPS", "2 rue de la paix")),null, LocalDateTime.now());
        slot.book(d);
        assertFalse(slot.isAvailable);
    }

    @Test
    void unbook() {
        slot.unbook();
        assertTrue(slot.isAvailable);
        assertNull(slot.delivery);
    }

    @Test
    void matchThisHourTrue() {
        int hour = 11;
        assertTrue(slot.matchThisHour(hour));
    }

    @Test
    void matchThisHourFalse() {
        int hour = 17;
        assertFalse(slot.matchThisHour(hour));

    }
}