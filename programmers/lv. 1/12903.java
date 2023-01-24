
class Solution_12903 {
    public String solution(String s) {
        String answer = "";
        int length = s.length();
        if(length % 2 == 0)
            answer = "" + s.charAt(length/2-1) + s.charAt(length/2);
        else
            answer += s.charAt(length/2);
        return answer;
    }
}

class Main_12903{
    public static void main(String[] args) {
        Solution_12903 sol = new Solution_12903();
        System.out.println(sol.solution("qwerqw"));
    }
}