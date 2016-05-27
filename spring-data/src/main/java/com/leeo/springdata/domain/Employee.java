package com.leeo.springdata.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lijun on 2016/5/23.
 */
@Table(name = "employee")
@Entity
public class Employee {

    @GeneratedValue
    @Column(name = "id",nullable = false)
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "createTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
