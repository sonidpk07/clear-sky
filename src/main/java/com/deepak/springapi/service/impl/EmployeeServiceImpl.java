package com.deepak.springapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepak.springapi.entity.Employee;
import com.deepak.springapi.exception.BadRequestException;
import com.deepak.springapi.exception.NotFoundException;
import com.deepak.springapi.repository.EmployeeRepository;
import com.deepak.springapi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;

	public EmployeeServiceImpl(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findOne(String id) {
		return repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Employee with id " + id + " does not exist"));
	}

	@Override
	@Transactional
	public Employee create(Employee employee) {
		Optional<Employee> mayExists = repository.findByEmail(employee.getEmail());
		if (mayExists.isPresent()) {
			throw new BadRequestException("Employee with email " + employee.getEmail() + " already exists");
		}
		return repository.create(employee);
	}

	@Override
	@Transactional
	public Employee update(String id, Employee employee) {
		repository.findOne(id).orElseThrow(() -> new NotFoundException("Employee with id " + id + " does not exist"));
		return repository.update(employee);
	}

	@Override
	@Transactional
	public void delete(String id) {
		Employee existing = repository.findOne(id)
				.orElseThrow(() -> new NotFoundException("Employee with id " + id + " does not exist"));
		repository.delete(existing);
	}
}