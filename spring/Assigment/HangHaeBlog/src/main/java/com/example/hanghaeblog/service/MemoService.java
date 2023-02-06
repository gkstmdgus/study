package com.example.hanghaeblog.service;

import com.example.hanghaeblog.dto.MemoDto;
import com.example.hanghaeblog.entity.Memo;
import com.example.hanghaeblog.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;

    public List<Memo> getAllMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Memo postMemo(MemoDto memoDto) {
        Memo memo = new Memo(memoDto);
        memoRepository.save(memo);
        return memo;
    }
}
