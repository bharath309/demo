package  com.realpage.otis.overview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  com.realpage.otis.model.User;
import  com.realpage.otis.overview.dao.UserDao;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User loadUserByUserName(String userName) {
		return userDao.loadUserByUserName(userName);
	}

}
