package mybatis.service.user.test;

import java.io.Reader;
import java.util.List;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : MyBatisTestApp05.java
 * �� �ݹ� ������ �Ʒ��� ���� �ι� Test �Ѵ�.
 * 		1. mybatis-config01.xml  <setting name="callSettersOnNulls" value="false"/>
 * 		2. mybatis-config01.xml  <setting name="callSettersOnNulls" value="true"/>
 *   ������ ���� Test�� ��.
 *   �� JDBCTestApp05.java �� �����Ͽ� ���� �� ��.
  */
public class MyBatisTestApp05 {
	
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
		
		//==> Test �� User instance ���� �� age / regData null setting
		User user = new User("user04","�ָ�","user04",null,0);
		user.setRegDate(null);
	
		//1. UserMapper05.addUser Test  :: users table age/grade/redDate �Է°� Ȯ��:OK 
		System.out.println(":: 1. addUser(INSERT)  ? "
													+ sqlSession.insert("UserMapper05.addUser",user));
		System.out.println("\n");
		
		//2. UserMapper05.getUser01 Test :: users table age/grade/redDate �Է°� Ȯ��
		System.out.println(":: 2. getUser01(SELECT)  ? "
													+ sqlSession.selectOne("UserMapper05.getUser02",
																													user.getUserId()));
		System.out.println("\n");
		
		//3. UserMapper05.getUserList Test :: users table age/grade/redDate �Է°� Ȯ��
		System.out.println(":: 3. getUserList(SELECT)  ? ");
		List<User> list = sqlSession.selectList("UserMapper05.getUserList");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//4. UserMapper05.removeUser Test
		System.out.println(":: 4. removeUser (DELETE)  ? "
													+sqlSession.delete("UserMapper05.removeUser", 
																							user.getUserId()) );
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class