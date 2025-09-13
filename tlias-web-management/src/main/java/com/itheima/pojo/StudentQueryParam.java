    package com.itheima.pojo;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class StudentQueryParam {

        private Integer page = 1;
        private Integer pageSize = 10;
        private String name;              // 姓名
        private String no;                // 学号
        private Integer gender;           // 性别（1男 2女）
        private String phone;             // 电话
        private Integer degree;           // 学历/学位
        private Integer violationCount;   // 违规次数
        private Integer violationScore;   // 违规扣分
        private Integer clazzId;          // 班级ID
        private String clazzName;         // 班级名称

        private LocalDateTime updateTime; // 更新时间

    }
