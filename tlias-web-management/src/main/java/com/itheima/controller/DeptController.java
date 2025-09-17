package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/*
*   部门管理
*
* */

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    private final DeptService deptService;
    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    
    @GetMapping
    public Result list(){
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();

        return Result.success(deptList);
    }

    //一旦声明了@RequestParam，该参数在请求时必须传递，如果不传递将会报错
    @Log
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        //System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：" + id);
        deptService.deleteById(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加部门：" + dept);
        log.info("添加部门：" + dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：" + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门：" + dept);
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
