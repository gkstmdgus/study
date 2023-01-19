package lv0;
import java.util.Arrays;

class Solution_120808_1{
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int i = 1;
        answer[1] = denom1 * denom2;
        answer[0] = numer1 * denom2 + numer2 * denom1;
        while(true){
            i++;
            if (answer[0] % i == 0 && answer[1] % i ==0){
                answer[0] /= i;
                answer[1] /= i;
                i = 1;
                continue;
            }
            if (answer[0] < i && answer[1] < i){
                break;
            }
        }
        return answer;
    }
}

class Main_120808_1{
    public static void main(String[] args) {
        Solution_120808_1 sol = new Solution_120808_1();
        System.out.println(Arrays.toString(sol.solution(3,4,5,6)));
    }
}