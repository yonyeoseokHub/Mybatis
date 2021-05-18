package ibatis.services.user.test;

import java.io.Reader;
import java.util.List;

import mybatis.service.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * FileName : MyBatisTestApp.java
  * �� JBDCTestApp.java �� �� ����.
  * �� MyBatis Framework ���� �����ϴ� API�� ��� users table �� ���� SELECT   
 */
public class MyBatisTestApp {
	
	///main method
	public static void main(String[] args) throws Exception{
		
		//==> mybatis-config.xml : MyBatis Framework �� �ٽ� MetaData
		//==> UserMapper.xml : SQL �� ���� MetaData 
		
		//==> 1. xml metadata �д� Stream ����
		Reader reader = Resources.getResourceAsReader("sql/mybatis-config.xml");
		
		//==> 2. Reader ��ü�� �̿� xml metadata �� ������ ���� ������ ����, ��밡���� 
		//==>     SqlSession�� �����ϴ� SqlSessionFactory  instance ����
		SqlSessionFactory sqlSessionFactory 
											= new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//==> 3. xml �� ��ϵ� query ���� �� ������ ���(?) ��ü�� ���� List ��ü �ޱ� 
		List<User> list = sqlSession.selectList("UserMapper.getUserList");
		
		//==> 3. SqlSession  close
		sqlSession.close();
		/*	  
		  	//==>UserMapper.xml �� ���,������ ����
		  	<select 	id="getUserList" 	resultType="mybatis.service.domain.User">
				SELECT
				user_id 			AS userId,
				user_name 	AS userName,
				password 	AS password,
				age 				AS age,
				grade				AS grade,
				reg_date 		AS regDate
				FROM USERS   											
		  	</select>
		 */
		System.out.println("#####################################");
		System.out.println(":: ȸ������ ���");
		
		for (User user : list) {
			System.out.println( user.toString() ) ;
		}
		System.out.println("#####################################");
	}// end of main
}//end of class

/*
 * �� MyBatisTestApp / JDBCTestApp �� ���� MyBatis Framework �� ����
 * �� SQL,Ŀ�ؼ�,Ʈ���輱 �� ��Ÿ����Ÿ�� ĸ��ȭ������, 
 *     :: ���� => mybatis-config.xml / UserMapper.xml
 * �� JDBCö�� :  Connection => Statement => ResultSet
 *      resource ���� : close
 *      query ���� ��� �����Ͻ���ü ���ε� JDBC API �� ����Ͽ� ����� �ݺ������� �ݵ��
 *      �����ϴ� �Ϸ��� ������ ������.
 *      :: ���� =>List<User> list = sqlSession.selectList("UserMapper.getUserList");
 *  
 *  �� MyBatis Framework �� ����
 *  �� �۰� �����ϴ� (�ٸ� ���̺귯���� �������� ����. )
 *  �� SQL Mapper(Data Mapper) =>SQL �� �����Ͻ���ü ���ε�
 *  �� ���꼺 / ���� / �۾��� �й� (�ҽ��ڵ�� SQL �� �и�)
 *  �� ���ɻ��� �и� 
 *       ( DBMS �� �������� API���� �� JDBC API�� �ƴ� �����Ͻ� ��ü�� ������ �۾�)
 *        
  *  �� MyBatis Framework �� JDBC ������ ����ȭ / JDBC ö���� �߻�ȭ / ĸ��ȭ�� lib 
*/
