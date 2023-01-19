class Solution_12948{
    public String solution(String phone_number) {
        int starNum = phone_number.length()-4;
        String star = "*".repeat(starNum);
        String answer = star + phone_number.substring(starNum);
        return answer;
    }
}

class Main_12948{
    public static void main(String[] args) {
//        Solution_12948 sol = new Solution_12948();
//        System.out.println(sol.solution("027778888"));
        double div = 3/2d;
        System.out.println(div);
    }
}