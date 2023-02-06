package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.MemoDto;
import com.example.hanghaeblog.entity.Memo;
import com.example.hanghaeblog.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/searchAll")
    public List<Memo> searchAll() {
        return memoService.getAllMemos();
    }

    @PostMapping("/post")
    public Memo post(@RequestBody MemoDto memoDto) {
        return memoService.postMemo(memoDto);
    }
}
