package com.itheima.util;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "aliyun.oss")
@Component
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;

}
