package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {


    /**
     * 添加班级
     * @param clazz
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")

    @Insert("insert into clazz (name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /**
     * 班级分页
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);


    /**
     * 删除班级
     * @param id
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 更新班级信息
     * @param clazz 班级对象
     */
    @Update("UPDATE clazz SET name = #{name}, room = #{room}, begin_date = #{beginDate}, " +
            "end_date = #{endDate}, master_id = #{masterId}, subject = #{subject}, " +
            "update_time = #{updateTime} WHERE id = #{id}")
    void update(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);


    List<Clazz> selectAll();
}
