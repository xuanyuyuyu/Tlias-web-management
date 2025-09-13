package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpLogMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    /**
     * 添加员工
     * @param emp 员工对象
     */
    @Transactional(rollbackFor = {Exception.class}) //事务管理
    @Override
    public void save(Emp emp) {
        try {
            //保存员工的基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //保存员工的工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历集合，为empId复制
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"新增员工：" + emp);
            empLogService.insertLog(empLog);
        }


    }

    @Override
    public List<Emp> selectAll() {

        List<Emp> empList = empMapper.selectAll();

        return empList;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);

        //批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);


    }

    @Override
    public Emp getInfo(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    /**
     * 修改员工
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //根据id修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据id修改员工工作经历信息 先删再增
        //删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //新增工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }


    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码获取员工信息
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        //2.如果存在，封装登录信息
        if(e != null){
            log.info("登录成功，员工信息：{}",e);
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());

            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }
        //3.返回登录信息
        return null;
    }

}
