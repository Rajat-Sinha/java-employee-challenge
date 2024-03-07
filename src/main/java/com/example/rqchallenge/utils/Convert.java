package com.example.rqchallenge.utils;

import com.example.rqchallenge.entity.EmployeeData;
import com.example.rqchallenge.model.Employee;
import org.springframework.stereotype.Component;

@Component
public final class Convert {

    public static Employee convertEntityToObjectEmployee(EmployeeData e) {
        return new Employee(e.getId(), e.getEmployeeName(), e.getEmployeeSalary(), e.getEmployeeAge(), e.getProfileImage());
    }

}
