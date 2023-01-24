
class Solution_12922 {
    public String solution(int n) {
        StringBuffer answer = new StringBuffer();
        for (int i = 1; i < n+1; i++) {
            if(i % 2 == 0)
                answer.append("박");
            else
                answer.append("수");
        }
        return answer.toString();
    }
}

class Main_12922{
    public static void main(String[] args) {

    }
}