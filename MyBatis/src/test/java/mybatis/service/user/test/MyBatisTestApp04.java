package mybatis.service.user.test;

import java.io.Reader;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : MyBatisTestApp04.java
  */
public class MyBatisTestApp04 {
	
	///main method
	public static void main(String[] args) throws Exception{
		
		//==> 1. xml metadata �д� Stream ����
		Reader reader = Resources.getResourceAsReader("sql/mybatis-config01.xml");
		
		//==> 2. Reader ��ü�� �̿� xml metadata �� ������ ���� ������ ����, ��밡���� 
		//==>     SqlSession�� �����ϴ� SqlSessionFactory  instance ����
		SqlSessionFactory sqlSessionFactory 
											= new SqlSessionFactoryBuilder().build(reader);
		//==>3. SqlSessionFactory �� ���� autoCommit true �� SqlSession instance ����
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		System.out.println("\n");
		
		//==> Test �� user instance ���� �� age / regData null setting
		User user = new User("user04","�ָ�","user04",null,0);
	
		//1. UserMapper04.addUser01 Test  :: users table age/grade/redDate �Է°� Ȯ��:NG 
		System.out.println(":: 1. addUser01(INSERT)  ? "
													+ sqlSession.insert("UserMapper04.addUser01",user));
		System.out.println("\n");
		
		//2. UserMapper04.addUser02 Test :: users table age/grade/redDate �Է°� Ȯ��:NG
		user.setUserId("user05");
		System.out.println(":: 2. addUser02(INSERT)  ? "
													+ sqlSession.insert("UserMapper04.addUser02",user));
		System.out.println("\n");
		
		//3. UserMapper04.addUer03 Test  :: users table age/grade/redDate �Է°� Ȯ��:OK
		user.setUserId("user06");
		//==> Dynamic SQL(?) ��� ó�� :: ���� �н�..	
		System.out.println(":: 3. addUser03(INSERT)  ? "
													+ sqlSession.insert("UserMapper04.addUser03",user));
		System.out.println("\n");
		
		//4. UserMapper04.addUser04 Test  :: users table age/grade/redDate �Է°� Ȯ��:OK
		user.setUserId("user07");
		//==> Dynamic SQL(?) ��� ó�� :: ���� �н�..
		System.out.println(":: 4. addUser04(INSERT)  ? "
													+ sqlSession.insert("UserMapper04.addUser04",user));
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class