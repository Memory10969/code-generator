package com.wbb.generator.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbb.generator.example.entity.User;
import com.wbb.generator.example.service.IUserService;

import com.wbb.generator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<User> users = userService.selectAll();
            result = Result.successResult(users);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectById")
    public Result selectById(String userId) {
        Result result;
        try {
            User user = userService.selectById(userId);
            result = Result.successResult(user);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(User user) {
        Result result;
        try {
            List<User> users = userService.select(user);
            result = Result.successResult(users);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(int pageNum,int pageSize, User user) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<User> users = userService.selectPage(page, user);
            result = Result.successResult(users);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/insert")
    public Result insert(User user) {
        Result result;
        try {
            int insert = userService.insert(user);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(User user) {
        Result result;
        try {
            int insert = userService.update(user);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/deleteById")
    public Result deleteById(String id) {
        Result result;
        try {
            int insert = userService.deleteById(id);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
