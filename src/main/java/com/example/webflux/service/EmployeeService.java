package com.example.webflux.service;

import com.example.webflux.model.entity.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
public interface EmployeeService {

    void createEmp(Employee employee);

    Mono<Employee> findByEmpId(Integer id);

    Flux<Employee> findAllEmp();

    Mono<Employee> updateEmp(Employee employee);

    Mono<Void> deleteEmp(Integer id);
}
