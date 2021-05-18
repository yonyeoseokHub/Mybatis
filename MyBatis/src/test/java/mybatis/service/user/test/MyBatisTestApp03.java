package mybatis.service.user.test;

import java.io.Reader;
import java.util.List;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : IBatisTestApp03.java
  */
public class MyBatisTestApp03 {
	
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
		
		//0.UserMapper03.getUserList Test
		System.out.println(":: 0. getUserList(SELECT)  ? ");
		List<User> list = sqlSession.selectList("UserMapper03.getUserList");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//==> Test �� User domain instance ����
		User user = new User("user04","�ָ�","user04",40,4);
		
		//1.UserMapper03.addUser Test
		int insultResult = sqlSession.insert("UserMapper03.addUser", user);
		System.out.println(":: 1. addUser(INSERT)  result ? "+insultResult);
		System.out.println("\n");
		
		//2.UserMapper03.getUserList Test
		System.out.println(":: 2. getUserList(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper03.getUserList");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//3.UserMapper03.uadateUser Test
		user.setUserName("�庸��");
		int updateResult = sqlSession.update("UserMapper03.updateUser", user);
		System.out.println(":: 3. updateUser(UPDATE) result ? "+updateResult);
		System.out.println("\n");
		
		//4.UserMapper03.getUserList Test
		System.out.println(":: 4. getUserList(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper03.getUserList");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//5.UserMapper03.removeUser Test
		int deleteResult = sqlSession.delete("UserMapper03.removeUser", user.getUserId());
		System.out.println(":: 5. removeUser(DELETE) result ? "+deleteResult);
		System.out.println("\n");
		
		//6. UserMapper03.getUserList Test
		System.out.println(":: 6. getUserList(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper03.getUserList");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class