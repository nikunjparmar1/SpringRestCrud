package com.beingjavaguys.dao;

import com.beingjavaguys.model.Employee;

import java.util.List;

public interface DataDao {

	public boolean addEntity(Employee employee) throws Exception;
	public Employee getEntityById(Integer id) throws Exception;
	public List<Employee> getEntityList() throws Exception;
	public boolean deleteEntity(Integer id) throws Exception;
}
