import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LLTest {
    @Test
        //@DisplayName("Add two numbers")
    void add_items() {
        assertEquals(4, Calculator.add(2, 2));
    }
    @Test
        //@DisplayName("Add two numbers")
    void read_items() {
        assertEquals(4, Calculator.add(2, 2));
    }

    @Test
        //@DisplayName("Multiply two numbers")
    void DFS() {
        assertAll(() -> assertEquals(4, Calculator.multiply(2, 2)),
                () -> assertEquals(-4, Calculator.multiply(2, -2)),
                () -> assertEquals(4, Calculator.multiply(-2, -2)),
                () -> assertEquals(0, Calculator.multiply(1, 0)));
    }
    @Test
        //@DisplayName("Add two numbers")
    void sort() {
        assertEquals(4, Calculator.add(2, 2));
    }

}