package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.RequDto;
import com.example.hanghaeblog.dto.RespDto;
import com.example.hanghaeblog.entity.Memo;
import com.example.hanghaeblog.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public List<RespDto> getAllMemos() {
        List<Memo> byOrderByModifiedAtDesc = memoRepository.findByOrderByModifiedAtDesc();
        List<RespDto> mto = new ArrayList<>();
        for (Memo memo : byOrderByModifiedAtDesc) {
            mto.add(new RespDto(memo));
        }
        return mto;
    }

    public Long postMemo(RequDto requDto) {
        Memo memo = new Memo(requDto);
        memoRepository.save(memo);
        return memo.getId();
    }

    @Transactional
    public RespDto getMemo(Long id) {
        return new RespDto(checkIdValidation(id));
    }

    @Transactional
    public Long updateContent(RequDto requDto, Long id) {
        Memo memo = checkIdValidation(id);
        checkPasswordValidation(memo,requDto);
        memo.update(requDto);
        return memo.getId();
    }

    @Transactional
    public String deleteMemo(RequDto requDto, Long id) {
        Memo memo = checkIdValidation(id);
        checkPasswordValidation(memo,requDto);
        memoRepository.deleteById(id);
        return "Success";
    }

    public Memo checkIdValidation(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id가 존재하지 않습니다.")
        );
    }

    public void checkPasswordValidation(Memo memo, RequDto requDto) {
        if(!memo.getPassword().equals(requDto.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}

// DTO 에 담아서 반환하는 이유?