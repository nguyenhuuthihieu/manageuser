/**
 * Copyright(C) 2019  Luvina Software Company
 * ConfigProperties.java, 2019-11-16 NguyenHuuThiHieu
 */
package manageuser.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import manageuser.utils.Constant;

/**
 * Đọc dữ liệu trong file config.properties
 * @author NguyenHuuThiHieu
 */
public class ConfigProperties {
	private static Properties prop = new Properties();
	/**
	 * Đọc dữ liệu từ file database.properties
	 */
	static {
		try {
			prop.load(new InputStreamReader(MessageProperties.class.getClassLoader()
					.getResourceAsStream(Constant.FILE_PATH_CONFIG),"UTF-8"));
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
	public static String getInforConfig(String key) {
		return prop.getProperty(key);
	}
}
