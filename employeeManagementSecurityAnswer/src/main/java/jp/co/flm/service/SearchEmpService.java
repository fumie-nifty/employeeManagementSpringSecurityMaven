package jp.co.flm.service;

import java.util.List;

import jp.co.flm.entity.Employee;

public interface SearchEmpService {

	public Employee getEmployee(Integer employeeId);

	public List<Employee> getAllEmployee();

}
