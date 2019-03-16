package orange.dp.sequences;

import orange.dp.Sherlock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/*
* */
public class LargestCommonSubsequence {

    private List<? extends Comparable> first;
    private List<? extends Comparable> second;

    private int[][] cache;

    public LargestCommonSubsequence(List<? extends Comparable> first, List<? extends Comparable> second) {
        this.first = first;
        this.second = second;
        cache = new int[first.size()][second.size()];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        List<Integer> f = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> s = Arrays.stream(reader.readLine().split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        solve(f,s);
    }

    public static <T extends Comparable> int solve(final List<T> first, final List<T> second){
        LargestCommonSubsequence l = new LargestCommonSubsequence(first,second);
        int val = l.topDown(0,0);
        l.printcache();
        return val;
//        return execute(first, second);
    }

    //finds the length of the largest common sub-sequence.
    public static <T extends Comparable> int execute(final List<T> first, final List<T> second){
        //base case.
        if (first.size() == 0 || second.size() == 0){
            return 0;
        }
        if (first.get(0).equals(second.get(0))){
            System.out.print(first.get(0));
            return 1 + solve(first.subList(1,first.size()), second.subList(1, second.size()));
        }
        int left = solve(first.subList(1,first.size()), second);
        int right = solve(first, second.subList(1, second.size()));

        return max(left, right);
    }

    private static int max(int a, int b){
        return (a > b) ? a : b;
    }

    private void printcache(){
        StringJoiner sj  =new StringJoiner(" ");
        int m = 0, n = 0;
        while(m < first.size() && n < second.size()){
            if (first.get(m).equals(second.get(n))){
                sj.add(first.get(m).toString());
                m++; n++;
            }else if (readGracefully(m+1, n) > readGracefully(m, n+1)){
                m++;
            }else{
                n++;
            }
        }
        System.out.println(sj.toString());
    }

    private int readGracefully(int i, int j){
        if (i < 0 || i>= cache.length || j<0 || j >= cache[i].length){
            return -1;
        }
        return cache[i][j];
    }

    /* ****************BOTTOM_UP approach *******************/

    private int topDown(int l, int r){
        //base
        if (l < 0 || r < 0 || l >= first.size() || r >= second.size() ){
            return 0;
        }
//        if (first.get(l).equals(second.get(r))){
//            return 1;
//        }
        // first chars are equal
        if (cache[l][r] > 0){
            return cache[l][r];
        }
        if (first.get(l).equals(second.get(r))){
            int val = 1 + topDown(l+1, r+1);
            cache[l][r] = val;
            return val;
        }
        int left = topDown(l, r+1);
        int right = topDown(l+1, r);

        int val = max(left, right);
        cache[l][r] = val;
        return val;
    }

    public void LCS(){
        //filling the cache
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                //base case
                if (i == 0 || j == 0){
                    cache[i][j] = 0;
                }
            }
        }
    }
}
