package com.example.rqchallenge.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {

    private String employeeName;
    private Integer employeeSalary;
    private Integer employeeAge;
    private String profileImage;

}
