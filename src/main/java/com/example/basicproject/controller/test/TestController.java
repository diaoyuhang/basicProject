package com.example.basicproject.controller.test;

import com.example.basicproject.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/search")
    public ResultDto<List<SearchValue>> search(String content) {

        List<SearchValue> res = new ArrayList<>();
        if (!"test".equals(content)) {

            res.add(new SearchValue(1, "披萨1", 300));
            res.add(new SearchValue(2, "披萨2", 300));
            res.add(new SearchValue(3, "披萨3", 300));
            res.add(new SearchValue(1, "披萨4", 300));
            res.add(new SearchValue(1, "披萨5", 300));
        }

        return ResultDto.createSuccess(res);
    }
}
