import java.util.*;
class Solution_12947 {
    public boolean solution(int x) {
        boolean answer = true;
        String str = "" + x;
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += (int)(str.charAt(i)-'0');
        }
        if (x % sum != 0)
            answer = false;
        return answer;
    }
}

class Main_12947 {
    public static void main(String[] args) {
        Solution_12947 sol = new Solution_12947();
        System.out.println(sol.solution(13));
    }
}
