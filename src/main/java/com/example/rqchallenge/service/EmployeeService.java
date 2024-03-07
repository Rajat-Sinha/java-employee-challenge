package com.example.rqchallenge.service;


import com.example.rqchallenge.entity.EmployeeData;
import com.example.rqchallenge.exception.DataNotFoundException;
import com.example.rqchallenge.exception.ParameterMissingException;
import com.example.rqchallenge.model.Employee;
import com.example.rqchallenge.model.Response;
import com.example.rqchallenge.model.request.EmployeeRequest;
import com.example.rqchallenge.repository.IEmployeeRepository;
import com.example.rqchallenge.utils.Convert;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    @Autowired
    IEmployeeRepository iEmployeeRepository;

    public Response getAllEmployees() {

        List<Employee> employees = new ArrayList<>();
        var employeeData = iEmployeeRepository.findAll();
        if(ObjectUtils.isNotEmpty(employeeData)) {
            employees = employeeData.stream().map(Convert::convertEntityToObjectEmployee).collect(Collectors.toList());
        }

        return new Response("success", employees);
    }

    public Response getEmployeesByNameSearch(String name) {
        List<Employee> employees = new ArrayList<>();

        var employeeData = iEmployeeRepository.findByEmployeeNameContainingIgnoreCase(name);
        if(ObjectUtils.isNotEmpty(employeeData)) {
            employees = employeeData.stream().map(Convert::convertEntityToObjectEmployee).collect(Collectors.toList());
        }

        return new Response("success", employees);
    }

    public Response getEmployeeById(Long id) {

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        var employeeData = iEmployeeRepository.findById(id).orElse(null);
        if(ObjectUtils.isEmpty(employeeData)) {
            throw new DataNotFoundException("Employee not found");
        }

        return new Response("success", Convert.convertEntityToObjectEmployee(employeeData));
    }

    public Response getHighestSalaryOfEmployees() {

        var employeeData = iEmployeeRepository.findFirstByOrderByEmployeeSalaryDesc();
        if(ObjectUtils.isEmpty(employeeData)) {
            throw new DataNotFoundException("No employee found");
        }

        int highestSalary = employeeData.getEmployeeSalary();
        return new Response("success", highestSalary);
    }

    public Response getTopTenHighestEarningEmployeeNames() {
        var employeeData = iEmployeeRepository.findFirst10ByOrderByEmployeeSalaryDesc();
        if(ObjectUtils.isEmpty(employeeData)) {
            throw new DataNotFoundException("No employee found");
        }

        var employeeNames = employeeData.stream()
                .map(EmployeeData::getEmployeeName)
                .collect(Collectors.toList());
        return new Response("success", employeeNames);
    }

    public Response createEmployee(EmployeeRequest request) {

        if(ObjectUtils.isEmpty(request.getEmployeeName())) {
            throw new ParameterMissingException("Employee name is missing");
        }

        if(ObjectUtils.isEmpty(request.getEmployeeSalary())) {
            throw new ParameterMissingException("Employee Salary is missing");
        }

        if(ObjectUtils.isEmpty(request.getEmployeeAge())) {
            throw new ParameterMissingException("Employee age is missing");
        }

        if(ObjectUtils.isEmpty(request.getProfileImage())) {
            throw new ParameterMissingException("Profile age is missing");
        }

        var name = request.getEmployeeName();
        var salary = request.getEmployeeSalary();
        var age = request.getEmployeeAge();
        var profileImage = request.getProfileImage();

        var employee = new EmployeeData(name, salary, age, profileImage);
        var employeeS = iEmployeeRepository.save(employee);

        return new Response("success", Convert.convertEntityToObjectEmployee(employeeS));
    }

    public Response deleteEmployeeById(Long id) {

        if(ObjectUtils.isEmpty(id)) {
            throw new ParameterMissingException("Id is missing");
        }

        var employeeData = iEmployeeRepository.findById(id).orElse(null);
        if(ObjectUtils.isEmpty(employeeData)) {
            throw new DataNotFoundException("Employee not found");
        }

        iEmployeeRepository.deleteById(id);
        return new Response("success", "Employee with ID " + id + " deleted successfully");
    }

}
