/**
 * Copyright(C) 2019  Luvina Software Company
 * MstGroupDao.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface thá»±c hiá»‡n káº¿t ná»‘i Ä‘áº¿n báº£ng Mst_group trong DB
 * @author NguyenHuuThiHieu
 */
public interface MstGroupDao {
	/**
	 * Lấy ra danh sách group trong DB
	 * @return danh sách group
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException;
}
