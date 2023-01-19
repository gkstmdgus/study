import java.util.Arrays;

class Solution_12919{
    public String solution(String[] seoul) {
        int idx = 0;
        for (int i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")){
                System.out.println(seoul[i]);
                System.out.println(i);
                idx = i;
                break;
            }
        }
        String answer = "김서방은 "+idx+"에 있다.";
        return answer;
    }
}

class Main_12919{
    public static void main(String[] args) {
        Solution_12919 sol = new Solution_12919();
        System.out.println(sol.solution(new String[]{"Han","bird","Kan","Kim","Book"}));
//        String[] str = new String[]{"Han","bird","Kan","Kim","Book"};
//        System.out.println(str.length);
//        System.out.println(str[0]);
//        System.out.println(Arrays.toString(str));
    }
}