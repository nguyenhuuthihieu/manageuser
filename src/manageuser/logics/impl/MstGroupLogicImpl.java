/**
 * Copyright(C) 2019  Luvina Software Company
 * MstGroupLogicImpl.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.logics.impl;

import java.sql.SQLException;
import java.util.List;

import manageuser.dao.MstGroupDao;
import manageuser.dao.impl.MstGroupDaoImpl;
import manageuser.entities.MstGroup;
import manageuser.logics.MstGroupLogic;

/**
 * Triển khai interface MstGroupLogic
 * @author NguyenHuuThiHieu
 */
public class MstGroupLogicImpl implements MstGroupLogic {

	/**
	 * @throws ClassNotFoundException, SQLException 
	 * @see manageuser.logics.MstGroupLogic#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException {
		List<MstGroup> listGroup = null;
		try {
			MstGroupDao groupDao = new MstGroupDaoImpl();
			listGroup = groupDao.getAllMstGroup();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getAllMstGroup " + e.getMessage());
			throw e;
		}
		return listGroup;
	}

}
