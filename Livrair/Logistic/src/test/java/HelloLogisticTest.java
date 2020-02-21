import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloLogisticTest {

    @Test
    void hello() {
        HelloLogistic helloLogistic = new HelloLogistic();
        helloLogistic.hello();
        assertTrue(true);
    }
}