class Solution_82612 {
    public long solution(int price, int money, int count) {
        long answer = 0;
        for (int i = 1; i < count + 1; i++) {
            answer += price * i;
        }
        return answer <= money ? 0 : answer-money;
    }
}

class Main_82612{
    public static void main(String[] args) {

    }
}