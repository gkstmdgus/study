package com.example.hanghaeblog2;

import com.example.hanghaeblog2.dto.request.MemberRequestDto;
import com.example.hanghaeblog2.dto.request.PostRequestDto;
import com.example.hanghaeblog2.entity.Member;
import com.example.hanghaeblog2.entity.Post;
import com.example.hanghaeblog2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbinit();
    }

    // 초기값 자동 세팅
    @Component
    @Transactional
    @RequiredArgsConstructor
    private static class InitService {
        
        private final EntityManager em;
        
        public void dbinit() {
            Member member1 = new Member(new MemberRequestDto("bin1", "Bin12332", false));
            Member member2 = new Member(new MemberRequestDto("bin2", "Bin12332", false));
            Member member3 = new Member(new MemberRequestDto("bin3", "Bin12332", false));
            Member membera = new Member(new MemberRequestDto("bina", "Bin12332", true));

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(membera);

            em.flush();
            em.clear();

            PostRequestDto post1 = new PostRequestDto("bin1의 게시글", "내용11");
            PostRequestDto post2 = new PostRequestDto("bin2의 게시글", "내용22");
            PostRequestDto post3 = new PostRequestDto("bin3의 게시글", "내용33");
            PostRequestDto posta = new PostRequestDto("bina의 게시글", "내용44");

            em.persist(new Post(post1,member1));
            em.persist(new Post(post1,member1));
            em.persist(new Post(post2,member2));
            em.persist(new Post(post2,member2));
            em.persist(new Post(post3,member3));
            em.persist(new Post(post3,member3));
            em.persist(new Post(posta,membera));
            em.persist(new Post(posta,membera));

            em.flush();
            em.clear();

        }
    }
}
