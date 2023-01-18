import java.util.*;
class Solution_12934 {
    public long solution(long n) {
        long answer = -1;
        double root = Math.sqrt(n);
        if(root == (int)root)
            answer = (long)Math.pow(root + 1,2);
        return answer;
    }
}

class Main_12934 {
    public static void main(String[] args) {
        Solution_12934 sol = new Solution_12934();
        System.out.println(sol.solution(3));
    }
}
