package entities;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyPlanningTest {

    private DailyPlanning dailyPlanning;

    @Before
    public void setUp() {
        try {
            dailyPlanning = new DailyPlanning(new HashMap());
        } catch (Exception e) {
            assert (false);
        }
    }

    @Test
    public void availableSlotForGivenDate() {
        assertTrue(dailyPlanning.availableSlotForGivenDate(9));
        Delivery d1 = new Delivery(new Package("5", "Baptiste",
                "1 rue de la paix",
                new Supplier("UPS", "2 rue de la paix")), null, LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)));

        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(d1);

        DailyPlanning next = null;
        try {
            next = new DailyPlanning(DailyPlanning.fromDeliveries(deliveries));
        } catch (Exception e) {
            assert (false);
        }
        assertFalse(next.availableSlotForGivenDate(9));

    }


}
