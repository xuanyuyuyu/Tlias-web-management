package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {

    List<Student> listByPage(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> list);

    void insert(Student student);

    @Select("select * from stu where id = #{id}")
    Student selectById(Integer id);

    void update(Student student);


    void updateViolation(Integer id, Integer score);


    @MapKey("id")
    List<Map<String, Object>> getStudentDegreeData();


    List<Map<String, Object>> getStudentCountData();
}
