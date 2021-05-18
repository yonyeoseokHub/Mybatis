package mybatis.service.user.impl;

import java.util.List;

import mybatis.service.domain.Search;
import mybatis.service.domain.User;
import mybatis.service.user.UserDao;
import mybatis.service.user.UserService;


public class UserServiceImpl12 implements UserService {
	
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		System.out.println("::"+getClass()+".setUserDao call.....");
		this.userDao = userDao;
	}
	
	public UserServiceImpl12() {
		System.out.println("::"+getClass()+"default Constructor Call.....");
	}
	
	public int addUser(User user) throws Exception{
		return userDao.addUser(user);
	}
	
	public User getUser(String userId) throws Exception{
		return userDao.getUser(userId);
	}
	
	public int updateUser(User user) throws Exception{
		return userDao.updateUser(user);
	}
	
	public int removeUser(String userId) throws Exception{
		return userDao.removeUser(userId);
	}
	
	public List<User> getUserList(Search search) throws Exception{
		return userDao.getUserList(search);
			
	}
		
}
	
	


