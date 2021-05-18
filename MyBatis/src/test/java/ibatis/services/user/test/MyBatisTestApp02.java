package ibatis.services.user.test;

import java.io.Reader;
import java.util.List;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : MyBatisTestApp02.java
  */
public class MyBatisTestApp02 {
	
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
		
		//0. getUserList01 :: # �����ڸ� �̿��� like ������ �˻� 
		//									  ==> �˻���� ���°� Ȯ��
		System.out.println(":: 0. user�ν����ϴ� userId User(SELECT)  ? ");
		List<User> list = sqlSession.selectList("UserMapper02.getUserList01","user");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//1. getUserList01 :: # �����ڸ� �̿��� like ������ �˻� 
		//                         			  ==> �˻���� ���� Ȯ�� :: %���ϵ�ī�� �����Է� 
		System.out.println(":: 1. user�ν����ϴ� userId User(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper02.getUserList01","user%");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//2.  getUserList01 :: # �����ڸ� �̿��� like ������ �˻� 
		//									   ==> �˻���� ���°� Ȯ��
		System.out.println(":: 2. 01�� ������ userId User(SELECT)  ? ");
		 list = sqlSession.selectList("UserMapper02.getUserList01","01");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//3. getUserList01 :: # �����ڸ� �̿���  like ������ �˻� 
		//						 			  ==> �˻���� ���� Ȯ�� :: %���ϵ�ī�� �����Է�
		System.out.println(":: 3. 01�� ������ userId User(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper02.getUserList01","%01");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//4. getUserList02 :: $ �����ڸ� �̿��� like ������ �˻�
		//									  ==> �˻���� ���� Ȯ�� 
		System.out.println(":: 4. user ���ڰ� �ִ� userId User(SELECT)  ? ");
		list = sqlSession.selectList("UserMapper02.getUserList02","user");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//5. getUserList02 :: $ �����ڸ� �̿��� like ������ �˻�
		//									  ==> �˻���� ���� Ȯ�� 
		System.out.println(":: 5. 01 ���ڰ� �ִ� userId User(SELECT)  ? ");
		 list = sqlSession.selectList("UserMapper02.getUserList02","01");
		for (int i =0 ;  i < list.size() ; i++) {
			System.out.println( "<"+ ( i +1 )+"> ��° ȸ��.."+ list.get(i).toString() );
		}
		System.out.println("\n");
		
		//6. getUserList01 / getUserList02 �� ���Ͽ� #������ ��
        // $������(���� �� ����)�� �ǹ̸� Ȯ������.  :: SQL ���Կ� ���� �ʿ� 
		System.out.println(":: 6. # ������ / $ �������� ����   ? ");
		String name = (String)sqlSession.
													selectOne("UserMapper02.findUser",
																"user_id = 'user01' and password = 'user01'");
		System.out.println(":: 6. findUser02(SELECT)  ? "+name);
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class