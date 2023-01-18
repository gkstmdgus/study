import java.util.*;
class Solution_12943 {
    public int solution(long num) {
        int answer = 0;
        while(num != 1){
            answer++ ;
            num = (num % 2 == 0) ? num / 2 : 3 * num + 1;
            if(answer == 500)
                return -1;
        }
        return answer;
    }
}

class Main_12943 {
    public static void main(String[] args) {
        Solution_12943 sol = new Solution_12943();
        System.out.println(sol.solution(626331));
    }
}
