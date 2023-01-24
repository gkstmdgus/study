class Solution_77884 {
    public int solution(int left, int right) {
        int answer = 0;
        int count = 0;
        for (int i = left; i < right+1; i++) {
            for (int j = 1; j < i + 1; j++) {
                count += i % j == 0 ? 1 : 0;
            }
            answer += count % 2 == 0 ? i : -1 * i;
            count = 0;
        }
        return answer;
    }
}

class Main_77884{
    public static void main(String[] args) {

    }
}