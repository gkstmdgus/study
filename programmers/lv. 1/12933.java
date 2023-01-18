import java.util.*;
class Solution_12933 {
    public long solution(long n) {
        long answer = 0;
        long x = 1;
        String str = "" + n;

        char[] ch = str.toCharArray();
        Arrays.sort(ch);
        for(char i : ch){
            answer += (int)(i-'0') * x;
            x *= 10;
        }
        return answer;
    }
}

class Main_12933 {
    public static void main(String[] args) {
        Solution_12933 sol = new Solution_12933();
//        System.out.println(sol.solution(8000000000));
        long l = 8_000_000_000L;
        System.out.println(l);
    }
}
