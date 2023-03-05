package com.wbb.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbb.generator.tteacher.entity.TTeacher;
import com.wbb.generator.tteacher.service.ITTeacherService;
import com.wbb.generator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tteacher")
public class TTeacherController {
    @Autowired
    ITTeacherService tTeacherService;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<TTeacher> list = tTeacherService.selectAll();
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
            TTeacher tTeacher = tTeacherService.selectById(id);
            result = Result.successResult(tTeacher);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(TTeacher tTeacher) {
        Result result;
        try {
            List<TTeacher> list = tTeacherService.select(tTeacher);
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam  int pageNum,@RequestParam  int pageSize,TTeacher tTeacher) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<TTeacher> list = tTeacherService.selectPage(page,tTeacher);
            long count = tTeacherService.count(tTeacher);
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
    public Result insert(@RequestBody TTeacher tTeacher) {
        Result result;
        try {
            int insert = tTeacherService.insert(tTeacher);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody TTeacher tTeacher) {
        Result result;
        try {
            int insert = tTeacherService.update(tTeacher);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody TTeacher tTeacher) {
        Result result;
        try {
            int insert = tTeacherService.updateById(tTeacher);
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
            int insert = tTeacherService.deleteById(id);
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
            int delete = tTeacherService.deleteByIds(ids);
            result = Result.successResult(delete);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
