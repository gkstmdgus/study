class Solution_12940 {
    public int[] solution(int n, int m) {
        int[] answer = {1,1};  // answer[0] 최대 공약수, answer[1] 최소 공배수
        int num = 1;
        while(true){
            num++;
            if(n % num == 0 && m % num == 0){
                n /= num;
                m /= num;
                answer[0] *= num;
                num = 1;
            }
            if(num > n && num > m)
                break;
        }
        answer[1] = answer[0] * n * m;
        return answer;
    }
}

class Main_12940{
    public static void main(String[] args) {
    }
}