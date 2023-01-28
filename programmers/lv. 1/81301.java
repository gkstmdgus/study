class Solution_81301 {
    public int solution(String s) {
        String[][] arr = new String[][]{{"zero","0"},{"one","1"},{"two","2"},{"three","3"},{"four","4"},{"five","5"},{"six","6"},{"seven","7"},{"eight","8"},{"nine","9"}};
        for (int i = 0; i < arr.length; i++) {
            s = s.replaceAll(arr[i][0],arr[i][1]);
        }
        return Integer.valueOf(s);
    }
}

class Main_81301 {
    public static void main(String[] args) {
        Solution_81301 sol = new Solution_81301();
        System.out.println(sol.solution("one4seveneight"));

    }
}