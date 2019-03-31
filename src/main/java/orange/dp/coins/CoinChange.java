package orange.dp.coins;
/*
* Two problems from HackerRank
* 1) https://www.hackerrank.com/challenges/coin-change/problem
* */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CoinChange {

    private final long value;
    private final List<Integer> coins;
    private long[][] cache;
    private Map<CoinIndex, Long> Cache = new HashMap<>();

    public CoinChange(long value, List<Integer> coins) {
        this.value = value;
        this.coins = coins;
        cache = new long[(int)value+1][coins.size()+1];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Integer Value = Integer.valueOf(reader.readLine().split(" ")[0]);
        new CoinChange(Value, Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList()))
        .execute(); //add method call over here.
    }

    public long execute(){
        long val = solve(new CoinIndex(value,0, coins.size()));
        System.out.print(val);
        return val;
    }

    // will return the number of ways to make chane of Value given coins using only these coins [].
    // going with the top-down approach with caching.

    public long solve(final CoinIndex coins){
        //base case
        if (coins.value < 0){
            return 0;
        }
        if (coins.value == 0){
            return 1;
        }
        // for each coin in the range use
        Long cached = this.Cache.get(coins);
        if (cached != null) {
            return cached;
        }

        long sum = 0;
        for (long i = coins.getL(); i < coins.getR(); i++){
            long target = coins.value - this.coins.get((int)i);
            CoinIndex cc = new CoinIndex(target, i, coins.getR());
            //check the cache before making recursive call
            sum += solve( cc);

        }
        this.Cache.put(coins, sum);
        return sum;
    }

    private static class CoinIndex {
        private long value;
        private long L;
        private long R;


        public CoinIndex(long value, long l, long r) {
            this.value = value;
            L = l;
            R = r;
        }

        public long getL() {
            return L;
        }

        public long getR() {
            return R;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CoinIndex coinIndex = (CoinIndex) o;
            return value == coinIndex.value &&
                    L == coinIndex.L &&
                    R == coinIndex.R;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, L, R);
        }
    }

}
