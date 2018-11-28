package com.sgugu.entity;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {

    public void addEmp(Employee employee);
    public void delEmp(Integer id);
    public void updateEmp(@Param("lastName")String lastName, @Param("id")int id);
}
