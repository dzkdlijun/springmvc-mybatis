package com.leeo.springdata.domain;

import javax.persistence.*;

/**
 * Created by lijun on 2016/5/23.
 */
@Table(name = "department")
@Entity
public class Department extends BaseDomain{

    @Column(name = "departmentName")
    private String departmentName;

    @Version
    private Integer version;

//    @OneToMany(mappedBy = "department", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
//    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
