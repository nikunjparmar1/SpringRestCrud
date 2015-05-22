package com.beingjavaguys.dao;

import com.beingjavaguys.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Configurable(autowire = Autowire.BY_NAME)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml","classpath:hibernate.cfg.xml"})
@WebAppConfiguration
@Transactional
public class EmployeeDaoTest{

	@Autowired
	private DataDao employeeDao;

	@Test
	public void testFindById() throws Exception {
		Employee emp = employeeDao.getEntityById(1);
		System.out.println("Employee name is :"+emp.getFirstName());

		Assert.assertEquals(emp.getFirstName(),"Nilesh");
	}

	@Test
	public void addEntity() throws Exception{
		Employee employee = new Employee();
//		employee.setId(null);
		employee.setFirstName("Nikunj");
		employee.setLastName("Parmar");
		employee.setEmail("nikunj.parmar@sanelib.com");
		employee.setPhone("5236412");

		employeeDao.addEntity(employee);
	}
}
