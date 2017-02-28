package com.deepak.springapi.service;

import java.util.List;

import com.deepak.springapi.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findOne(String id);

	public Employee create(Employee employee);

	public Employee update(String id, Employee employee);

	public void delete(String id);
}