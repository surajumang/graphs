package orange.misc;

import java.util.Objects;

/**
 * Created 8/31/2019
 *
 * @author sjkumar
 */
public class Pair<P, Q> {
    private P first;
    private Q second;

    public Pair(P first, Q second) {
        this.first = first;
        this.second = second;
    }

    public static <P1, Q1> Pair<P1, Q1> create(P1 first, Q1 second){
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return first.equals(pair.first) &&
                second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
