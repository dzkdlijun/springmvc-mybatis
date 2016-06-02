package com.leeo.springdata.dao;

import com.leeo.springdata.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lijun on 2016/5/30.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Long>,JpaSpecificationExecutor<Employee> {

    /**
     * 缺点，方法名太长，且不易实现复杂查询
     * @param employeeName
     * @param departmentName
     * @return
     */
    List<Employee> findByNameLikeAndDepartmentDepartmentNameLike(String employeeName,String departmentName);

    List<Employee> findByNameLike(String name);

    /**
     * jpql实现
     */
    @Query("select e from Employee e where e.id=(select max(e2.id) from Employee e2)")
    Employee getMaxIdPerson();

    /**
     * jpql 传参,1:使用占位符
     */
    @Query("select e from Employee e where e.name = ?1 and e.email = ?2")
    List<Employee> findEmployeeByParam(String name,String email);

    /**
     * jpql 传参，2：命名参数
     */
    @Query("select e from Employee e where e.name = :name and e.email = :email")
    List<Employee> findEmployeeByParameterNaming(@Param("name")String name,@Param("email")String email);

    @Query("select e from Employee e where e.name like %?1% or e.email like %?2%")
    List<Employee> findEmployeeByParamLike(String name,String email);

    /**
     * 本地sql查询
     */
    @Query(value = "select count(id) from employee",nativeQuery = true)
    Integer countBySQL();

    /**
     * jpql 更新
     */
    @Modifying
    @Query("update Employee e set e.name = ?1 where e.id = ?2")
    void updateEmployeeNameById(String name,Long id);

}
