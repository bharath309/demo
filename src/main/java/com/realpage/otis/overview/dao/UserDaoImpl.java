package com.realpage.otis.overview.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import  com.realpage.otis.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier("mpfUiJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public User loadUserByUserName(String userName) {
		 return (User)jdbcTemplate.queryForObject("select mpfUserId, username,email,lastname,firstname,active, role.rolename as roleName from mpfuser join role on  mpfuser.roleid = role.roleid and username = ? ", new Object[]{userName},new BeanPropertyRowMapper(User.class));
	}

}
