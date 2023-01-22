class Solution_12935 {
    public int[] solution(int[] arr) {
        if(arr.length == 1)
            return new int[]{-1};
        int[] answer = new int[arr.length-1];
        int minNum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            minNum = Math.min(minNum,arr[i]);
        }
        for (int i = 0, c = 0; i < arr.length; i++, c++) {
            if(arr[i] == minNum){
                c--;
                continue;
            }
            answer[c] = arr[i];
        }
        return answer;
    }
}

class Main_12935 {
    public static void main(String[] args) {
        Solution_12935 sol = new Solution_12935();
        System.out.println(sol.solution(new int[]{1,2,3}));
    }
}