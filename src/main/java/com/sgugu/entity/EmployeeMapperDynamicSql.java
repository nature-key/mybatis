package com.sgugu.entity;

import java.util.List;

public interface EmployeeMapperDynamicSql {

    public List<Employee> getEmployeeById(Employee employee);
    public List<Employee> getEmployeeByIdTrim(Employee employee);
    public List<Employee> getEmployeeByIdChosee(Employee employee);
    public void updateEmployee(Employee employee);
}
