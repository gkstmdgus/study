import java.util.Arrays;

class Solution_17681 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] strArr1 = new String[n];
        String[] strArr2 = new String[n];

        // 초기값을 공백으로 초기화
        Arrays.fill(answer,"0");
        Arrays.fill(strArr1,"0");
        Arrays.fill(strArr2,"0");

        // arr1, arr2 를 이진수로 변환해서 strArr1, strArr2 배열에 문자열로 입력.
        for (int i = 0; i < n; i++) {
            strArr1[i] = String.format("%05d",Integer.parseInt(Integer.toBinaryString(arr1[i])));
            strArr2[i] = String.format("%05d",Integer.parseInt(Integer.toBinaryString(arr2[i])));

            for (int j = 0; j < strArr1[i].length(); j++) {

            }
        }

        // charAt()메서드를 사용해서 둘 다 1이면 " ", 나머지먼 "#" 입력.
        for (int i = 0; i < strArr1[i].length(); i++) {
            for (int j = 0; j < strArr1[i].length(); j++) {
                if(strArr1[i].charAt(j) == '0' && strArr2[i].charAt(j) == '0') {
                    answer[i] += " ";
                    continue;
                }
                answer[i] += "#";
            }
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }
}

class Main_17681{
    public static void main(String[] args) {
        Solution_17681 sol = new Solution_17681();
//        System.out.println(Arrays.toString(sol.solution(6,new int[]{46,33,33,22,31,50},new int[]{27,56,19,14,14,10})));
        String str = "00000";
        String test = String.format("%05d",Integer.parseInt(Integer.toBinaryString(10)));
        System.out.println(test);
    }
}