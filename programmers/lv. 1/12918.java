class Solution_12918 {
    public boolean solution(String s) {
        if(!(s.length() == 4 || s.length() == 6))
            return false;
        String str = s.replaceAll("[0-9]","");
        return str.equals("") ? true : false;
    }
}

class Main_12918{
    public static void main(String[] args) {

    }
}