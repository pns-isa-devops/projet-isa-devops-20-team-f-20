import entities.Delivery;
import entities.PackageStatus;
import entities.Supplier;
import entities.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanDeliveryTest {

    private List<Delivery> deliveries = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Supplier supplier = new Supplier("UPS", "Cannes");
        Package pack = new Package("1", "testuser", PackageStatus.REGISTERED, "210 avenue roumanille", supplier);
        Delivery existingDelivery = new Delivery(pack, null, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        deliveries.add(existingDelivery);
    }

    @Test
    void planDeliveryAvailable() {
        SchedulerBean scheduler = new SchedulerBean();
        Delivery d = scheduler.planDelivery(new Package("2", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)), deliveries);
        System.out.println(d);
        assertNotNull(d);
    }

    @Test
    void planDeliveryNotAvailable(){
        SchedulerBean scheduler = new SchedulerBean();
        Delivery d = scheduler.planDelivery(new Package("2", "testuser", PackageStatus.REGISTERED,
                        "210 avenue roumanille", new Supplier("UPS", "Cannes")),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)), deliveries);
        System.out.println(d);
        assertNull(d);
    }
}