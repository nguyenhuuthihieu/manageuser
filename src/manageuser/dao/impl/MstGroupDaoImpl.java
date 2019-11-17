/**
 * Copyright(C) 2019  Luvina Software Company
 * MstGroupDaoImpl.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import manageuser.dao.BaseDao;
import manageuser.dao.MstGroupDao;
import manageuser.entities.MstGroup;
import manageuser.utils.Common;
/**
 * Triển khai interface MstGroupDao
 * @author NguyenHuuThiHieu
 */
public class MstGroupDaoImpl extends BaseDao implements MstGroupDao {
	
	/**
	 * @throws ClassNotFoundException, SQLException
	 * @see manageuser.dao.MstGroupDao#getAllMstGroup()
	 */
	@Override
	public List<MstGroup> getAllMstGroup() throws ClassNotFoundException, SQLException {
		List<MstGroup> listGroup = new LinkedList<MstGroup>();
		try {
			openConnectDB();
			if (connection != null) {
				String sqlGetAllGroups = "SELECT group_id, group_name FROM mst_group ORDER BY group_id";
				PreparedStatement pStatement = connection.prepareStatement(sqlGetAllGroups);
				ResultSet result = pStatement.executeQuery();
				while (result.next()) {
					MstGroup group = new MstGroup();
					group.setGroupId(result.getInt("group_id"));
					group.setGroupName(Common.encodeHTML(result.getString("group_name")));
					listGroup.add(group);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(this.getClass() + " method getAllMstGroup " + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return listGroup;
	}

}
