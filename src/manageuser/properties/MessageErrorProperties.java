/**
 * Copyright(C) 2019  Luvina Software Company
 * MessageErrorProperties.java.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import manageuser.utils.Constant;

/**
 *	Đọc các thông tin về các câu thông báo, lỗi của hệ thống
 * @author NguyenHuuThiHieu
 */
public class MessageErrorProperties {
	private static Properties prop = new Properties();
	/**
	 * Đọc file chứa các lỗi hệ thống
	 */
	static {
		try {
			prop.load(new InputStreamReader(MessageProperties.class.getClassLoader()
					.getResourceAsStream(Constant.FILE_PATH_ERR_MSG),"UTF-8"));
        } catch (IOException ex) {
           	System.out.println(ex.getMessage());
           	throw new RuntimeException(ex.getMessage());
        }
	}
	/**
	 * Lấy ra message lỗi tương ứng với key truyền vào 
	 *@param keyMsg
	 *@return
	 */
	public static String getMsgError(String keyMsg) {
		return prop.getProperty(keyMsg);
	}
}
