class Solution_86491 {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = sizes[0][0];
        int maxHeight = sizes[0][1];
        // 가로, 세로 최대 최소 구함
        for (int i = 1; i < sizes.length; i++) {
            maxWidth = Math.max(maxWidth,sizes[i][0]);
            maxHeight = Math.max(maxHeight,sizes[i][1]);
        }
        if (maxWidth == maxHeight){
            answer = maxWidth * maxHeight;
        } else if (maxWidth > maxHeight){

        } else {

        }
        return answer;
    }
}

class Main_86491{
    public static void main(String[] args) {

    }
}