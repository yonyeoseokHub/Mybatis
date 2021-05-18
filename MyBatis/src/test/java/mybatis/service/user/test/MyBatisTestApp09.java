package mybatis.service.user.test;

import java.util.ArrayList;

import mybatis.service.domain.Search;

import org.apache.ibatis.session.SqlSession;

/*
 * FileName : IBatisTestApp09.java
  */
public class MyBatisTestApp09 {
	
	///main method
	public static void main(String[] args) throws Exception{
	
		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("\n");

		System.out.println("\n==================================\n");
		//==> Test �� Search instance ���� 
		Search search = new Search();
		
		//1. UserMapper09.getUserList01 Test 
		ArrayList<String> arrayList = new ArrayList<String>();
		search.setUserId( arrayList );
		System.out.println(":: 1. getUserList01(SELECT)  ? ");
		//==> �Ʒ��� �� ���๮ �ּ��� �����ư���  1EA �� Test �ϼ���.
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper09.getUserList01",search) );
		//MyBatisTestUtil.printList( sqlSession.selectList("UserMapper09.getUserList03",search) );
		
		//2. UserMapper09.getUserList01 Test 
		arrayList.add("user01");
		arrayList.add("user02");
		System.out.println(":: 2. getUserList01(SELECT)  ? ");
		//==> �Ʒ��� �� ���๮ �ּ��� �����ư���  1EA �� Test �ϼ���.
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper09.getUserList01",search) );
		//MyBatisTestUtil.printList( sqlSession.selectList("UserMapper09.getUserList03",search) );
		
		//3. UserMapper09.getUserList02 Test
		String [] userName = new String[2];
		search.setUserName(userName);
		System.out.println(":: 3. getUserList02(SELECT)  ? ");
		//==> �Ʒ��� �� ���๮ �ּ��� �����ư���  1EA �� Test �ϼ���.
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper09.getUserList02",search) );
		//MyBatisTestUtil.printList( sqlSession.selectList("UserMapper09.getUserList04",search) );
		
		//4. UserMapper09.getUserList02 Test
		userName[ 0 ] = "ȫ�浿iba";
		userName[ 1 ] = "�ָ�";
		search.setUserName(userName);
		System.out.println(":: 4. getUserList02(SELECT)  ? ");
		//==> �Ʒ��� �� ���๮ �ּ��� �����ư���  1EA �� Test�ϼ���.
		SqlSessionFactoryBean.printList( sqlSession.selectList("UserMapper09.getUserList02",search) );
		//MyBatisTestUtil.printList( sqlSession.selectList("UserMapper09.getUserList04",search) );
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
		
	}//end of main
}//end of class