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
    @Test
    public void ShouldDoubleCapacity() {
        // Arrange
        // makes instance of `DynamicArray` with size 2
        DynamicArray<String> dynamicArray = new DynamicArray<>("foo");
        
        // Act
        dynamicArray.add("fizz");

        // Assert
        /* The capacity gets full, if the doubling
        wouldn't work it'd throw an Exception */
        assertDoesNotThrow(() -> {
                dynamicArray.add("buzz");
                dynamicArray.add("brazz");
            }
        );
    }
    @Test
    public void GetElements() {
        // Arrange
        DynamicArray<String> dynamicArray = new DynamicArray<>("foo");

        // Act
        dynamicArray.add("fizz");
        dynamicArray.add("buzz");
        dynamicArray.add("brazz");

        // Assert
        assertEquals(dynamicArray.get(0), "foo");
        assertEquals(dynamicArray.get(1), "fizz");
        assertEquals(dynamicArray.get(2), "buzz");
        assertEquals(dynamicArray.get(3), "brazz");
    }
    @Test
    public void InsertElement() {
        // Arrange
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        
        // Act
        dynamicArray.add("foo");
        dynamicArray.add("fizz");
        dynamicArray.add("buzz");
        // add `brazz` between foo and fizz
        dynamicArray.add(1, "brazz");

        // Asssert
        System.out.println("INDEX 0 " + dynamicArray.get(0));
        System.out.println("INDEX 1 " + dynamicArray.get(1));
        System.out.println("INDEX 2 " + dynamicArray.get(2));
        System.out.println("INDEX 3 " + dynamicArray.get(3));
        assertEquals(dynamicArray.get(0), "foo");
        assertEquals(dynamicArray.get(1), "brazz");
        assertEquals(dynamicArray.get(2), "fizz");
        assertEquals(dynamicArray.get(3), "buzz");
    }
}