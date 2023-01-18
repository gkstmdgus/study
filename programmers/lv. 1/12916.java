import java.util.*;
class Main_12916 extends Object {
    public boolean solution(String s){
        boolean answer = true;
        int pNum = 0;
        int yNum = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'p' || s.charAt(i) == 'P')
                pNum += 1;
            else if (s.charAt(i) == 'y' || s.charAt(i) == 'Y')
                yNum += 1;
        }

        if(pNum != yNum)
            return false;
        
        return answer;
    }

    public static void main(String[] args) {
        Main_12916 m = new Main_12916();
        System.out.println(m.solution("pPoooyY"));
    }
}