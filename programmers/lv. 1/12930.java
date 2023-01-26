import java.util.Arrays;

// 공백이 앞 뒤로 있을 때 문제를 해결해야함
class Solution_12930 {
    public String solution(String s) {
        StringBuffer answer = new StringBuffer();
        char[] chArr = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chArr.length; i++) {
            if(chArr[i] == ' '){
                count = 0;
                continue;
            }
            chArr[i] = count++ % 2 == 0 ? Character.toUpperCase(chArr[i]) : Character.toLowerCase(chArr[i]);
        }
        return String.valueOf(chArr);
    }
}

class Main_12930{
    public static void main(String[] args) {
        Solution_12930 sol = new Solution_12930();
        System.out.println(sol.solution(new String("  try  rat   ")));
        String str = "--abc--";
        String[] arr = str.split("-");
        System.out.println(str.length());
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}