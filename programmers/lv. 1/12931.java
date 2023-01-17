import java.util.*;
public class Main extends Object {
    public int solution(int n){
        int answer = 0;
        int num = 1;
        int rest = 0;

        while(n % num != n){
            num *= 10;
            rest = n % num;
            n -= rest;
            answer += rest/(num/10);
        }
        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        System.out.println(m.solution(987));
    }
}