package minmax;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MinMaxReporter <T> {

    T min;
    T max;

    public MinMaxReporter( Comparator<? super T> t) {
        Objects.requireNonNull(t);
    }
    public <S> void add(S s) {
        Objects.requireNonNull(s);

    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

    public void setOnMinChanged(BiConsumer consumer) {

    }

    public void setOnMaxChanged(BiConsumer consumer) {

    }
}
