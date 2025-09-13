package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {


    /**
     * 统计员工信息
     * @return
     */
    JobOption getEmpJobData();

    List<Map<String, Object>> geEmpGenderData();

    List<Map<String, Object>> getStudentDegreeData();

    ClazzOption getStudentCountData();
}
