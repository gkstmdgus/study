import java.util.Arrays;

class Solution_76501{
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            answer += signs[i] ? absolutes[i] : -1 * absolutes[i];
        }
        return answer;
    }
}

class Main_76501{
    public static void main(String[] args) {

    }
}