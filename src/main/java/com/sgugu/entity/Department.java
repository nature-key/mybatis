package com.sgugu.entity;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable{
    private  Integer id;
    private  String departName;
    private List<Employee> emps;

    public Department(Integer id) {
        this.id = id;
    }

    public Department() {
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departName='" + departName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
