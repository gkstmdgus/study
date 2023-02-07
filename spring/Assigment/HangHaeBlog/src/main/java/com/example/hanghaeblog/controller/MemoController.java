package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.RequDto;
import com.example.hanghaeblog.dto.RespDto;
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

    @GetMapping("/post")
    public List<RespDto> searchAll() {
        return memoService.getAllMemos();
    }

    @PostMapping("/post")
    public Long post(@RequestBody RequDto requDto) {
        return memoService.postMemo(requDto);
    }

    @GetMapping("/post/{id}")
    public RespDto getContent(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/post/{id}")
    @ResponseBody
    public Long updateContent(@PathVariable Long id, @RequestBody RequDto requDto) {
        return memoService.updateContent(requDto, id);
    }

    @DeleteMapping("/post/{id}")
    public String deleteContent(@RequestBody RequDto requDto, @PathVariable Long id) {
        return memoService.deleteMemo(requDto, id);
    }
}
