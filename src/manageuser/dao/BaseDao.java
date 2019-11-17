/**
 * Copyright(C) 2019  Luvina Software Company
 * BaseDao.java.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import manageuser.properties.DatabaseProperties;

/**
 * Thực hiện kết nối với DB
 * @author NguyenHuuThiHieu
 */
public class BaseDao {
	protected Connection connection;
	/**
	 * Mở kết nối đến DB 
	 *@throws SQLException
	 *@throws ClassNotFoundException
	 */
	public void openConnectDB() throws SQLException, ClassNotFoundException{
		try {
			Class.forName(DatabaseProperties.getInforDB("db.class_for_name"));	
			connection = DriverManager.getConnection(DatabaseProperties.getInforDB("db.url"), 
													DatabaseProperties.getInforDB("db.user"),
													DatabaseProperties.getInforDB("db.pass"));
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(this.getClass() + " method openConnectDB " + e.getMessage());
			throw e ;
		} 
	}
	
	/**
	 * Đóng kết nối DB
	 *@throws SQLException
	 */
	public void closeConnectDB() throws SQLException{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(this.getClass() + " method closeConnectDB " + e.getMessage());
			throw e;
		}
	}
}
