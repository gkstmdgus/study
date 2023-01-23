// 11-1. 다음은 정수집합 1,2,3,4와 3,4,5,6의 교집합, 차집합, 합집합을 구하는 코드이
//다. 코드를 완성하여 실행결과와 같은 결과를 출력하시오.

import java.util.*;
class Exercise11_1{
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList kyo = new ArrayList();
        ArrayList cha = new ArrayList();
        ArrayList hap = new ArrayList();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        kyo.addAll(list1);
        kyo.retainAll(list2);
        cha.addAll(list1);
        cha.removeAll(kyo);
        hap.addAll(cha);
        hap.addAll(list2);

        System.out.println("list1 = " + list1);
        System.out.println("list2 = " + list2);
        System.out.println("kyo = " + kyo);
        System.out.println("cha = " + cha);
        System.out.println("hap = " + hap);

    }
}

// 11-2. 다음 코드의 실행결과를 적으시오.

class Exercise11_2{
    public static void main(String[] args){
        ArrayList list = new ArrayList();
        list.add(3);
        list.add(6);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(7);

        HashSet set = new HashSet(list);
        TreeSet tset = new TreeSet(list);
        Stack stack = new Stack();
        stack.addAll(tset);

        while(!stack.empty())
            System.out.println(stack.pop());
    }
}
// HashSet에 데이터가 들어가면서 중복된 수는 제거된다. 3,6,2,7 (순서무관)
// TreeSet에 들어가면서 값이 정렬된다. 2,3,6,7
// Stack에 TreeSet의 값이 저장되고 pop하면 가장 뒤에있는 수부터 나오므로
// 7,6,3,2 순으로 출력된다.


// 11-5. 다음에 제시된 Student클래스가 Comparable인터페이스를 구현하도록 변경해서
// 이름(name)이 기본 정렬기준이 되도록 하시오.

class Exercise11_5 {
    public static void main(String[] args){
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("홍길동",1,1,100,100,100));
        list.add(new Student("남궁성",1,2,90,70,80));
        list.add(new Student("김자바",1,3,80,80,90));
        list.add(new Student("이자바",1,4,70,90,70));
        list.add(new Student("안자바",1,5,60,100,80));

        Collections.sort(list);
        Iterator it = list.iterator();

        while(it.hasNext())
            System.out.println(it.next());
    }
}


// 11-6. 다음의 코드는 성적평균의 범위별로 학생 수를 세기 위한 것이다.
// TreeSet이 학생들의 평균을 기준으로 정렬하도록 compare(Object o1, Object o2)와 평균점수의 범위를 주면
// 해당 범위에 속한 학생의 수를 반환하는 getGroupCount()를 완성하라.

class Exercise11_6 {
    static int getGroupCount(TreeSet tset, int from, int to){
        Student s1 = new Student("",1,1,from,from,from);
        Student s2 = new Student("",1,1,to,to,to);
        return tset.subSet(s1, s2).size();
    }
    public static void main(String[] args) {
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(!(o1 instanceof Student && o2 instanceof Student))
                    return -1;
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return (int)(s1.getAverage() - s2.getAverage());
            }
        });

        set.add(new Student("홍길동",1,1,100,100,100));
        set.add(new Student("남궁성",1,2,90,70,80));
        set.add(new Student("김자바",1,3,80,80,90));
        set.add(new Student("이자바",1,4,70,90,70));
        set.add(new Student("안자바",1,5,60,100,80));

        Iterator it = set.iterator();

        while (it.hasNext())
            System.out.println(it.next());

        System.out.println("[60~69] : "+getGroupCount(set,60,70));
        System.out.println("[70~79] : "+getGroupCount(set,70,80));
        System.out.println("[80~89] : "+getGroupCount(set,80,90));
        System.out.println("[90~100] : "+getGroupCount(set,90,101));
    }
}   // End of Exercise11_6


// 11-7. 다음에 제시된 BanNoAscending클래스를 완성하여, ArrayList에 담긴 Student인스턴스들이
// 반(ban)과 번호(no)로 오름차순 정렬되게 하시오.(반이 같은 경우 번호를 비교해서 정렬한다.)

class Exercise11_7 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("김자바",1,2,80,80,90));

        Collections.sort(list, new BanNOAscending());

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
} // end of Exercise11_7 class

class BanNOAscending implements Comparator {
    public int compare(Object o1, Object o2){
        if(!(o1 instanceof Student && o2 instanceof  Student))
            return -1;
        Student s1 = (Student) o1;
        Student s2 = (Student) o2;

        return s1.ban != s2.ban ? s1.ban - s2.ban : s1.no - s2.no;
    }
}


// 11-8. 문제11-7의 Student클래스에 총점(total)과 전교등수(schoolRank)를 저장하기 위한 인스턴스변수를 추가하였다.
// Student클래스의 기본정렬을 이름(name)이 아닌 총점(total)을 기준으로 한 내림차순으로 변경한 다음, 총점을 기준으로
// 각 학생의 전교등수 를 계산하고 전교등수를 기준으로 오름차순 정렬하여 출력하시오.


class Exercise11_8 {
    public static void calculateSchoolRank(List list){
        Collections.sort(list);

        int prevRank = -1;
        int prevTotal = -1;
        int length = list.size();

        for (int i = 0; i < length; i++) {
            Student s = (Student) list.get(i);
            if(s.total == prevTotal)
                s.schoolRank = prevRank;
            else
                s.schoolRank = i + 1;
            prevRank = s.schoolRank;
            prevTotal = s.total;
        }

    }
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("김자바",1,2,80,80,90));

        calculateSchoolRank(list);

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}


// 11-9. 문제11-8의 Student클래스에 반등수(classRank)를 저장하기 위한 인스턴스변수 를 추가하였다.
// 반등수를 계산하고 반과 반등수로 오름차순 정렬하여 결과를 출력하시오. (1)~(2)에 알맞은 코드를 넣어 완성하시오.

class Exercise11_9 {
    public static void calculateClassRank(List list) {
        Collections.sort(list,new ClassTotalComparator());

        int prevRank = -1;
        int prevBan = -1;
        int prevtotal = -1;
        int length = list.size();

        int classRank = 1;

        for (int i = 0; i < length; i++) {
            Student s = (Student) list.get(i);
            if(s.ban == prevBan){
                if(s.total == prevtotal)
                    s.classRank = prevRank;
                else
                    s.classRank = classRank + 1;
                prevRank = s.classRank;
            } else{
                s.classRank = 1;
                prevRank = 1;
                classRank = 0;
            }
            prevtotal = s.total;
            prevBan = s.ban;
            classRank++;
        }
    }
    public static void calculateSchoolRank(List list){
        Collections.sort(list);

        int prevRank = -1;
        int prevTotal = -1;
        int length = list.size();

        for (int i = 0; i < length; i++) {
            Student s = (Student) list.get(i);
            if(s.total == prevTotal)
                s.schoolRank = prevRank;
            else
                s.schoolRank = i + 1;
            prevRank = s.schoolRank;
            prevTotal = s.total;
        }

    }
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("김자바",1,2,80,80,90));

        calculateSchoolRank(list);
        calculateClassRank(list);

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}

class ClassTotalComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof Student && o2 instanceof Student))
            return -1;
        Student s1 = (Student) o1;
        Student s2 = (Student) o2;
        if(s1.ban == s2.ban)
            return s2.total - s1.total;
        else
            return s1.ban - s2.ban;
    }
}
class Student implements Comparable{
    String name;
    int ban;
    int no;
    int kor,eng,math;

    int total;
    int schoolRank;
    int classRank;

    public Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;

        total = kor + eng + math;
    }

    int getTotal(){
        return kor + eng + math;
    }
    float getAverage() {
        return (int)((getTotal()/3f)*10 + 0.5)/10f;
    }

    public String toString(){
        return name + "," + ban + "," + no + "," + kor + "," + eng + "," +
                math + "," + getTotal() + "," + getAverage() + "," + schoolRank + "," + classRank;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Student))
            return -1;
        int s = ((Student) o).total;
        return s - total;
    }
} // End of Student class
