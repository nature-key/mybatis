package com.sgugu.entity;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public List<Employee> getEmployeeInnerByInnerParam(Employee employee);

    public List<Employee> getEmployeeById(Employee employee);

    public List<Employee> getEmployeeByIdTrim(Employee employee);

    public List<Employee> getEmployeeByIdChosee(Employee employee);

    public void updateEmployee(Employee employee);

    public List<Employee> getListForEach(List<Integer> ids);

    public void addEmployee(@Param("emps") List<Employee> emps);
}
