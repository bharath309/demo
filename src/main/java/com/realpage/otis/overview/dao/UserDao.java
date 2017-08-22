package  com.realpage.otis.overview.dao;

import  com.realpage.otis.model.User;

public interface UserDao {
	public User loadUserByUserName(String userName) ;
}
