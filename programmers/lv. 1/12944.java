
public class Main extends Object {
    public double solution(int[] arr){
        double answer = 0;
        int leng = arr.length;
        for (int i = 0; i < leng; i++) {
            answer += arr[i];
        }

        return answer/leng;
    }
    public static void main(String[] args) {

    }
}