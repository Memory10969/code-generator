package com.wbb.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbb.generator.tstudent.entity.TStudent;
import com.wbb.generator.tstudent.service.ITStudentService;
import com.wbb.generator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tstudent")
public class TStudentController {
    @Autowired
    ITStudentService tStudentService;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<TStudent> list = tStudentService.selectAll();
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
            TStudent tStudent = tStudentService.selectById(id);
            result = Result.successResult(tStudent);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(TStudent tStudent) {
        Result result;
        try {
            List<TStudent> list = tStudentService.select(tStudent);
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam  int pageNum,@RequestParam  int pageSize,TStudent tStudent) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<TStudent> list = tStudentService.selectPage(page,tStudent);
            long count = tStudentService.count(tStudent);
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
    public Result insert(@RequestBody TStudent tStudent) {
        Result result;
        try {
            int insert = tStudentService.insert(tStudent);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody TStudent tStudent) {
        Result result;
        try {
            int insert = tStudentService.update(tStudent);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody TStudent tStudent) {
        Result result;
        try {
            int insert = tStudentService.updateById(tStudent);
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
            int insert = tStudentService.deleteById(id);
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
            int delete = tStudentService.deleteByIds(ids);
            result = Result.successResult(delete);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
