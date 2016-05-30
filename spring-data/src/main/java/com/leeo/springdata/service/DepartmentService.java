package com.leeo.springdata.service;

import com.leeo.springdata.dao.DepartmentRepository;
import com.leeo.springdata.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lijun on 2016/5/30.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department find(Long id) {
        return departmentRepository.getOne(id);
    }

    public Page<Department> getPage(int pageNo, int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize);//zero-based page index
        return departmentRepository.findAll(pageRequest);
    }

    @Transactional
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public Department update(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    @Transactional
    public void delete(Long id) {
        departmentRepository.delete(id);
    }
}
