package entities;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DailyPlanningTest {

    private DailyPlanning dailyPlanning;

    @Before
    public void setUp(){
        try {
            dailyPlanning = new DailyPlanning(new ArrayList<>());
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void availableSlotForGivenDate() {
        assertTrue(dailyPlanning.availableSlotForGivenDate(9));
        Delivery d = new Delivery(new Package("5", "Baptiste",
                PackageStatus.REGISTERED, "1 rue de la paix",
                new Supplier("UPS", "2 rue de la paix")),null, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,0)));
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(d);
        DailyPlanning next = null;
        try {
            next = new DailyPlanning(deliveries);
        } catch (Exception e) {
            assert(false);
        }
        assertFalse(next.availableSlotForGivenDate(9));

    }


}
