package ibatis.services.user.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;

import mybatis.service.domain.User;

/*
 * FileName : JDBCTestApp06.java
 *   �� JDBC ö���� ���� DBMS users table  INSERT  ::  INSERT �� NULL ó��
 *   �� JDBC ö���� ���� DBMS users table  SELECT ::  NULL Ŀ���� Application ���� ó��  
 */
public class JDBCTestApp05 {
	
	//main method
	public static void main(String[] args) throws Exception{
		//DBMS ��������
		String dburl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String dbuser ="ibatis";
		String dbpwd = "ibatis";
		//JDBC�� �̿��ϱ� ���� ��ü ����
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
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
		//==>SELECT SQL
		StringBuffer  selectSQL = new StringBuffer();
		selectSQL.append("SELECT ");
		selectSQL.append("user_id, user_name, password, age, grade, reg_date ");
		selectSQL.append("FROM USERS WHERE user_id=?");
		
		//3�ܰ� INSET,SELECT ����
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
		
		//==>SELECT SQL
		pStmt = con.prepareStatement( selectSQL.toString() );
		pStmt.setString(1, user.getUserId());
		rs = pStmt.executeQuery();
		//==> �����Ų SQL Ȯ��
		System.out.println( "SQL :: "+ selectSQL.toString() );
		
		 //==> 1EA�� User ��ü�� 1EA�� user ������ ���´� 
		User vo = new User();
		
		if( rs.next() ){
			 vo.setUserId( rs.getString("user_id") );
			 vo.setUserName( rs.getString("user_name") );
			 vo.setPassword( rs.getString("password") );
			 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			 //==> NULL �Էµ� column  console Ȯ���ϸ�....
			 if(rs.getString("user_id").equals("user04")){
				 System.out.println("getObject(password) : "+rs.getObject("password")); // toString() ����
				 System.out.println("getString(password) : "+rs.getString("password"));
				 System.out.println("getObject(age) : "+rs.getObject("age"));
				 System.out.println("getInt(age) : "+rs.getInt("age"));
				 System.out.println("getObject(grade) : "+rs.getObject("grade"));
				 System.out.println("getInt(grade) : "+rs.getInt("grade"));
				 System.out.println("getObject(reg_date) : "+rs.getObject("reg_date"));
				 System.out.println("getTimestamp(reg_date) : "+rs.getTimestamp("reg_date"));
			 }
			 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			 vo.setAge( rs.getInt("age") );
			 vo.setGrade( rs.getInt("grade") );
			 vo.setRegDate( rs.getTimestamp("reg_date"));
		 }
		//4�ܰ� �� Resource �ݳ�
		 rs.close();		 pStmt.close();		 con.close();
		System.out.println("#####################################");
		System.out.println(":: ȸ������ ���");
		//==> �Ʒ��� ��°���� DBMS�� select ����� Ȯ������...
		//==> Reference DataType / Primitive DataType �� �Ͽ� 
		//==> TABLE   null �Էµ� �κ��� �����Ͻ���ü(VO)�� ��� ���ε��Ǿ� �ִ��� Ȯ��
		System.out.println( vo.toString() ) ;
		System.out.println("#####################################");
	}//end of main
}//end of class