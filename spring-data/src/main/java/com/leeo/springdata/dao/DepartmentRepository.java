package com.leeo.springdata.dao;

import com.leeo.springdata.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by lijun on 2016/5/30.
 */
@Component
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
