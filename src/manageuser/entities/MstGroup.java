/**
 * Copyright(C) 2019  Luvina Software Company
 * MstGroup.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.entities;

/**
 * Bean chứa các thuộc tính của bảng tbl_group trong db
 * @author NguyenHuuThiHieu
 */
public class MstGroup {
	private int groupId;
	private String groupName;
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
