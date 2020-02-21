import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloSchedulerTest {

    @Test
    void hello() {
        HelloScheduler helloScheduler = new HelloScheduler();
        helloScheduler.hello();
        assertTrue(true);
    }
}