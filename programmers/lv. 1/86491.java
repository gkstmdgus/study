class Solution_86491 {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        // 둘 중 큰 값은 왼쪽, 작은 값은 오른쪽으로 이동
        for (int i = 0; i < sizes.length; i++) {
            if(sizes[i][1] > sizes[i][0]){
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
        }
        // 가로, 세로 최대 최소 구함
        for (int i = 0; i < sizes.length; i++) {
            maxWidth = Math.max(maxWidth,sizes[i][0]);
            maxHeight = Math.max(maxHeight,sizes[i][1]);
        }
        return maxHeight * maxWidth;
    }
}

class Main_86491{
    public static void main(String[] args) {

    }
}