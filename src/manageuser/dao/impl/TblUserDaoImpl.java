/**
 * Copyright(C) 2019  Luvina Software Company
 * TblUserDaoImpl.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manageuser.dao.BaseDao;
import manageuser.dao.TblUserDao;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.utils.Common;

/**
 * Implement UserDao để Thao tác với DB của các chức năng login + list/search user
 * @author NguyenHuuThiHieu
 */
public class TblUserDaoImpl extends BaseDao implements TblUserDao {
	public static final String SELECT_ADMIN_CMD = "SELECT login_name, password, salt"
			+" FROM tbl_user WHERE login_name = ? AND rule = 0";
	public static final String SELECT_USERS_CMD = "SELECT u.user_id, u.full_name, u.birthday, g.group_name, u.email, u.tel, j.name_level, duj.end_date, duj.total"
													+ " FROM tbl_user u JOIN mst_group g ON u.group_id = g.group_id"
													+ " LEFT JOIN tbl_detail_user_japan duj ON u.user_id = duj.user_id"
													+ " LEFT JOIN japan j ON duj.code_level = j.code_level"
													+ " WHERE login_name = ? AND rule = 1";
	/**
	 * @see manageuser.dao.TblUserDao#getUserByLoginName(java.lang.String)
	 */
	@Override
	public TblUser getUserByLoginName(String uName) throws SQLException, ClassNotFoundException {
		TblUser user = null;
		try {
			openConnectDB();
			if (connection != null) {
				PreparedStatement pStatement = connection.prepareStatement(SELECT_ADMIN_CMD);
				pStatement.setString(1, uName);
				ResultSet result = pStatement.executeQuery();
				if (result.next()) {
					user = new TblUser();
					user.setLoginName(result.getString("login_name"));
					user.setPassword(result.getString("password"));
					user.setSalt(result.getString("salt"));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getUserByLoginName() " + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return user;
	}
	/**
	 * @throws ClassNotFoundException, SQLException 
	 * @see manageuser.dao.TblUserDao#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		int totalUser = 0;
		try {
			openConnectDB();
			if (connection != null) {
				StringBuilder countUserSql = new StringBuilder();
				countUserSql.append("SELECT COUNT(u.user_id) AS totalUser FROM tbl_user u ");
				countUserSql.append("WHERE u.full_name LIKE ? AND u.rule = 1 ");
				if (groupId != 0) {
					countUserSql.append(" AND u.group_id = ?");
				}
				PreparedStatement pStatement = connection.prepareStatement(countUserSql.toString());
				int index = 1;
				pStatement.setString(index++, "%" + Common.encodeSQL(fullName) + "%");
				if (groupId != 0) {
					pStatement.setInt(index++, groupId);
				}
				ResultSet result = pStatement.executeQuery();
				if (result.next()){
					totalUser = result.getInt("totalUser");
			    }
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getTotalUsers() " + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return totalUser;
	}
	/**
	 * @throws ClassNotFoundException, SQLException 
	 * @see manageuser.dao.TblUserDao#getListUsers(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
					String sortByFullName, String sortByCodeLevel, String sortByEndDate) 
					throws ClassNotFoundException, SQLException {
		List<UserInfor> listUsers = new LinkedList<UserInfor>();
		try {
			openConnectDB();
			StringBuilder usersSql = new StringBuilder();
			usersSql.append("SELECT  u.user_id, u.full_name, u.birthday, g.group_name, u.email, u.tel, j.name_level, duj.end_date, duj.total ");
			usersSql.append("FROM tbl_user u JOIN mst_group g ON u.group_id = g.group_id ");
			usersSql.append("LEFT JOIN tbl_detail_user_japan duj ON u.user_id = duj.user_id ");
			usersSql.append("LEFT JOIN mst_japan j ON duj.code_level = j.code_level ");
			usersSql.append("WHERE full_name LIKE ? AND rule = 1 ");
			if (groupId != 0) {
				usersSql.append("AND u.group_id = ? ");
			}
			if ("full_name".equals(sortType) && "ASC".equals(sortByFullName)) {
				usersSql.append("ORDER BY  u.full_name ASC, duj.code_level ASC, duj.end_date DESC ");
			} else if ("full_name".equals(sortType) && "DESC".equals(sortByFullName)) {
				usersSql.append("ORDER BY  u.full_name DESC, duj.code_level ASC, duj.end_date DESC ");
			} else if ("code_level".equals(sortType) && "ASC".equals(sortByCodeLevel)) {
				usersSql.append("ORDER BY duj.code_level ASC,  u.full_name ASC, duj.end_date DESC ");
			} else if ("code_level".equals(sortType) && "DESC".equals(sortByCodeLevel)) {
				usersSql.append("ORDER BY duj.code_level DESC,  u.full_name ASC, duj.end_date DESC ");
			} else if ("end_date".equals(sortType) && "DESC".equals(sortByEndDate)) {
				usersSql.append("ORDER BY duj.end_date DESC, u.full_name ASC, duj.code_level ASC ");
			} else if ("end_date".equals(sortType) && "ASC".equals(sortByEndDate)) {
				usersSql.append("ORDER BY duj.end_date ASC, u.full_name ASC, duj.code_level ASC ");
			}	
			usersSql.append("LIMIT ?, ? ");
			PreparedStatement pStatement = connection.prepareStatement(usersSql.toString());
			int index = 1;
			pStatement.setString(index++, "%" + Common.encodeSQL(fullName) + "%");
			if (groupId != 0) {
				pStatement.setInt(index++, groupId);
			}
			pStatement.setInt(index++, (offset - 1) * limit);
			pStatement.setInt(index++, limit);
			ResultSet result = pStatement.executeQuery();
			while (result.next()){
				UserInfor userInfor = new UserInfor();
				userInfor.setUserId(result.getInt("user_id"));
				userInfor.setFullName(Common.encodeHTML(result.getString("full_name")));
				userInfor.setEmail(Common.encodeHTML(result.getString("email")));
				userInfor.setBirthday(Common.formatDate(result.getDate("birthday")));
				userInfor.setTel(result.getString("tel"));
				userInfor.setGroupName(Common.encodeHTML(result.getString("group_name")));
				userInfor.setNameLevel(Common.encodeHTML(result.getString("name_level")));
				userInfor.setEndDate(Common.formatDate(result.getDate("end_date")));
				userInfor.setTotal(result.getInt("total"));
				listUsers.add(userInfor);
		    }
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getListUsers() " + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return listUsers;
	}
	
}
