package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

// 멤버랑 팀의 데이터를 한 번에 받아오는 Dto
@Data
public class MemberTeamDto {

    private Long memberId;
    private String username;
    private int age;
    private Long teamId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, int age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
