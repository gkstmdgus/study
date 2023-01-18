import java.util.*;
class Solution_12912 {
    public long solution(int a, int b) {
        int tmp = 0;
        if(a > b){
            tmp = a;
            a= b;
            b = tmp;
        }
        long answer = 0;
        for (int i = a; i < b+1; i++) {
            answer += i;
        }
        return answer;
    }
}

class Main_12912 {
    public static void main(String[] args) {
        Solution_12912 sol = new Solution_12912();
        System.out.println(sol.solution(3,5));
    }
}
