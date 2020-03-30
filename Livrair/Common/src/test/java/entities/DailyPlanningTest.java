package entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DailyPlanningTest {

    private DailyPlanning dailyPlanning;

    @BeforeEach
    void setUp(){
        dailyPlanning = new DailyPlanning(new ArrayList<>());
    }

    @Test
    void availableSlotForGivenDate() {
        assertTrue(dailyPlanning.availableSlotForGivenDate(9));
        Delivery d = new Delivery(new Package("5", "Baptiste",
                PackageStatus.REGISTERED, "1 rue de la paix",
                new Supplier("UPS", "2 rue de la paix")),null, LocalDateTime.of(LocalDate.now(), LocalTime.of(9,0)));
        List<Delivery> deliveries = new ArrayList<>();
        deliveries.add(d);
        DailyPlanning next = new DailyPlanning(deliveries);
        assertFalse(next.availableSlotForGivenDate(9));

    }


}