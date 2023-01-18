import java.util.*;
class Main_12928 extends Object {
    public int solution(int n){
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if(n%(i+1)==0)
                answer += i + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        Main_12928 m = new Main_12928();
        System.out.println(m.solution(5));
    }
}