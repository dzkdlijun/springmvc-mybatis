package com.leeo.springdata.domain;

import javax.persistence.*;

/**
 * Created by lijun on 2016/5/23.
 */
@Table(name = "department")
@Entity
public class Department {

    @GeneratedValue
    @Column(name = "id",nullable = false)
    @Id
    private Integer id;
    @Column(name = "departmentName")
    private String departmentName;

//    @OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
//    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
