package com.hodo.practice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hodo.practice.common.R;

import lombok.AllArgsConstructor;

/**
 * @program: practiceproject
 * @Date: 2021/8/2 9:46
 * @Author: zb HD051735
 * @Description:
 */
@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController
{

	@PostMapping("/test")
    public R test()
    {
        return R.ok("成功！");
    }
}
