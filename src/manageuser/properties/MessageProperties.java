/**
 * Copyright(C) 2019  Luvina Software Company
 * MSGProperties.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import manageuser.utils.Constant;



/**
 * Đọc các thông tin message động
 * @author NguyenHuuThiHieu
 */
public class MessageProperties {
	private static Properties prop = new Properties();
	/**
	 * Đọc file message động
	 */
	static { 
		try {
			prop.load(new InputStreamReader(MessageProperties.class.getClassLoader()
												.getResourceAsStream(Constant.FILE_PATH_MSG),"UTF-8"));
        } catch (IOException ex) {
           	System.out.println(ex.getMessage());
           	throw new RuntimeException(ex.getMessage());
        }
	}
	/**
	 * Lấy ra message động tương ứng với key truyền vào 
	 *@param keyMsg
	 *@return
	 */
	public static String getMsg(String keyMsg) {
		return prop.getProperty(keyMsg);
	}
}
