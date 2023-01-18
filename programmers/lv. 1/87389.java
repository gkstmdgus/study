import java.util.*;
class Solution_87389 {
    public int solution(int n) {
        int answer = 0;
        int i = 0;

        while(answer != 1){
            i++;
            answer = n % i;
        }
        return i;
    }
}

class Main_87389 {
    public static void main(String[] args) {
        Solution_87389 sol = new Solution_87389();
        System.out.println(sol.solution(12));
    }
}
