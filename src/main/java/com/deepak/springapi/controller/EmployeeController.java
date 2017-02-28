package com.deepak.springapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.springapi.constants.URI;
import com.deepak.springapi.entity.Employee;
import com.deepak.springapi.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = URI.EMPLOYEES)
@Api(tags = "employees")
public class EmployeeController {

	private EmployeeService service;

	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find All Employees", notes = "Returns a list of employees in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public List<Employee> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = URI.ID)
	@ApiOperation(value = "Find Employee by Id", notes = "Returns a employee by id if it exists in the app")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Employee findOne(@PathVariable("id") String id) {
		return service.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Employee", notes = "Creates a employee in the app with unique email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Employee create(@RequestBody Employee employee) {
		return service.create(employee);
	}

	@RequestMapping(method = RequestMethod.PUT, value = URI.ID)
	@ApiOperation(value = "Update Employee", notes = "Updates an existing employee")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	public Employee update(@PathVariable("id") String id, @RequestBody Employee employee) {
		return service.update(id, employee);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error"), })
	@ApiOperation(value = "Delete Employee", notes = "Deletes an existing employee")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}