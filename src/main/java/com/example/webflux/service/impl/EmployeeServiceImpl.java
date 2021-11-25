package com.example.webflux.service.impl;

import com.example.webflux.dao.EmployeeRepository;
import com.example.webflux.model.entity.Employee;
import com.example.webflux.service.EmployeeService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public void createEmp(Employee employee) {
        employeeRepo.save(employee).subscribe();
    }

    @Override
    public Mono<Employee> findByEmpId(Integer id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Flux<Employee> findAllEmp() {
        return employeeRepo.findAll();
    }

    @Override
    public Mono<Employee> updateEmp(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Mono<Void> deleteEmp(Integer id) {
        return employeeRepo.deleteById(id);
    }
}
