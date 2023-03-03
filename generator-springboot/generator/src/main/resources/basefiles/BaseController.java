package ${packageName}.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageName}.${lowerCaseName}.entity.${className};
import ${packageName}.${lowerCaseName}.service.I${className}Service;
import ${resultPackageName}.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/${lowerCaseName}")
public class ${className}Controller {
    @Autowired
    I${className}Service ${camel}Service;

    @GetMapping("/selectAll")
    public Result selectAll() {
        Result result;
        try {
            List<${className}> list = ${camel}Service.selectAll();
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
            ${className} ${camel} = ${camel}Service.selectById(id);
            result = Result.successResult(${camel});
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/select")
    public Result select(@RequestBody ${className} ${camel}) {
        Result result;
        try {
            List<${className}> list = ${camel}Service.select(${camel});
            result = Result.successResult(list);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam  int pageNum,@RequestParam  int pageSize,${className} ${camel}) {
        Result result;
        try {
            IPage page = new Page(pageNum,pageSize);
            List<${className}> list = ${camel}Service.selectPage(page,${camel});
            long count = ${camel}Service.count(${camel});
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
    public Result insert(@RequestBody ${className} ${camel}) {
        Result result;
        try {
            int insert = ${camel}Service.insert(${camel});
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${className} ${camel}) {
        Result result;
        try {
            int insert = ${camel}Service.update(${camel});
            result = Result.successResult(insert);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/updateById")
    public Result updateById(@RequestBody ${className} ${camel}) {
        Result result;
        try {
            int insert = ${camel}Service.updateById(${camel});
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
            int insert = ${camel}Service.deleteById(id);
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
            int delete = ${camel}Service.deleteByIds(ids);
            result = Result.successResult(delete);
        }catch (Exception e){
            result = Result.errorResult(Result.SERVICE_ERROR_CODE,e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
