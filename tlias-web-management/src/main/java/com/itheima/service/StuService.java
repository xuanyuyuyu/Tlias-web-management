package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StuService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void deletes(List<Integer> list);

    void save(Student student);

    Student selectById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Integer score);
}
