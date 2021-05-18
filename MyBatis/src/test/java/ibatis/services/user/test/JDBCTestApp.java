package ibatis.services.user.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mybatis.service.domain.User;

/*
 * FileName : JDBCTestApp.java
 *   �� JDBC ö���� ���� DBMS users table �� ���� SELECT  :: ������� 
 */
public class JDBCTestApp {
	
	///main method
	public static void main(String[] args) {
		//DBMS ��������
		String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String dbuser ="ibatis";
		String dbpwd = "ibatis";

		//JDBC�� �̿��ϱ� ���� ��ü ����
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet selectResult = null;
		
		//==> DB �� ����� ��� ȸ�������� ArrayList�� �����ϱ� ����....  
		List<User> list = null; 

		try{		
			//1�ܰ� connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dburl,dbuser,dbpwd);
			
			//2�ܰ� SELECT �� ���� �� ���� 
			StringBuffer  userSelectAll = new StringBuffer();
			userSelectAll.append("SELECT ");
			userSelectAll.append("user_id, user_name, password, age, grade, reg_date ");
			userSelectAll.append("FROM USERS ");
			pStmt = con.prepareStatement( userSelectAll.toString() );

			//3�ܰ� SELECT ����
			selectResult = pStmt.executeQuery();
			//==> �����Ų SQL Ȯ��
			System.out.println( "[userSelectAll SQL] :: "+ userSelectAll.toString() );
			
			//==> DB �� ����� ��� ȸ�������� ArrayList�� �����ϱ� ����....  
			list = new ArrayList<User>();
			
			 while( selectResult.next() ){
				 //==> 1EA�� User Domain instance�� 1EA�� user ������ ���´� 
				User user = new User();
				System.out.println("::"+selectResult.getString("user_id")+"���� ArrayList ����..");
				 user.setUserId( selectResult.getString("user_id") );
				 user.setUserName( selectResult.getString("user_name") );
				 user.setPassword( selectResult.getString("password") );
				 user.setAge( selectResult.getInt("age") );
				 user.setGrade( selectResult.getInt("grade") );
				 user.setRegDate( selectResult.getTimestamp("reg_date"));
				 list.add( user );
			 }
			
		}catch(ClassNotFoundException e){		
			throw new RuntimeException(e.getMessage(), e);
		}catch(SQLException e){		
			throw new RuntimeException(e.getMessage(), e);
		}finally{
			//������ DB�� ���õ� ��ü close
			if(selectResult != null){
				try{	
					selectResult.close();		
				}catch(Exception e1){  }
			}
			if(pStmt != null){
				try{	
					pStmt.close();	
				}catch(Exception e2){  }
			}
			if(con != null){
				try{	
					con.close();		
				}catch(Exception e3){  }
			}
		}//end of finally
		
		System.out.println("\n#####################################");
		System.out.println(":: ȸ������ ���");
		
		for (User user : list) {
			System.out.println( user.toString() ) ;
		}
		//==>���� ����
		//for (int i =0 ; i < list.size() ; i++){ 
		//	System.out.println(   list.get( i ).toString() ) ;
		//}
		System.out.println("#####################################\n");
	}//end of main
}//end of class