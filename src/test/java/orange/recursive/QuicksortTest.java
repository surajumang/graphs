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
public class QuicksortTest {

    @Test
    public void testPartition1() {
        List<Integer> a = Arrays.asList(5,4,7,2,1,3);
        int index = Quicksort.placePivot(a);
        System.out.println("Index is " + index);
        a.forEach(i -> System.out.print(i + " "));
    }

    @Test
    public void sort1() {
        List<Integer> a = new ArrayList<>(Arrays.asList(5, 4, 7, 2, 1, 3));
        List<Integer> b= Quicksort.quicksort(a);
        b.forEach(i -> System.out.print(i + " "));
    }

    @Test
    public void randomTest() {
        for (int i = 0; i < 100; i++) {
            List<Integer> intial = getRandomIntegers(10000);
            List<Integer> fina = Quicksort.quicksort(intial);
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