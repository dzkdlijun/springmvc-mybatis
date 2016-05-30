package com.leeo.springdata.service;

import com.leeo.springdata.dao.EmployeeRepository;
import com.leeo.springdata.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lijun on 2016/5/30.
 */
@Service
public class EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> getPage(int pageNo,int pageSize){
        PageRequest pageable = new PageRequest(pageNo-1,pageSize);//zero-based page index
        return employeeRepository.findAll(pageable);
    }

    public Employee find(Long id){
        return employeeRepository.getOne(id);
    }

    @Transactional
    public Employee add(Employee employee){
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Employee employee){
        return employeeRepository.saveAndFlush(employee);
    }

    @Transactional
    public void delete(Long id){
        employeeRepository.delete(id);
    }
}
