import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HelloDeliveryTest {

    @Test
    void hello() {
        HelloDelivery hellodelivery = new HelloDelivery();
        hellodelivery.hello();
        assertTrue(true);
    }
}