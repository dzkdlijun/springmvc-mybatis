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
    private Integer id;
    @Column(name = "departmentName")
    private String departmentName;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
