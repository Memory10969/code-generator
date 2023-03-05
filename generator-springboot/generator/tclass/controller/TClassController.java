package com.wbb.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wbb.generator.tclass.entity.TClass;
import com.wbb.generator.tclass.service.ITClassService;
import com.wbb.generator.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tclass")
public class TClassController {
    @Autowired
    ITClassService tClassService;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<TClass> list = tClassService.selectAll();
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
            TClass tClass = tClassService.selectById(id);
            result = Result.successResult(tClass);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(TClass tClass) {
        Result result;
        try {
            List<TClass> list = tClassService.select(tClass);
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam  int pageNum,@RequestParam  int pageSize,TClass tClass) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<TClass> list = tClassService.selectPage(page,tClass);
            long count = tClassService.count(tClass);
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
    public Result insert(@RequestBody TClass tClass) {
        Result result;
        try {
            int insert = tClassService.insert(tClass);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody TClass tClass) {
        Result result;
        try {
            int insert = tClassService.update(tClass);
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody TClass tClass) {
        Result result;
        try {
            int insert = tClassService.updateById(tClass);
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
            int insert = tClassService.deleteById(id);
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
            int delete = tClassService.deleteByIds(ids);
            result = Result.successResult(delete);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
