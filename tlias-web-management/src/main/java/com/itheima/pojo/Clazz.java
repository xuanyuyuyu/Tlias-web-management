package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Clazz {
    private Integer id;
    private String name;
    private String room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String status;
    private Integer masterId;  //班主任
    private Integer subject;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String masterName;
}
