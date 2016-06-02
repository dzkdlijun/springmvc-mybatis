package com.leeo.springdata.service;

import com.leeo.springdata.dao.EmployeeRepository;
import com.leeo.springdata.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

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

    public Page<Employee> getPageBySort(int pageNo,int pageSize){
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC,"name");
        Sort sort = new Sort(order1,order2);
        PageRequest pageRequest = new PageRequest(pageNo-1,pageSize,sort);
        return employeeRepository.findAll(pageRequest);
    }

    public Employee find(Long id){
        return employeeRepository.getOne(id);
    }

    public List<Employee> findByNameLikeAndDepartment_NameLike(String employeeName,String departmentName){
        return employeeRepository.findByNameLikeAndDepartmentDepartmentNameLike(employeeName, departmentName);
    }

    public List<Employee> findByNameLike(String name){
        return employeeRepository.findByNameLike(name);
    }

    public Employee getMaxIdEmployee(){
        return employeeRepository.getMaxIdPerson();
    }

    public List<Employee> findEmployeeByParams(String name,String eamil){
        return employeeRepository.findEmployeeByParam(name, eamil);
    }

    public List<Employee> findEmployeeByParameterNaming(String name,String email){
        return employeeRepository.findEmployeeByParameterNaming(name, email);
    }

    public Integer countBySql(){
        return employeeRepository.countBySQL();
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
    public void updateEmployeeNameById(String name,Long id){
        employeeRepository.updateEmployeeNameById(name,id);
    }

    @Transactional
    public void delete(Long id){
        employeeRepository.delete(id);
    }

    @Transactional
    public List<Employee> saveEmployees(List<Employee> employees){
        return employeeRepository.save(employees);
    }

    /**
     * 实现带查询条件的分页和排序
     * 调用JpaSpecificationExecutor的page<T>findAll(Specification<T> spec,Pageable pageable</T></T>)</>
     * Specification:封装了Jpa Criteria 查询的查询条件
     * Pageable:封装了请求分页的信息，如pageNo,pageSize,Sort
     */
    public Page<Employee> findEmployeeBySpecification(int pageNo,int pageSize){
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC,"name");
        Sort sort = new Sort(order1,order2);
        PageRequest pageRequest = new PageRequest(pageNo-1,pageSize,sort);
        Specification<Employee> specification = new Specification<Employee>() {
            /**
             *
             * @param root 查询实体的类
             * @param query 可以从中得到root对象，及告知JPA Criteria 要查询哪一个实体，还可以
             *              来添加查询条件，还可以结合EntityManger 对象得到最终查询的TypedQuery 对象
             * @param cb CriteriaBuilder对象，用于创建Criteria 相关对象的工厂，单日可以丛总获取
             *           Predicate 对象
             * @return Predicate 类型，代表一个查询条件
             */
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path = root.get("id");
                Predicate predicate = cb.gt(path,5);
                return predicate;
            }
        };
        return employeeRepository.findAll(specification,pageRequest);
    }
}
