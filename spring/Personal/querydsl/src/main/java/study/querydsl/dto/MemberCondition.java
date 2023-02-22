package study.querydsl.dto;

import lombok.Data;

@Data
public class MemberCondition {

    private String username;
    private String teamName;
    // null 고려해서 Integer 사용
    private Integer ageGoe;
    private Integer ageLoe;
}
