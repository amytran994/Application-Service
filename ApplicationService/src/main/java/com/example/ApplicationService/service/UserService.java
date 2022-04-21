//package com.example.ApplicationService.service;
//
//
//import com.example.ApplicationService.dao.UserDAO;
//import com.example.ApplicationService.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class UserService {
//
//	private UserDAO userDAO;
//
//	@Autowired
//	@Qualifier("userHibernateDao")
//	public void setEmployeeJdbcDao(UserDAO userDAO) {
//		this.userDAO = userDAO;
//	}
//
//	@Transactional
//	public User getById(Integer id) {
//		return userDAO.getById(id);
//	}
//	@Transactional
//	public List<User> getAll() {
//		return userDAO.getAll();
//	}
//
//
//}
