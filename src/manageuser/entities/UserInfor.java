/**
 * Copyright(C) 2019  Luvina Software Company
 * UserInfor.java, 2019-11-16 NguyenHuuThiHieu
 */
package manageuser.entities;

/**
 * Chứa các thông tin của người dùng
 * @author NguyenHuuThiHieu
 */
public class UserInfor {
	// khai báo user_id
	private int userId;
	// khai báo group_id
	private String groupName;
	// khai báo tên đăng nhập
	private String fullName;
	// khai báo email
	private String email;
	// khai báo số điện thoại
	private String tel;
	// khai báo ngày sinh
	private String birthday;
	// Khai báo tên trình độ tiếng Nhật
	private String nameLevel;
	// Khai báo ngày hết hạn trính dộ tiếng Nhật
	private String endDate;
	//Khai báo điểm trình độ tiếng Nhật
	private int total;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
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
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the nameLevel
	 */
	public String getNameLevel() {
		return nameLevel;
	}
	/**
	 * @param nameLevel the nameLevel to set
	 */
	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}
	/**
	 * @return the endString
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endString the endString to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
}
