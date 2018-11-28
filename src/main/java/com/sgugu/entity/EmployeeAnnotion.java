package com.sgugu.entity;

import org.apache.ibatis.annotations.Select;

public interface EmployeeAnnotion {
    @Select("select * from tbl_employee where id = #{id} ")
    public com.sgugu.entity.Employee getEmployeeById(Integer id);
}
