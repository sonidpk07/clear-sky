package com.deepak.springapi.repository;

import java.util.List;
import java.util.Optional;

import com.deepak.springapi.entity.Employee;

public interface EmployeeRepository {

	public List<Employee> findAll();

	public Optional<Employee> findOne(String id);

	public Optional<Employee> findByEmail(String email);

	public Employee create(Employee employee);

	public Employee update(Employee employee);

	public void delete(Employee employee);
}