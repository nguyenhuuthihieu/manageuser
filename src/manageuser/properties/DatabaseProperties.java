/**
 * Copyright(C) 2019  Luvina Software Company
 * DatabaseProperties.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import manageuser.utils.Constant;

/**
 * Đọc file DB.properties
 * @author NguyenHuuThiHieu
 */
public class DatabaseProperties {
	private static Properties prop = new Properties();
	/**
	 * Đọc dữ liệu từ file database.properties
	 */
	static {
		try {
			prop.load(new InputStreamReader(MessageProperties.class.getClassLoader()
					.getResourceAsStream(Constant.FILE_PATH_DB_CONFIG),"UTF-8"));
        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        	throw new RuntimeException(ex.getMessage());
        }
	}
	/**
	 * Lấy ra thông tin tương ứng với key truyền vào 
	 *@param key
	 *@return
	 */
	public static String getInforDB(String key) {
		return prop.getProperty(key);
	}
}
