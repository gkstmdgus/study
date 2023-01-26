import java.util.*;
class Solution_68935 {
    public int solution(int n) {
        int answer = 0;
        Stack stack = new Stack();
        while(n != 0){
            stack.push(n%3);
            n /= 3;
        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            answer += ((Integer)stack.pop()).intValue() * Math.pow(3,i);
        }
        return answer;
    }
}

class Main_68935{
    public static void main(String[] args) {
        Solution_68935 sol = new Solution_68935();
        System.out.println(sol.solution(125));
    }
}