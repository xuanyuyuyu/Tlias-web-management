package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;               // 学生ID
    private String name;              // 姓名
    private String no;                // 学号
    private Integer gender;           // 性别（1男 2女）
    private String phone;             // 电话
    private Integer degree;           // 学历/学位
    private String idCard;            // 身份证号
    private Integer isCollege;        // 是否在校（0否 1是）
    private String address;           // 地址
    private LocalDate graduationDate; // 毕业日期
    private Integer violationCount;   // 违规次数
    private Integer violationScore;   // 违规分数
    private Integer clazzId;          // 班级ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private String clazzName;         // 班级名称
}
