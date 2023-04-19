package tests;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxReporterTest {

    @Test
    void basicsInteger() {
        Comparator<Integer> intComparator = Integer::compare;
        MinMaxReporter<Integer> reporter = new MinMaxReporter<>(intComparator);

        reporter.add(10);
        reporter.add(4);
        reporter.add(19);
        reporter.add(23);

        assertEquals(4, reporter.getMin());
        assertEquals(23, reporter.getMax());

        reporter.add(17);
        reporter.add(5);

        assertEquals(4, reporter.getMin());
        assertEquals(23, reporter.getMax());

        reporter.add(2);

        assertEquals(2, reporter.getMin());
        assertEquals(23, reporter.getMax());

        reporter.add(42);

        assertEquals(2, reporter.getMin());
        assertEquals(42, reporter.getMax());
    }

    @Test
    void basicsString() {
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        MinMaxReporter<String> reporter = new MinMaxReporter<>(lengthComparator);

        reporter.add("Hi");
        reporter.add("Hello");

        assertEquals("Hi", reporter.getMin());
        assertEquals("Hello", reporter.getMax());

        reporter.add("Bye");
        reporter.add("Nope");

        assertEquals("Hi", reporter.getMin());
        assertEquals("Hello", reporter.getMax());

        reporter.add("I");

        assertEquals("I", reporter.getMin());
        assertEquals("Hello", reporter.getMax());

        reporter.add("Good bye!");

        assertEquals("I", reporter.getMin());
        assertEquals("Good bye!", reporter.getMax());
    }

    @Test
    void onMinChanged() {
        MinMaxReporter<Double> reporter = new MinMaxReporter<>(Double::compare);
        var log = new String[] {""};
        reporter.setOnMinChanged((oldMin, newMin) -> {
            log[0] += oldMin + " -> " + newMin + "\n";
        });

        reporter.add(4.3);
        reporter.add(7.0);
        reporter.add(2.8);
        reporter.add(2.7);

        assertEquals(log[0], """
                null -> 4.3
                4.3 -> 2.8
                2.8 -> 2.7
                """);
    }

    @Test
    void onMaxChanged() {
        MinMaxReporter<Integer> reporter = new MinMaxReporter<>(Integer::compare);
        var log = new String[] {""};
        reporter.setOnMaxChanged((oldMin, newMin) -> {
            log[0] += oldMin + " -> " + newMin + "\n";
        });

        reporter.add(8);
        reporter.add(10);
        reporter.add(9);
        reporter.add(100);

        assertEquals(log[0], """
                null -> 8
                8 -> 10
                10 -> 100
                """);

        reporter.add(45);
        reporter.add(99);

        assertEquals(log[0], """
                null -> 8
                8 -> 10
                10 -> 100
                """);

        reporter.add(100); // if the new element is equal to the max, no event is fired

        assertEquals(log[0], """
                null -> 8
                8 -> 10
                10 -> 100
                """);

        reporter.add(101);

        assertEquals(log[0], """
                null -> 8
                8 -> 10
                10 -> 100
                100 -> 101
                """);
    }
}
