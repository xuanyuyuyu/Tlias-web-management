package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Slf4j
@RestController
@RequestMapping("/students")
public class StuController {

    @Autowired
    private StuService stuService;

    /**
     * 分页查询
     * @param
     * @return
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询：{}",studentQueryParam);

        PageResult<Student> pageResult = stuService.page(studentQueryParam);

        return Result.success(pageResult);
    }

    /**
     * 批量删除学生
     * @param ids 格式(1,2,3)
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){

        //把字符串转为List
        String[] split = ids.split(",");
        List<Integer> list = new ArrayList<Integer>();
        for (String s : split) {
            list.add(Integer.parseInt(s));
        }
        log.info("批量删除学生：{}",list);
        stuService.deletes(list);

        return Result.success();
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学生：",student);
        stuService.save(student);
        return Result.success();
    }

    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据id查找学生：{}",id);
        Student student = stuService.selectById(id);

        return Result.success(student);
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("更新学生信息：{}",student);
        stuService.update(student);

        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Integer score){
        stuService.updateViolation(id,score);
        return Result.success();
    }

}
