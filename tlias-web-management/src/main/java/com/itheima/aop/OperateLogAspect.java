package com.itheima.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.util.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Resource
    private OperateLogMapper operateLogMapper;

    @Resource
    private ObjectMapper objectMapper; // 用于序列化参数和返回值

    /**
     * 切入 com.itheima.controller 包下所有增删改的方法
     */
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 1. 执行目标方法
        Object result = null;
        Throwable throwable = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable ex) {
            throwable = ex;
            throw ex;
        } finally {
            long end = System.currentTimeMillis();
            long costTime = end - start;

            // 2. 收集日志数据
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();

            String paramsJson = null;
            try {
                paramsJson = objectMapper.writeValueAsString(joinPoint.getArgs());
            } catch (Exception e) {
                paramsJson = "[参数序列化失败]";
            }

            String returnJson = null;
            try {
                returnJson = objectMapper.writeValueAsString(result);
            } catch (Exception e) {
                returnJson = "[返回值序列化失败]";
            }

            // 假设当前登录用户ID可通过 ThreadLocal / SecurityContextHolder 获取
            Integer operateEmpId = getCurrentUserId();

            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(paramsJson);
            operateLog.setReturnValue(returnJson);
            operateLog.setCostTime(costTime);

            // 3. 保存日志
            try {
                operateLogMapper.insert(operateLog);
                log.info("操作日志已保存: {}", operateLog);
            } catch (Exception e) {
                log.error("保存操作日志失败", e);
            }
        }
    }

    /**
     * 获取当前操作用户ID
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
