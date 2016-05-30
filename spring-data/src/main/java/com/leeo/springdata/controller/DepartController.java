package com.leeo.springdata.controller;

import com.leeo.springdata.base.PageDomain;
import com.leeo.springdata.domain.Department;
import com.leeo.springdata.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lijun on 2016/5/30.
 */
@Controller
@RequestMapping(value = "/department/")
public class DepartController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public PageDomain<Department> list(@RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,@RequestParam(value = "pageSize",required = false,defaultValue = "1")int pageSize){
        return new PageDomain<Department>(departmentService.getPage(pageNo,pageSize));
    }

    @RequestMapping(value = "add",method = RequestMethod.POST)
    @ResponseBody
    public Department add(@RequestBody Department department){
        return departmentService.add(department);
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    @ResponseBody
    public void delete(@RequestParam Long id){
        departmentService.delete(id);
    }
}
