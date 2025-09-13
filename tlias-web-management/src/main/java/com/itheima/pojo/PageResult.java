package com.itheima.pojo;


/*
*   分页结果封装类
*
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
