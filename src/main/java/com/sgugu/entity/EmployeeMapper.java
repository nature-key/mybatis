package com.sgugu.entity;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    public void addEmp(Employee employee);

    public void delEmp(Integer id);

    public void updateEmp(@Param("lastName") String lastName, @Param("id") int id);

    public Employee getEmployeeById(Integer id);

    public Employee getEmployeeByIdandName(@Param("id") Integer id, @Param("lastName") String lastName);

    public Employee getEmployee(Map<String, Object> map);

    public Employee getEmployeeThird(@Param("id") Integer id, @Param("lastName") String lastName,String email);

    public List<Employee> getEmployeeByLastName(String lastName);

    public Map<String,Object> getEmployeeByreturnMap(String lastName);
    @MapKey("id")
    public Map<Integer,Employee> getEmployeeByreturnEmp(String lastName);

}
