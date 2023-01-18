import java.util.*;
class Solution_12954 {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long tmp = x;
        for (int i = 0; i < n; i++) {
            answer[i] = tmp;
            tmp += x;
        }
        return answer;
    }
}

class Main_12954 {
    public static void main(String[] args) {
        Solution_12954 sol = new Solution_12954();
        System.out.println(Arrays.toString(sol.solution(2,5)));
    }
}
