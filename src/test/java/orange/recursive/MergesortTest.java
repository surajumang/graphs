package orange.recursive;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * Created 4/15/2019
 *
 * @author sjkumar
 */
public class MergesortTest {

    @Test
    public void mergeTest() {
        List<Integer> a = Arrays.asList(1,3,4,7);
        List<Integer> b = Arrays.asList(3,4,5,9,9);
        List<Integer> c = Mergesort.merge(a, b, new ArrayList<>());
        c.forEach(i -> System.out.print(i + " "));
    }

    @Test
    public void sortTest() {
        List<Integer> b = Arrays.asList(3,4,5,9,9);
        List<Integer> bb = Mergesort.mergeSort(b);
        b.sort(Integer::compareTo);
        Assertions.assertThat(bb).containsSequence(b);
    }

    @Test
    public void randomTest() {
        for (int i = 0; i < 100; i++) {
            List<Integer> intial = getRandomIntegers(10000);
            List<Integer> fina = Mergesort.mergeSort(intial);
            intial.sort(Integer::compareTo);
            Assertions.assertThat(fina).containsSequence(intial);
        }
    }

    private static List<Integer> getRandomIntegers(int size){
        Random r = new Random(997);
        List<Integer> a = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            a.add(r.nextInt());
        }
        return a;
    }
}