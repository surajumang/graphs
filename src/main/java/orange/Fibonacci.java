package orange;

// compute the ith fibonacci number. (use the matrix exponentiation method.)
// consider the Mathematical formula which comes by solving the recurrence relation.(Approx answer in O(1))
public class Fibonacci {
    public static void main(String [] arg){
        String s="abc";
        String s1=s.toString();
        String s2=s1.toLowerCase();
        String s3=s1.toUpperCase();
        String s4=s3.toLowerCase();

        print(s, s1, s2, s3, s4);
    }

    static <T> void print(String delim, T... values){
        for (int i = 0; i < values.length; i++) {
            System.out.println(delim + System.identityHashCode(values[i]));
        }
    }
}
