import java.util.*;

class Solution_12910{
    public int[] solution(int[] arr, int divisor) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % divisor == 0){
                str += arr[i] + ",";
            }
        }
        if(str.equals(""))
            return new int[]{-1};
        String[] strArr =str.split(",");
        int[] answer = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            answer[i] = Integer.parseInt(strArr[i]);
        }
        Arrays.sort(answer);
        return answer;
    }
}

class Main_12910{
    public static void main(String[] args) {
        Solution_12910 sol = new Solution_12910();
        int[] arr = {3,2,6};
        System.out.println(Arrays.toString(sol.solution(arr,10)));
    }
}