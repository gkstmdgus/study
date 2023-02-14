package com.example.hanghaeblog.controller;

import com.example.hanghaeblog.dto.RequestDto;
import com.example.hanghaeblog.dto.ResponseDto;
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
    public List<ResponseDto> searchAll() {
        return memoService.getAllMemos();
    }

    @PostMapping("/post")
    public ResponseDto.id post(@RequestBody RequestDto requestDto) {
        return memoService.postMemo(requestDto);
    }

    @GetMapping("/post/{id}")
    public ResponseDto getContent(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    @PutMapping("/post/{id}")
    @ResponseBody
    public ResponseDto updateContent(@PathVariable Long id, @RequestBody RequestDto requestDto) {
        return memoService.updateContent(requestDto, id);
    }

    @DeleteMapping("/post/{id}")
    public ResponseDto.isSuccess deleteContent(@RequestBody RequestDto requestDto, @PathVariable Long id) {
        return memoService.deleteMemo(requestDto, id);
    }


}
