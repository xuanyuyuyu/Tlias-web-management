package com.itheima.mapper;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
*
*  员工信息
*/
@Mapper
public interface EmpMapper {

    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")  //获取到生成中的主键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "        value (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 查找所有员工信息
     * @return
     */
    @Select("SELECT id, username, password, name, gender, phone, job, salary, image, entry_date AS entryDate, dept_id AS deptId, create_time AS createTime, update_time AS updateTime FROM emp")
    List<Emp> selectAll();

    /**
     * 根据ID批量删除员工的基本信息
     * @param ids
     */
    void deleteByIds(List<Integer> ids);


    /**
     * 根据ID查找员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);


    /**
     * 员工修改信息
     * @param emp
     */

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    @Select("SELECT id, username, name FROM emp WHERE username = #{username} AND password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
