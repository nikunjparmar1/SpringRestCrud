package com.beingjavaguys.dao.Impl;

import com.beingjavaguys.dao.DataDao;
import com.beingjavaguys.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class DataDaoImpl implements DataDao {

	@Autowired
	SessionFactory sessionFactory;


	@Autowired
	private DataSource dataSource;


	Session session = null;
	Transaction tx = null;

	@Override
	public boolean addEntity(Employee employee) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(employee);
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public Employee getEntityById(Integer id) throws Exception {
		String sql = "select * from employee where id="+id;
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Employee employee = template.query(sql, new ResultSetExtractor<Employee>() {
			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				Employee emp = new Employee();
				while (rs.next()){
					emp.setId(rs.getInt("id"));
					emp.setFirstName(rs.getString("firstName"));
					emp.setLastName(rs.getString("lastName"));
					emp.setEmail(rs.getString("email"));
					emp.setPhone(rs.getString("phone"));
				}
				return emp;
			}
		});
		if(employee != null) {
			return employee;
		}else {return null;}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Employee> employeeList = session.createCriteria(Employee.class)
				.list();
		tx.commit();
		session.close();
		return employeeList;
	}



	@Override
	public boolean deleteEntity(Integer id)
			throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Employee.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

}
