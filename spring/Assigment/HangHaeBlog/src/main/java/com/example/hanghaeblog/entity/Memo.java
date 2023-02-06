package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.MemoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Memo extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String password;

    public Memo(MemoDto memoDto) {
        this.name = memoDto.getName();
        this.title = memoDto.getTitle();
        this.description = memoDto.getDescription();
        this.password = memoDto.getPassword();
    }
}
