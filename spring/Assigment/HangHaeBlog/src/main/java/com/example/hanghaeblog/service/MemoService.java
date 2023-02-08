package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.RequestDto;
import com.example.hanghaeblog.dto.ResponseDto;
import com.example.hanghaeblog.entity.Memo;
import com.example.hanghaeblog.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public List<ResponseDto> getAllMemos() {
        List<Memo> byOrderByModifiedAtDesc = memoRepository.findByOrderByModifiedAtDesc();
        List<ResponseDto> mto = new ArrayList<>();
        for (Memo memo : byOrderByModifiedAtDesc) {
            mto.add(new ResponseDto(memo));
        }
        return mto;
    }

    public ResponseDto.id postMemo(RequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return new ResponseDto.id(memo.getId());
    }

    @Transactional
    public ResponseDto getMemo(Long id) {
        return new ResponseDto(checkIdValidation(id));
    }

    @Transactional
    public ResponseDto updateContent(RequestDto requestDto, Long id) {
        Memo memo = checkIdValidation(id);
        checkPasswordValidation(memo, requestDto);
        memo.update(requestDto);
        return new ResponseDto(memo);
    }

    @Transactional
    public ResponseDto.isSuccess deleteMemo(RequestDto requestDto, Long id) {
        ResponseDto t = new ResponseDto();
        Memo memo = checkIdValidation(id);
        checkPasswordValidation(memo, requestDto);
        memoRepository.deleteById(id);
        ResponseDto.isSuccess isSuccess = new ResponseDto.isSuccess(true);
        System.out.println(isSuccess);
        return isSuccess;
    }

    public Memo checkIdValidation(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id가 존재하지 않습니다.")
        );
    }

    public void checkPasswordValidation(Memo memo, RequestDto requestDto) {
        if(!memo.getPassword().equals(requestDto.getPassword()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }
}

// DTO 에 담아서 반환하는 이유?