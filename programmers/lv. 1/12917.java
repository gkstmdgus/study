import java.util.Arrays;
import java.util.Comparator;

class Solution_12917 {
    public String solution(String s) {
        String[] strArr = s.split("");
        Arrays.sort(strArr, new newComparator());
        return String.join("",strArr);
    }
}
class newComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof String && o2 instanceof  String))
            return -1;
        String s1 = (String) o1;
        String s2 = (String) o2;
        return s2.compareTo(s1);
    }
}

class Main_12917{
    public static void main(String[] args) {
        Solution_12917 sol = new Solution_12917();
        System.out.println(sol.solution("Zbcdefg"));
    }
}