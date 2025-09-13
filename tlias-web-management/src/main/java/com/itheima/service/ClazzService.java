package com.itheima.service;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    void save(Clazz clazz);

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void update(Clazz clazz);

    Clazz selectById(Integer id);

    List<Clazz> selectAll();
}
