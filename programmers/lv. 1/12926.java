import java.util.Arrays;

class Solution_12926 {
    public String solution(String s, int n) {
        int num = 0;
        char[] chArr = s.toCharArray();
        for (int i = 0; i < chArr.length; i++) {
            if(chArr[i] == ' ') continue;
            // 대문자 65 ~ 90
            if('A' <= chArr[i] && chArr[i] <= 'Z'){
                num = chArr[i] + n;
                chArr[i] = num > 90 ? (char) (num - 26) : (char)(chArr[i] + n);
            }else{  // 소문자 97 ~ 122
                num = chArr[i] + n;
                chArr[i] = num > 122 ? (char) (num - 26) : (char)(chArr[i] + n);
            }
        }
        return String.valueOf(chArr);
    }
}

class Main_12926 {
    public static void main(String[] args) {

    }
}