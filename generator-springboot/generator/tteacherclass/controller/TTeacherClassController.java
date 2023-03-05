package com.wbb.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbb.generator.tteacherclass.entity.TTeacherClass;
import com.wbb.generator.tteacherclass.service.ITTeacherClassService;
import com.wbb.generator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tteacherclass")
public class TTeacherClassController {
    @Autowired
    ITTeacherClassService tTeacherClassService;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<TTeacherClass> list = tTeacherClassService.selectAll();
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam String id) {
        Result result;
        try {
            TTeacherClass tTeacherClass = tTeacherClassService.selectById(id);
            result = Result.successResult(tTeacherClass);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(TTeacherClass tTeacherClass) {
        Result result;
        try {
            List<TTeacherClass> list = tTeacherClassService.select(tTeacherClass);
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam  int pageNum,@RequestParam  int pageSize,TTeacherClass tTeacherClass) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<TTeacherClass> list = tTeacherClassService.selectPage(page,tTeacherClass);
            long count = tTeacherClassService.count(tTeacherClass);
            Map map = new HashMap();
            map.put("total",count);
            map.put("list",list);
            result = Result.successResult(map);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody TTeacherClass tTeacherClass) {
        Result result;
        try {
            int insert = tTeacherClassService.insert(tTeacherClass);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody TTeacherClass tTeacherClass) {
        Result result;
        try {
            int insert = tTeacherClassService.update(tTeacherClass);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody TTeacherClass tTeacherClass) {
        Result result;
        try {
            int insert = tTeacherClassService.updateById(tTeacherClass);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/deleteById")
    public Result deleteById(@RequestParam String id) {
        Result result;
        try {
            int insert = tTeacherClassService.deleteById(id);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody String[] ids) {
        Result result;
        try {
            int delete = tTeacherClassService.deleteByIds(ids);
            result = Result.successResult(delete);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
