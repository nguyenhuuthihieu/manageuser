/**
 * Copyright(C) 2019  Luvina Software Company
 * DataCommon.java, 2019-11-14 NguyenHuuThiHieu
 */
package manageuser.utils;

/**
 * Chứa các dữ liệu dùng chung
 * @author NguyenHuuThiHieu
 */
public class Constant {
	// Đường dẫn đến file jsp
	public static final String PATH_ADM001 = "/view/jsp/ADM001.jsp";
	public static final String PATH_ADM002 = "/view/jsp/ADM002.jsp";
	public static final String PATH_ADM003 = "/view/jsp/ADM003.jsp";
	public static final String PATH_ADM004 = "/view/jsp/ADM004.jsp";
	public static final String PATH_ADM005 = "/view/jsp/ADM005.jsp";
	public static final String PATH_ADM006 = "/view/jsp/ADM006.jsp";
	public static final String PATH_SYS_ERROR = "/view/jsp/System_Error.jsp";
	// Đường dẫn dến file properties
	public static final String FILE_PATH_MSG = "./properties/message_ja.properties";
	public static final String FILE_PATH_ERR_MSG = "./properties/message_error_ja.properties";
	public static final String FILE_PATH_DB_CONFIG = "./properties/database.properties";
	public static final String FILE_PATH_CONFIG = "./properties/config.properties";
	// Loại đầu vào list user
	public static final String TYPE_DEFAULT = "DEFAULT";
	public static final String TYPE_PAGE = "SELECT_PAGE";
	public static final String TYPE_SEARCH = "SEARCH";
	public static final String TYPE_SORT = "SORT";
	public static final String TYPE_BACK = "BACK";
	// Các thuộc tính trên session
	public static final String SESSION_FULL_NAME = "full_name";
	public static final String SESSION_GROUP_ID = "group_id";
	public static final String SESSION_PAGE = "page";
	public static final String SESSION_SORT_TYPE = "sort_type";
	public static final String SESSION_SORT_FULL_NAME = "sort_full_name";
	public static final String SESSION_SORT_CODE_LEVEL = "sort_code_level";
	public static final String SESSION_SORT_END_DATE = "sort_end_date";
}