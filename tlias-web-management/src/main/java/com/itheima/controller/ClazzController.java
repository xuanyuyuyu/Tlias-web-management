package com.itheima.controller;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("新增班级" + clazz);
        clazzService.save(clazz);

        return Result.success();
    }

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);

        return Result.success(pageResult);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询id：{}",id);
        Clazz c = clazzService.selectById(id);

        return Result.success(c);
    }
    @GetMapping("/list")
    public Result selectAll(){
        log.info("查询所有");
        List<Clazz> clazzList = clazzService.selectAll();

        return Result.success(clazzList);
    }
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除班级:{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
