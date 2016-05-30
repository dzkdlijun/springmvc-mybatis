package com.leeo.springdata.dao;

import com.leeo.springdata.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lijun on 2016/5/30.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
