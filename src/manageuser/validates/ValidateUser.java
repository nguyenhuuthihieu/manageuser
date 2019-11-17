/**
 * Copyright(C) 2019  Luvina Software Company
 * ValidateUser.java.java, 2019-11-14 NguyenHuuThiHieu
 */
package manageuser.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.MessageProperties;

/**
 * Thực hiện validate thông tin người dùng nhập
 * @author NguyenHuuThiHieu
 */
public class ValidateUser {
	/**
	 * Validate dữ liệu đăng nhập 
	 *@param uname
	 *@param pass
	 *@return
	 * @throws ClassNotFoundException, NoSuchAlgorithmException, SQLException 
	 */
	public List<String> validateLogin(String uName, String pass) 
			throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		List<String> listError = new ArrayList<String>();
		try {
			Boolean isNameEmpty = "".equals(uName);
			Boolean isPassEmpty = "".equals(pass);
			if (isNameEmpty) {
				listError.add(MessageProperties.getMsg("er001_login_name_empty"));
			} 
			if (isPassEmpty) {
				listError.add(MessageProperties.getMsg("er001_pass_empty"));
			} 
			if (!isNameEmpty && !isPassEmpty){
				TblUserLogic userLogic = new TblUserLogicImpl();
				if (!userLogic.checkExistUser(uName, pass)) {
					listError.add(MessageProperties.getMsg("er016_name_pass_wrong"));
				}
			}
		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		}
		return listError;
	}
}
