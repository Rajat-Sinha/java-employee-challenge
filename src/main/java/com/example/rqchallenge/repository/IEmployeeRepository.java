package com.example.rqchallenge.repository;

import com.example.rqchallenge.entity.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeData, Long> {

    List<EmployeeData> findByEmployeeNameContainingIgnoreCase(String name);
    EmployeeData findFirstByOrderByEmployeeSalaryDesc();
    List<EmployeeData> findFirst10ByOrderByEmployeeSalaryDesc();

}
