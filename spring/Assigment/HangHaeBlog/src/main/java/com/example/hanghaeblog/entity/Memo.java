package com.example.hanghaeblog.entity;

import com.example.hanghaeblog.dto.RequDto;
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
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    public Memo(RequDto requDto) {
        this.author = requDto.getAuthor();
        this.title = requDto.getTitle();
        this.content = requDto.getContent();
        this.password = requDto.getPassword();
    }

    public void update(RequDto requDto) {
        this.author = requDto.getAuthor();
        this.title = requDto.getTitle();
        this.content = requDto.getContent();
        this.password = requDto.getPassword();
    }
}
