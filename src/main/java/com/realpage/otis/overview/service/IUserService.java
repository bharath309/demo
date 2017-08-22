package  com.realpage.otis.overview.service;

import  com.realpage.otis.model.User;

public interface IUserService {

	public User loadUserByUserName(String userName);
	
}
