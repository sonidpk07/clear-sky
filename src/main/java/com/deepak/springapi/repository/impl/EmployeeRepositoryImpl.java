package com.deepak.springapi.repository.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.deepak.springapi.entity.Employee;
import com.deepak.springapi.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Employee> findAll() {
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
		return query.getResultList();
	}

	@Override
	public Optional<Employee> findByEmail(String email) {
		TypedQuery<Employee> query = em.createNamedQuery("Employee.findByEmail", Employee.class);
		query.setParameter("pEmail", email);
		List<Employee> employee = query.getResultList();
		if (!employee.isEmpty()) {
			return Optional.of(employee.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Employee> findOne(String id) {
		return Optional.ofNullable(em.find(Employee.class, id));
	}

	@Override
	public Employee create(Employee employee) {
		em.persist(employee);
		return employee;
	}

	@Override
	public Employee update(Employee employee) {
		return em.merge(employee);
	}

	@Override
	public void delete(Employee employee) {
		em.remove(employee);
	}
}