package com.example.webflux.controller;

import com.example.webflux.model.entity.Employee;
import com.example.webflux.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Xiaobo Bi (869384236@qq.com)
 */
@RestController
public class EmployController {

    private final EmployeeService employeeService;

    public EmployController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create/emp")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmp(@RequestBody Employee employee) {
        employeeService.createEmp(employee);
    }

    @GetMapping(value = "/get/all")
    @ResponseBody
    public Flux<Employee> findAll() {
        return employeeService.findAllEmp();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Mono<Employee>> findEmpById(@PathVariable("id") Integer id) {
        Mono<Employee> employee = employeeService.findByEmpId(id);
        return new ResponseEntity<>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/emp")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> update(@RequestBody Employee employee) {
        return employeeService.updateEmp(employee);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        employeeService.deleteEmp(id).subscribe();
    }

    @PostMapping("/tx")
    @ResponseStatus(HttpStatus.OK)
    public void tx() {
        employeeService.doStuffWithTransactional().subscribe();
        // 事务结束
        // int i = 1 / 0;
    }
}
