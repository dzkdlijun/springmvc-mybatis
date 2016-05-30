package com.leeo.springdata.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lijun on 2016/5/30.
 */
@MappedSuperclass
public class BaseDomain {
    @GeneratedValue
    @Column(name = "id",nullable = false)
    @Id
    private Long id;

    public BaseDomain() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
