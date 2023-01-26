class Solution_12982 {
    public int solution(int[] d, int budget) {
        java.util.Arrays.sort(d);
        int answer = 0;
        int sum = 0;
        for (int i = 0; i < d.length; i++) {
            sum += d[i];
            if(sum > budget) break;
            answer += 1;
        }
        return answer;
    }
}

class Main_12982{
    public static void main(String[] args) {

    }
}