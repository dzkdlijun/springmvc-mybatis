package com.leeo.springdata.controller;

import com.leeo.springdata.domain.Department;
import com.leeo.springdata.domain.Employee;
import com.leeo.springdata.service.DepartmentService;
import com.leeo.springdata.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lijun on 2016/5/30.
 */
@Controller
@RequestMapping(value = "/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Page<Employee> list(@RequestParam(value = "pageNo", defaultValue = "1", required = false) int pageNo,
                               @RequestParam(value = "pageASize", required = false, defaultValue = "10") int pageSize) {
        return employeeService.getPage(pageNo, pageSize);
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Employee add(@RequestBody Employee employee,@RequestParam Long departId){
        Department department = departmentService.find(departId);
        employee.setDepartment(department);
        return employeeService.add(employee);
    }

}
