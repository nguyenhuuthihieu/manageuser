/**
 * Copyright(C) 2019  Luvina Software Company
 * TblUserDao.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.dao;

import java.sql.SQLException;
import java.util.List;

import manageuser.entities.TblUser;
import manageuser.entities.UserInfor;

/**
 * Interface Thao tÃ¡c vá»›i DB cá»§a cÃ¡c chá»©c nÄƒng login + list/search user
 * @author NguyenHuuThiHieu
 */
public interface TblUserDao {
	/**
	 * Lấy user bằng tên đăng nhập
	 * @param uName
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public TblUser getUserByLoginName(String uName) throws SQLException, ClassNotFoundException;
	/**
	 * Xử lí logic lấy ra tổng số user 
	 * @param groupId id group chọn
	 * @param fullName tên nhập tìm kiếm
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int getTotalUsers(int groupId, String fullName) throws ClassNotFoundException, SQLException;
	/**
	 * Lấy danh sách  user
	 * @param offset vị trí data cần lấy nào
	 * @param limit số lượng lấy
	 * @param groupId mã nhóm tìm kiếm
	 * @param fullName Tên tìm kiếm
	 * @param sortType Nhận biết xem cột nào được ưu tiên sắp xếp(full_name or end_date or code_level)
	 * @param sortByFullName Giá trị sắp xếp của cột Tên(ASC or DESC)
	 * @param sortByCodeLevel Giá trị sắp xếp của cột Trình độ tiếng nhật(ASC or DESC)
	 * @param sortByEndDate Giá trị sắp xếp của cột Ngày kết hạn(ASC or DESC)
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<UserInfor> getListUsers (int offset, int limit, int groupId,
				String fullName, String sortType, String sortByFullName, String sortByCodeLevel, String sortByEndDate) 
						throws ClassNotFoundException, SQLException;
}
