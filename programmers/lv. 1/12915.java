import java.util.Arrays;
import java.util.Comparator;

class Solution_12915 {
    public String[] solution(String[] strings, int n) {
        String[] result = new String[strings.length];
        String[][] answer = new String[strings.length][2];
        // answer[i][0]에 기본 값 입력, answer[i][1]에는 자른 값 입력
        for (int i = 0; i < answer.length; i++) {
            answer[i][0] = strings[i];
            answer[i][1] = String.valueOf(strings[i].charAt(n));
        }
        // answer[i][1]를 기준으로 정렬
        Arrays.sort(answer,new SortComparator());
        for (int i = 0; i < answer.length; i++) {
            result[i] = answer[i][0];
        }
        return result;
    }
}

class SortComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof String[] && o2 instanceof String[]))
            return -1;
        String[] str1 = (String[]) o1;
        String[] str2 = (String[]) o2;
        if(str1[1].equals(str2[1]))
            return str1[0].compareTo(str2[0]);
        return str1[1].compareTo(str2[1]);
    }
}

class Main_12915 {
    public static void main(String[] args) {
        Solution_12915 sol = new Solution_12915();
        System.out.println(Arrays.toString(sol.solution(new String[]{"sun", "bed", "car"},1)));
    }
}