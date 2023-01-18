import java.util.*;
class Main_12931 extends Object {
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
        Main_12931 m = new Main_12931();
        System.out.println(m.solution(987));
    }
}