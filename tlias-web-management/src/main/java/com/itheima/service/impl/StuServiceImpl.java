package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper studentMapper;
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {

        //设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());

        List<Student> studentList = studentMapper.listByPage(studentQueryParam);

        Page<Student> p = (Page<Student>) studentList;

        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void deletes(List<Integer> list) {
        studentMapper.deleteByIds(list);
    }

    @Override
    public void save(Student student) {
        student.setViolationCount(0);
        student.setViolationScore(0);
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student selectById(Integer id) {
        Student student = studentMapper.selectById(id);
        return student;
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateViolation(id,score);
    }
}
