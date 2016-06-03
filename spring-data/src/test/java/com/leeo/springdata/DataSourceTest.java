package com.leeo.springdata;

import com.leeo.springdata.domain.Department;
import com.leeo.springdata.domain.Employee;
import com.leeo.springdata.service.DepartmentService;
import com.leeo.springdata.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lijun on 2016/5/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DataSourceTest {
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeService employeeService = ctx.getBean(EmployeeService.class);
    private DepartmentService departmentService = ctx.getBean(DepartmentService.class);

    //    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testKeyWords() {
        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        List<Employee> employees = employeeService.findByNameLikeAndDepartment_NameLike("jj", "soft");
        System.out.println(employees);
    }

    @Test
    public void testKeyWork() {
        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        List<Employee> employees = employeeService.findByNameLike("%jj%");
        System.out.println(employees);
    }

    @Test
    public void testMaxId() {
        EmployeeService employeeService = ctx.getBean(EmployeeService.class);
        Employee employee = employeeService.getMaxIdEmployee();
        System.out.println(employee);
    }

    @Test
    public void testFindByParams() {
        List<Employee> employees = employeeService.findEmployeeByParams("lijun", "123@abc.com");
        System.out.println(employees);
    }

    @Test
    public void testFindByNaming() {
        List<Employee> employees = employeeService.findEmployeeByParameterNaming("lijun", "123@abc.com");
        System.out.println(employees);
    }

    @Test
    public void testCountBySql(){
        System.out.println(employeeService.countBySql());
    }

    @Test
    public void testUpdateEmployeeNameById(){
        employeeService.updateEmployeeNameById("lijun", 2l);
    }

    @Test
    public void testSaveEmployees(){
        List<Employee> employees = new ArrayList<Employee>();
        Department department = departmentService.find(1l);
        for(int i=0;i<10;i++){
            Employee employee = new Employee();
            employee.setName("lijun" + i);
            employee.setBirth(new Date());
            employee.setCreateTime(new Date());
            employee.setEmail(i + "@abc.com");
            employee.setDepartment(department);
            employees.add(employee);
        }

        List<Employee> saved = employeeService.saveEmployees(employees);
        System.out.println(saved);
    }

    @Test
    public void testSpecificationPage(){
        Page<Employee> employees = employeeService.findEmployeeBySpecification(1,5,5l);
        System.out.println(employees.getContent());
    }
}
