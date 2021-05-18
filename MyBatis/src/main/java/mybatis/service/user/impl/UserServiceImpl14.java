package mybatis.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mybatis.service.domain.Search;
import mybatis.service.domain.User;
import mybatis.service.user.UserDao;
import mybatis.service.user.UserService;


@Service("userServiceImpl14")
public class UserServiceImpl14 implements UserService {
	
	@Autowired
	@Qualifier("userDaoImpl14")
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		System.out.println("::"+getClass()+".setUserDao call.....");
		this.userDao = userDao;
	}
	
	public UserServiceImpl14() {
		System.out.println("::"+getClass()+"default Constructor Call.....");
	}
	
	public int addUser(User user) throws Exception{
		
		int result = 0;
		System.out.println("첫번째 insert");
		result = userDao.addUser(user);
		System.out.println("첫번째 insert결과"+result);
		System.out.println("두번째 insert");
		result = userDao.addUser(user);
		System.out.println("두번째 insert결과"+result);
		
		return 0;
		
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
	
//	Transaction을 확인하기 위한 주석처리
//	public int addUser(User user) throws Exception{
//		return userDao.addUser(user);
//	}
//	
//	public User getUser(String userId) throws Exception{
//		return userDao.getUser(userId);
//	}
//	
//	public int updateUser(User user) throws Exception{
//		return userDao.updateUser(user);
//	}
//	
//	public int removeUser(String userId) throws Exception{
//		return userDao.removeUser(userId);
//	}
//	
//	public List<User> getUserList(Search search) throws Exception{
//		return userDao.getUserList(search);
//			
	}
		
}
	
	


