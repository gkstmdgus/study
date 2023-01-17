import java.util.*;
public class Main extends Object {
    public int solution(int n){
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if(n%(i+1)==0)
                answer += i + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solution(5));
    }
}