// 12-1. 클래스 Box가 다음과 같이 정의되어 있을 때, 다음 중 오류가 발생하는 문장은?
// 경고가 발생하는 문장은?

import java.util.ArrayList;

class Box<T>{
    T items;

    void setItems (T items) { this.items = items; }
    T getItems() { return items; }
}

// a. Box<Object> b = new Box<String>();
//b. Box<Object> b = (Object)new Box<String>();
// c. new Box<String>().setItem(new Object());
// d. new Box<String>().setItem("ABC");

// 풀이
// a. 오류 발생. 객체를 생성할 때는 지네릭 타입을 일치시켜야 한다. (와일드 카드 제외)
// b. 오류 발생. 위와 이유는 같다.
// c. 오류 발생. 선언된 지네릭 타입의 조상 타입은 불가능.
// d. 가능.


// 12-2. 지네릭 메서드 makeJuice()가 아래와 같이 정의되어 있을 때,
// 이 메서드를 올바 르게 호출한 문장을 모두 고르시오. (Apple과 Grape는 Fruit의 자손이라고 가정하자.)

//class Juicer {
//    static <T extends Furit> String makeJuice (FruitBox<T> box){
//        String tmp = "";
//        for (Fruit f : box.getList()) tmp += f + " ";
//        return tmp;
//    }
//}

// a. Juicer.<Apple>makeJuice(new FruitBox<Fruit>());
// b. Juicer.<Fruit>makeJuice(new FruitBox<Grape>());
// c. Juicer.<Fruit>makeJuice(new FruitBox<Fruit>());
// d. Juicer.makeJuice(new FruitBox<Apple>());
// e. Juicer.makeJuice(new FruitBox<Object>());

// 풀이
// a. T == apple. 같아야함.
// b. 가능. -> 불가능. T의 값을 정했기 때문에 같아야함.
// c. 가능.
// d. 가능.
// e. Object는 Fruit의 자손이 아니므로 불가능.


// 12-3. 다음 중 올바르지 않은 문장을 모두 고르시오.
//class Box<T extends  Fruits>{
//    T items;
//
//    void setItems(T items) { this.items = items; }
//    T getItems() { return items; }
//}

// a. Box<?> b = new Box();
// b. Box<?> b = new Box<>();
// c. Box<?> b = new Box<Object>();
// d. Box<Object> b = new Box<Fruit>();
// e. Box b = new Box<Fruit>();
// f. Box<? extends Fruit> b = new Box<Apple>();
// g. Box<? extends Object> b = new Box<? extends Fruit>();

// 풀이
// <?> 는 <? extends Objects>를 줄인 말이다.
// a. 불가능. -> 가능.
// b. 불가능. -> 가능.
// a,b가 가능한 이유는 Object는 Fruit의 자손은 아니지만 일단 조상이므로 생성은 가능. 하지만 제한 범위가
// Fruit과 그 자손이므로 Fruit 타입이 선언되었다고 생각하면 된다. 그렇기 때문에 c번에서 에러가 발생한다.
// c. 불가능.
// d. 불가능.
// e. 가능.
// f. 가능.
// g. 가능. -> 불가능. new 연산자는 타입이 명확해야함.


// 12-4.아래의 메서드는 두 개의 ArrayList를 매개변수로 받아서,
// 하나의 새로운 ArrayList로 병합하는 메서드이다. 이를 지네릭 메서드로 변경하시오.
//public static <T extends Product> ArrayList<T> merge(
//        ArrayList<T> list, ArrayList<T> list2
//){
//    ArrayList<T> newList = new ArrayList<>()(list);
//    newList.addAll(list2);
//
//    return newList;
//}


// 12-5. 아래는 예제7-3에 열거형 Kind와 Number를 새로 정의하여 적용한 것이다.
// (1)에 알맞은 코드를 넣어 예제를 완성하시오.
// (Math.random()을 사용했으므로 실행결과가 달라 질 수 있다.)

class DeckTest{
    public static void main(String[] args) {
        Deck d = new Deck();
        Card c = d.pick(0);
        System.out.println(c);

        d.shffle();
        c = d.pick(0);
        System.out.println(c);
    }
}

class Deck {
    final int CARD_NUM = Card.Kind.values().length;     // 4

    Card[] cardArr = new Card[CARD_NUM];
    Deck() {
        // cardArr에 카드를 넣어줘야 한다. (Card 객체로)
        cardArr[0] = new Card(Card.Kind.SPADE, Card.Number.ACE);
        cardArr[1] = new Card(Card.Kind.COLVER, Card.Number.EIGHT);
        cardArr[2] = new Card(Card.Kind.HEART, Card.Number.TWO);
        cardArr[3] = new Card(Card.Kind.DIAMOND, Card.Number.EIGHT);
    }

    Card pick(int index) {
        return cardArr[index];
    }

    Card pick(){
        int index = (int)(Math.random() * CARD_NUM);
        return pick(index);
    }

    void shffle() {
        for (int i = 0; i < cardArr.length; i++) {
            int r = (int)(Math.random() * CARD_NUM);
            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }
} // end of Deck class

class Card{
    enum Kind { COLVER, HEART, DIAMOND, SPADE }
    enum Number {
        ACE, TWO, THREE, FOUR, FIVE,
        SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING
    }

    Kind kind;
    Number number;

    Card(){
        this(Kind.SPADE, Number.ACE);
    }
    public Card(Kind kind, Number number) {
        this.kind = kind;
        this.number = number;
    }

    public String toString(){
        return "[" + kind.name() + "," + number.name() + "]";
    }
} // end of Card class



















