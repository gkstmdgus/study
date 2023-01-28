import java.util.Arrays;

class Solution_42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < answer.length; i++) {
            int[] temp = new int[commands[i][1] - commands[i][0] + 1];
            for (int j = commands[i][0] - 1,c = 0; j < commands[i][1]; j++, c++) {
                temp[c] = array[j];
            }
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }
        return answer;
    }
}

class Main_42748{
    public static void main(String[] args) {
        Solution_42748 sol = new Solution_42748();
        System.out.println(Arrays.toString(sol.solution(new int[]{1, 5, 2, 6, 3, 7, 4},new int[][]{{2,5,3},{4,4,1},{1,7,3}})));
    }
}