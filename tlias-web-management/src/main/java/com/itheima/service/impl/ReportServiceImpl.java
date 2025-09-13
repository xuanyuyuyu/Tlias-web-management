package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StuMapper stuMapper;
    @Override
    public JobOption getEmpJobData() {
        //获取数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();  //map: pos=教研主管，num=1

        //组装结果，返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> geEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        List<Map<String, Object>> studentDegreeData = stuMapper.getStudentDegreeData();
        return studentDegreeData;
    }

    @Override
    public ClazzOption getStudentCountData() {
        //获取数据
        List<Map<String,Object>> list = stuMapper.getStudentCountData();
        //组装结果
        List<Object> clazzList = list.stream().map(dataMap-> dataMap.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(dataMap-> dataMap.get("num")).toList();

        return new ClazzOption(clazzList,dataList);
    }


}
