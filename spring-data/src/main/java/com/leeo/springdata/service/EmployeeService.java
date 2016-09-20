package com.leeo.springdata.service;

import com.leeo.springdata.annotation.Intercept;
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
import java.util.ArrayList;
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

    @Intercept
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
        employeeRepository.updateEmployeeNameById(name, id);
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
    public Page<Employee> findEmployeeBySpecification(int pageNo,int pageSize, final Long id){
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
                Predicate predicate = cb.gt(path,id);
                return predicate;
            }
        };
//        return employeeRepository.findAll(specification, pageRequest);
//        return employeeRepository.findAll(buildSpecificationDynamically(id), pageRequest);
//        return employeeRepository.findAll(buildSpecificationByCriteriaQuery(id), pageRequest);
        return employeeRepository.findAll(buildSpecificationWithUnion("%lijun%", "service", id), pageRequest);
    }

    /**
     * 动态拼接Specification查询语句
     */
    private Specification<Employee> buildSpecificationDynamically(final Long minId){
        return new Specification<Employee>() {
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.like(root.get("name").as(String.class),"%lijun%"));
                list.add(cb.gt(root.get("id").as(Long.class), minId));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        };
    }

    /**
     * 使用CriteriaQuery组装Specification查询语句,能在动态拼接的基础上增加如排序和分组等其他功能
     */
    private Specification<Employee> buildSpecificationByCriteriaQuery(final Long minId){
        return new Specification<Employee>() {
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate1 = cb.like(root.get("name").as(String.class),"%lijun%");
                Predicate predicate2 = cb.gt(root.get("id").as(Long.class), minId);
                Predicate predicate3 = cb.equal(root.get("id").as(Long.class), 2l);
                //把predicate应用到CriteriaQuery中，因为还可以给CriteriaQuery添加其他功能，比如排序、分组啥的
                query.where(cb.and(predicate1, cb.or(predicate2, predicate3)));
                //添加排序功能
                query.orderBy(cb.desc(root.get("id").as(Long.class)));
                return query.getRestriction();
            }
        };
    }

    /**
     * 多表联合查询
     */
    private Specification<Employee> buildSpecificationWithUnion(final String employeeName, final String departmentName, final Long minId){
        return new Specification<Employee>() {
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<Predicate>();
                Predicate predicate1 = cb.equal(root.join("department").get("departmentName").as(String.class),departmentName);//department为表名
                Predicate predicate2 = cb.gt(root.get("id").as(Long.class), minId);
                Predicate predicate3 = cb.like(root.get("name").as(String.class),employeeName);
                predicate.add(predicate1);
                predicate.add(predicate2);
                predicate.add(predicate3);
                Predicate[] predicates = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(predicates));
            }
        };
    }

    /**
     * EntityManager的使用
     */

}
