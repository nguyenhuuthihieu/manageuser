/**
 * Copyright(C) 2019  Luvina Software Company
 * Common.java, 2019-11-14 NguyenHuuThiHieu
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import manageuser.properties.ConfigProperties;

/**
 * Chứa các phương thức dùng chung
 * @author NguyenHieu
 */
public class Common {
	/**
	 * Thực hiện mã hóa mật khẩu
	 * @param pass mật khẩu
	 * @param salt muối trong DB
	 * @return chuỗi đã được mã hóa
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptPassword(String pass, String salt) throws NoSuchAlgorithmException {
		// Khai báo mật khẩu mã hóa
		String generatedPassword = null;
        try {
        	// Khai báo MessageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // Thêm muối vào MessageDigest
            md.update(salt.getBytes());
            // Mã hóa mật khẩu
            byte[] passBytes = md.digest(pass.getBytes());
            // Khai báo StringBuilder chứa pass được mã hóa
            StringBuilder password = new StringBuilder();
            // Duyệt qua tất cả các byte trong passBytes
            for(int index = 0; index < passBytes.length ; index++)
            {
            	// Chuyển byte về String và thêm vào password
            	password.append(Integer.toString((passBytes[index] & 0xff) + 0x100, 16).substring(1));
            }
            // Chuyển về dạng String
            generatedPassword = password.toString();
        // Bắt ngoại lệ NoSuchAlgorithmException
        } catch (NoSuchAlgorithmException e) {
        	// Hiển thị ra ngoại lệ
            System.out.println(e.getMessage());
            // Ném ngoại lệ đi
            throw e;
        }
        // Trả về mật khẩu đã được mã hóa
        return generatedPassword;
	}
	/**
	 * So sánh giữa password trong DB và pass nhập vào đã được mã hóa
	 * @param passDB mật khẩu được lấy từ DB ra
	 * @param passInput mật khẩu do người dùng nhập vào đã được mã hóa
	 * @return bằng hay không
	 */
	public static Boolean comparePassword(String passDB, String passInput) {
		return passDB.equals(passInput);
	}
	/**
	 * Xử lí dữ liệu đầu vào để tránh lỗ hổng XSS
	 * @param str chuỗi cần mã hóa
	 * @return chuỗi đã được mã hóa
	 */
	public static String encodeHTML(String str) {
		if (str != null) {
			StringBuilder encodeStr = new StringBuilder();
			for (int index = 0; index < str.length(); index++) {
				char character = str.charAt(index);
				switch (character) {
					case 34:
						encodeStr.append("&quot;");
						break;
					case 38:
						encodeStr.append("&amp;");
						break;
					case 39:
						encodeStr.append("&#x27;");
						break;
					case 47:
						encodeStr.append("&#x2F;");
						break;
					case 60:
						encodeStr.append("&lt;");
						break;
					case 62:
						encodeStr.append("&gt;");
						break;
					default :
						encodeStr.append(character);
						break;
				}
			}
			str = encodeStr.toString();
		}
		return str;
	}
	/**
	 * Thực hiện encode sql
	 * @param strEncode chuỗi cần mã hóa
	 * @return chuỗi đã được mã hóa
	 */
	public static String encodeSQL(String str) {
		if (str != null) {
			StringBuilder encodeStr = new StringBuilder();
			for (int index = 0; index < str.length(); index++) {
				char character = str.charAt(index);
				switch (character) {
					case 37:
						encodeStr.append("//%");
						break;
					case 39:
						encodeStr.append("//_");
						break;
					case 95:
						encodeStr.append("//'");
						break;
					default :
						encodeStr.append(character);
						break;
				}
			}
			str = encodeStr.toString();
		}
		return str;
	}
	/**
	 * Kiểm tra đã login chưa
	 * @param session 
	 * @return đăng nhập hay chưa
	 */
	public static Boolean checkLogin(HttpSession session) {
		return !(session.getAttribute("LOGIN_NAME") == null);
	}
	/**
	 * Tạo chuỗi paging
	 * @param totalRecords tổng số record
	 * @param limit số bản ghi trong 1trang
	 * @param currentPage trang hiện tại
	 * @return
	 */
	public static List<Integer> getListPaging(int totalRecords, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<Integer>();
		int totalPage = getTotalPage(totalRecords, limit);
		int limitPage = Integer.parseInt(ConfigProperties.getInforConfig("limit_page"));
		if (totalPage > 1) {
			int offsetId = getOffset(totalPage, limitPage, currentPage);
			int pageId = offsetId * limitPage + 1;
			while (listPage.size() < limitPage && pageId <= totalPage) {
				listPage.add(pageId);
				pageId++;
			} 
		}
		return listPage;
	}
	/**
	 * Lấy ra offset paging hiện tại
	 * @param totalPages tổng số page
	 * @param currentPage page hiện tại
	 * @param limitPage số page trong 1 paging
	 * @return
	 */
	public static int getOffset(int totalPages, int limitPage, int currentPage) {
		int offsetId = 0;
		if (currentPage % limitPage == 0) {
			offsetId = currentPage / limitPage - 1;
		} else {
			offsetId = currentPage / limitPage;
		}
		return offsetId;
	}
	/**
	 * Định dạng cho ngày
	 * @param date ngày cần định dạng
	 * @return
	 */
	public static String formatDate(Date date) {
		String dateStr = "";
		if (date != null) {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			dateStr = format.format(date);
		}
		return dateStr;
	}
	/**
	 * @param totalRecords Tổng records
	 * @param limit số records trên 1 trang
	 * @return
	 */
	public static int getTotalPage(int totalRecords, int limit) {
		return totalRecords / limit + ((totalRecords % limit == 0) ? 0 : 1); 
	}
}
