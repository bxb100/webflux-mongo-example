package com.example.webflux.service.impl;

import com.example.webflux.dao.EmployeeRepository;
import com.example.webflux.model.entity.Employee;
import com.example.webflux.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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


    public Mono<Employee> updateEmpWitheException(Employee employee) {
        int i = 1 / 0;
        return employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public Mono<Void> doStuffWithTransactional() {

        Employee testInsert = new Employee(999, "test", "test");
        return employeeRepo.save(testInsert)
                .then(employeeRepo.findById(999))
                .then();
//                .flatMap(e -> {
//                    e.setName("test2");
//                    return updateEmpWitheException(e);
//                });
    }
}
