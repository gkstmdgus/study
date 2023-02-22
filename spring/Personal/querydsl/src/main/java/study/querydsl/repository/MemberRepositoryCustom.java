package study.querydsl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.querydsl.dto.MemberCondition;
import study.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberCondition condition);
    Page<MemberTeamDto> searchPageSimple(MemberCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberCondition condition, Pageable pageable);
}
