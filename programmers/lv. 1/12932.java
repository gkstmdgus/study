import java.util.*;
public class Main extends Object {
    public long[] solution(long n){
        String str = "" + n;
        long[] answer = new long[str.length()];

        for (int i = 0; i < str.length(); i++) {
            answer[i] = (int) n % 10;
            n = n /10;
        }
        return answer;
    }

    public static void main(String[] args) {
        Main m = new Main();
        long[] arr = m.solution(12345);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
    }
}