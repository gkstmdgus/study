class Solution_86051 {
    public int solution(int[] numbers) {
        int answer = 45;    // 1~9 합
        for (int i = 0; i < numbers.length; i++) {
            answer -= numbers[i];
        }
        return answer;
    }
}

class Main_86051{
    public static void main(String[] args) {

    }
}