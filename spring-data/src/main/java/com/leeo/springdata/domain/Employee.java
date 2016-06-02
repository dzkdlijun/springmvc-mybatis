package com.leeo.springdata.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lijun on 2016/5/23.
 */
@Table(name = "employee")
@Entity
public class Employee extends BaseDomain{

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime",nullable = false,columnDefinition = "timestamp")
    private Date createTime;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
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

    public String toString(){
        return this.getId() + ":" + this.getName();
    }

}
