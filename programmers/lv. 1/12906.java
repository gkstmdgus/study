import java.util.*;
class Solution_12906 {
    public int[] solution(int []arr) {
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        int nowNumber = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(nowNumber != arr[i]){
                list.add(nowNumber);
                nowNumber = arr[i];
            }else if(i == arr.length - 1)
                list.add(nowNumber);
        }
        list.toArray(new Integer[list.size()])

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        return answer;
    }
}

class Main_12906{
    public static void main(String[] args) {
        Solution_12906 sol = new Solution_12906();
        System.out.println(sol.solution(new int[]{1,1,3,3,0,1,1}));
    }
}