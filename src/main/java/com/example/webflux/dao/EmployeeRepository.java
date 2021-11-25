package com.example.webflux.dao;

import com.example.webflux.model.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {
}
