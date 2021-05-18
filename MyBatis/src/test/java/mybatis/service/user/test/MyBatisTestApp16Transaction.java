package mybatis.service.user.test;

import mybatis.service.domain.User;
import mybatis.service.user.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * FileName : MyBatisTestApp16Transaction.java
  * :: Business Layer unit Test : Service + Persistence (Spring +mybatis + DAO)
  * :: Annotation ��� 
  * :: AOP + Spring Transaction 
  */
public class MyBatisTestApp16Transaction {
	
	///main method
	public static void main(String[] args){

		ApplicationContext context =
				new ClassPathXmlApplicationContext(
																	new String[] {	"/config/commonservice16.xml"	 }
									                                   );
		System.out.println("\n");

		//==> Bean/IoC Container �� ���� ȹ���� UserService �ν��Ͻ� ȹ��
		UserService userService = (UserService)context.getBean("userServiceImpl14");
		
		System.out.println("\n");
		
		//==> Test �� User instance ����  
		User user = new User("user04","�ָ�","user04",null,0);
		
		try{
			System.out.println("\n::::::::::::::::::: Test ���Ȯ��.... start  :::::::::::::::::::::::::::");
			System.out.println(":: ȸ������ ��û��....");
			userService.addUser(user);
			System.out.println(":: ȸ������ ������....");
			System.out.println("::::::::::::::::::::: Test ���Ȯ��.... end  ::::::::::::::::::::::::::::");
		}catch(Exception e){
			e.printStackTrace();
		}
	}//end of main
}//end of class