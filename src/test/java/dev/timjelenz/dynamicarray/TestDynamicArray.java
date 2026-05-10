package dev.timjelenz.dynamicarray;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestDynamicArray {
    @Test
    public void shouldMatchInputSize() {
        // Arrange
        DynamicArray<String> dynamicArray1 = new DynamicArray<>("foo", "fizz", "buzz");
        DynamicArray<String> dynamicArray2 = new DynamicArray<>();

        // Assert
        assertEquals(dynamicArray1.size(), 3);
        assertEquals(dynamicArray2.size(), 0);
    }
}