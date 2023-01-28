class Solution_131705 {
    public int solution(int[] number) {
        int answer = 0;
        if(number.length < 3) return 0;
        for (int i = 0; i < number.length - 2; i++) {
            for (int j = i + 1; j < number.length - 1; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if(number[i] + number[j] + number[k] == 0) answer+= 1;
                }
            }
        }
        return answer;
    }
}

class Main_131705{
    public static void main(String[] args) {

    }
}