package com.example.rqchallenge.controller;

import com.example.rqchallenge.model.request.EmployeeRequest;
import com.example.rqchallenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        var response = employeeService.getAllEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<?> getEmployeesByNameSearch(@PathVariable String searchString) {
        var response = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") Long id) {
        var response = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/highestSalary")
    public ResponseEntity<?> getHighestSalaryOfEmployees() {
        var response = employeeService.getHighestSalaryOfEmployees();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    public ResponseEntity<?> getTopTenHighestEarningEmployeeNames() {
        var response = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest request) {
        var response = employeeService.createEmployee(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id) {
        var response = employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(response);
    }
}
