/**
 * Copyright(C) 2019  Luvina Software Company
 * TblUserLogicImpl.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblUserDaoImpl;
import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;
import manageuser.logics.TblUserLogic;
import manageuser.utils.Common;

/**
 * Triển khai interface TblUserLogic
 * @author NguyenHuuThiHieu
 */
public class TblUserLogicImpl implements TblUserLogic {
	private TblUserDao userDao = new TblUserDaoImpl();
	/* (non-Javadoc)
	 * @see manageuser.logics.TblUserLogic#checkExistUser(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean checkExistUser(String uName, String pass) 
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Boolean checkExist = false;
		try {
			TblUser user = userDao.getUserByLoginName(uName);
			if (user != null) {
				String salt = user.getSalt();
				String passIEncode = Common.encryptPassword(pass, salt);
				String passDB = user.getPassword();
				checkExist = Common.comparePassword(passDB, passIEncode);
			} 
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			throw e;
		} 
		
		return checkExist;
	}

	/**
	 * @throws ClassNotFoundException, SQLException 
	 * @see manageuser.logics.TblUserLogic#getTotalUsers(int, java.lang.String)
	 */
	@Override
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException {
		int totalUsers = 0;
		try {
			totalUsers = userDao.getTotalUsers(groupId, fullName);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getTotalUsers() " + e.getMessage());
			throw e;
		}
		return totalUsers;
	}

	/**
	 * @throws ClassNotFoundException,SQLException 
	 * @see manageuser.logics.TblUserLogic#getListUsers(int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserInfor> getListUsers(int offset, int limit, int groupId, String fullName, String sortType,
			String sortByFullName, String sortByCodeLevel, String sortByEndDate) 
					throws ClassNotFoundException,SQLException {
		List<UserInfor> listUsers = null;
		try {
			listUsers = userDao.getListUsers(offset, limit, groupId, fullName, sortType, sortByFullName, sortByCodeLevel, sortByEndDate);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getListUsers() " + e.getMessage());
			throw e;
		}
		return listUsers;
	}
	
}
