package mybatis.service.user.test;

import java.util.ArrayList;

import mybatis.service.domain.Search;
import mybatis.service.domain.User;

import org.apache.ibatis.session.SqlSession;

/*
 * FileName : MyBatisTestApp101.java
  */
public class MyBatisTestApp10 {
	
	///main method
	public static void main(String[] args) throws Exception{
	
		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("\n");
		
		//==> Test �� User instance ����  
		User user = new User("user04","�ָ�","user04",null,0);
		
		//1. UserMapper10.addUser Test  :: users table age/grade/redDate �Է°� Ȯ���Ұ� : OK 
		System.out.println(":: 1. addUser(INSERT)  ? ");
		System.out.println(":: "+ sqlSession.insert("UserMapper10.addUser",user));
		System.out.println("\n");
		
		//2. UserMapper10.getUser Test :: �ָ� inert Ȯ�� 
		System.out.println(":: 2. getUser(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectOne("UserMapper10.getUser",user.getUserId()) );
		System.out.println("\n");
		
		//3. UserMapper10.uadateUser Test  :: users table age/grade/redDate �Է°� Ȯ���Ұ� : OK
		//                                                    :: �ָ� ==> �´� ����
		user.setUserName("�´�");
		user.setGrade(777);
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+ sqlSession.update("UserMapper10.updateUser",user));
		System.out.println("\n");
		
		//4. UserMapper10.getUserList Test  :: Dynamic Query Ȯ�� id=user04/name=�´� �˻�
		System.out.println(":: 4. getUser(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectOne("UserMapper10.getUser",user.getUserId()) );
		System.out.println("\n");
		
		//5. UserMapper10.removeUser Test
		System.out.println(":: 5. Use10.removeUser (DELETE)  ? ");
		System.out.println(":: "+sqlSession.delete("UserMapper10.removeUser", 
																						   user.getUserId()) );
		System.out.println("\n");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("\n");
		
		//==> Test �� Search instance ���� 
		Search search = new Search();
		
		//1. UserMapper10.getUserList Test 
		System.out.println(":: 1. getUserList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper10.getUserList",search) );
		
		//2. UserMapper10.getUserList Test 
		search.setSearchCondition("userId");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("user01");
		search.setUserId( arrayList );
		
		System.out.println(":: 2. getUserList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper10.getUserList",search) );
		
		//3. UserMapper10.getUserList Test 
		search.setSearchCondition("userName");
		search.setUserName( new String[]{ "�̼���"} );
		
		System.out.println(":: 3. getUserList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper10.getUserList",search) );

		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
		
	}//end of main
}//end of class