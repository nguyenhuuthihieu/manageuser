/**
 * Copyright(C) 2019  Luvina Software Company
 * MstGroupLogic.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.logics;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.MstGroup;

/**
 * Interface thực hiện logic liên quan đến MstGroup
 * @author NguyenHuuThiHieu
 */
public interface MstGroupLogic {
	/**
	 * Xử lí logic lấy danh sách group trong DB
	 * @return danh sách group
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException;
}
