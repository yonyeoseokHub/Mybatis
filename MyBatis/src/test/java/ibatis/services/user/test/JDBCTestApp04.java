package ibatis.services.user.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

import mybatis.service.domain.User;

/*
 * FileName : JDBCTestApp04.java
 *   �� JDBC ö���� ���� DBMS users table  INSERT  ::  INSERT/UDATE �� NULL ó��
 */
public class JDBCTestApp04 {
	
	//main method
	public static void main(String[] args) throws Exception{
		//DBMS ��������
		String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String dbuser ="ibatis";
		String dbpwd = "ibatis";
		//JDBC�� �̿��ϱ� ���� ��ü ����
		Connection con = null;
		PreparedStatement pStmt = null;
		
		//==> Test �� User instance ����
		User user = new User("user04","�ָ�","user04",null,0);
		
		//1�ܰ� connection
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(dburl,dbuser,dbpwd);
		
		//2�ܰ� INSERT �� SELECT �� ���� 
		//==> INSERT SQL
		StringBuffer  insertSQL = new StringBuffer();
		insertSQL.append("INSERT ");
		insertSQL.append("INTO USERS( user_id,user_name,password,age,grade,reg_date) ");
		insertSQL.append("VALUES(?,?,?,?,?,?) ");
				
		//3�ܰ� INSET,SELECT ����
		//==>INSET SQL
		pStmt = con.prepareStatement( insertSQL.toString() );
		pStmt.setString(1, user.getUserId());
		pStmt.setString(2, user.getUserName());
		pStmt.setString(3, user.getPassword());
		/////////////// TABLE column NULL �Է� API�� ���� //////////////////
		pStmt.setNull(4,Types.INTEGER);	  	//==> NULL �Է�ó��
		pStmt.setNull(5,Types.NUMERIC); 	//==> NULL �Է�ó��
		pStmt.setNull(6, Types.DATE);			//==> NULL �Է�ó�� 
	    //==>JDBC Driver �� ����̹��� �÷�Ÿ���� �˷����� ������ �÷�Ÿ���� �˼� ����
		//==>JDBC API �� DB table Field �� ������ Ÿ���� ����Ͽ� ����, null �Է�
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
		int isInsert = pStmt.executeUpdate();
		//==> �����Ų SQL Ȯ�� �� ��� Ȯ��
		System.out.println( "SQL :: "+ insertSQL.toString() );
		System.out.println("INSERT ���� : "+isInsert);
		
		//4�ܰ� �� Resource �ݳ�
		 pStmt.close();		 con.close();
		 
	}//end of main
}//end of class