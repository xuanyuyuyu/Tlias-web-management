package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name;
    private Integer page = 1;
    private Integer pageSize = 10;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
