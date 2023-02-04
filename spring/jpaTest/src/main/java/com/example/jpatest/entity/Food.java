package com.example.jpatest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter     // 자동으로 getter 메서드를 만들어줌. 개꿀이네 이거
@Entity
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성해줌
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "food", fetch = FetchType.EAGER)       // join에 사용한 food_id의 주인이 누구인지 지정
    private List<Orders> orders = new ArrayList<>();            // FetchType은 나중에 알려줌.

    public Food(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }
}
