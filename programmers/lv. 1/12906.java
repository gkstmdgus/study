import java.lang.reflect.Array;
import java.util.*;
class Solution_12906 {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int nowNumber = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (nowNumber != arr[i]) {
                list.add(nowNumber);
                nowNumber = arr[i];
            }
            if (i == arr.length - 1)
                list.add(arr[i]);
        }
        return list;
    }
}

class Main_12906{
    public static void main(String[] args) {
        Solution_12906 sol = new Solution_12906();
        System.out.println(sol.solution(new int[]{1,1,3,3,0,1,1}));
    }
}