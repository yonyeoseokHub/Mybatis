package mybatis.service.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.Assert;
import mybatis.service.domain.User;
import mybatis.service.user.UserDao;
import mybatis.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:config/commonservice14.xml"})
public class UserServiceTest {
	
	@Autowired
	@Qualifier("userServiceImpl14")
	private UserService userService;
	
	@Autowired
	@Qualifier("userDaoImpl14")
	private UserDao userDAO;
	
	@Test
	public void testAddUser() throws Exception{
		
		System.out.println("\n===================================");
		User user = new User("user04", "주몽", "user04", null, 0);
		System.out.println("insert 결과: "+userService.addUser(user));
		System.out.println("delete결과:"+userDAO.removeUser(user.getUserId()));
		
		
		Assert.assertEquals(1, userService.addUser(user));
		Assert.assertEquals(1, userDAO.removeUser(user.getUserId()));
		System.out.println("======================================");
		
	}
	
	@Test
	public void testGetUser() throws Exception{
		System.out.println("======================================");
		User user = userService.getUser("user01");
		System.out.println(user);
		
		Assert.assertEquals("user01", user.getUserId());
		Assert.assertNotNull(userService.getUser("user02"));		
		System.out.println("======================================");
		
		
	}

}