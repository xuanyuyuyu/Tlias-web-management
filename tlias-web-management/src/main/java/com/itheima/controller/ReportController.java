package com.itheima.controller;


import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    /**
     * 统计员工职位人数
     * @return
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getGenderData(){
        log.info("统计员工性别");
        List<Map<String,Object>> genderList = reportService.geEmpGenderData();

        return Result.success(genderList);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历");
        List<Map<String,Object>> studentDegree = reportService.getStudentDegreeData();
        return  Result.success(studentDegree);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数");
        ClazzOption clazzOption = reportService.getStudentCountData();

        return Result.success(clazzOption);
    }

}
