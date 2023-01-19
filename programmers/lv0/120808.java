package lv0;
import java.util.Arrays;

class Solution_120808{
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        int minCommonDivisor = 1;       //  최소공배수
        int maxCommonMultiple = 1;      //  최대공약수
        int tempDenom1 = denom1;
        int tempDenom2 = denom2;
        int i = 1;
        while(true){    // 최대, 최소공배수 구하기
            i++;
            if (tempDenom1 % i == 0 && tempDenom2 % i ==0){
                maxCommonMultiple *= i;
                tempDenom1 /= i;
                tempDenom2 /= i;
                i = 1;
                continue;
            }
            if (tempDenom1 < i && tempDenom2 < i){
                minCommonDivisor = maxCommonMultiple * tempDenom1 * tempDenom2;
                i = 1;
                break;
            }
        } // end of while
        if (denom1 == denom2){  // same denom
            answer[1] = maxCommonMultiple;
            answer[0] = numer1 * (maxCommonMultiple / denom1) + numer2 * (maxCommonMultiple / denom2);
        } else{     // different denom
            answer[1] = minCommonDivisor;
            answer[0] = numer1 * (minCommonDivisor / denom1) + numer2 * (minCommonDivisor / denom2);
        }
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

class Main_120808{
    public static void main(String[] args) {
        Solution_120808 sol = new Solution_120808();
        System.out.println(Arrays.toString(sol.solution(3,4,3,4)));
    }
}