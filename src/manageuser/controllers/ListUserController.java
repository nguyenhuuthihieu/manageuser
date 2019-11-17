package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.UserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.ConfigProperties;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller xử lý cho màn hình danh sách user ADM002
 * @author NguyenHuuThiHieu
 */
@WebServlet()
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int limitRowPage = Integer.parseInt(ConfigProperties.getInforConfig("limit_row_page"));
	private int limitPaging = Integer.parseInt(ConfigProperties.getInforConfig("limit_page"));
	private List<Integer> listPaging = null;
	private List<UserInfor> listUsers = null;
	private TblUserLogic userLogic = new TblUserLogicImpl();
	private MstGroupLogic groupLogic = new MstGroupLogicImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			// Nếu đã đăng nhập 
			if (Common.checkLogin(session)) {
				List<MstGroup> listGroup = groupLogic.getAllMstGroup();
				request.setAttribute("listGroup", listGroup);
				String type = request.getParameter("type");
				// Các thông số khai báo ở dạng default
				int currentPage = 1;
				int totalUser = 0;
				int groupId = 0;
				String fullName = "";
				String typeSort = "full_name";
				String sortByFullName = "ASC";
				String sortByCodeLevel = "ASC";
				String sortByEndDate = "DESC";
				// Nếu tìm kiếm 
				if (Constant.TYPE_SEARCH.equals(type)) {
					fullName = request.getParameter("name");
					groupId = Integer.parseInt(request.getParameter("group_id"));
					currentPage = (int) session.getAttribute(Constant.SESSION_PAGE);
					typeSort = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
					sortByFullName = (String) session.getAttribute(Constant.SESSION_SORT_FULL_NAME);
					sortByCodeLevel = (String) session.getAttribute(Constant.SESSION_SORT_CODE_LEVEL);
					sortByEndDate = (String) session.getAttribute(Constant.SESSION_SORT_END_DATE);
				// Nếu chọn paging
				} else if (Constant.TYPE_PAGE.equals(type)) {
					groupId = (int) session.getAttribute(Constant.SESSION_GROUP_ID);
					fullName = (String) session.getAttribute(Constant.SESSION_FULL_NAME);
					currentPage = Integer.parseInt(request.getParameter("page"));
					typeSort = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
					sortByFullName = (String) session.getAttribute(Constant.SESSION_SORT_FULL_NAME);
					sortByCodeLevel = (String) session.getAttribute(Constant.SESSION_SORT_CODE_LEVEL);
					sortByEndDate = (String) session.getAttribute(Constant.SESSION_SORT_END_DATE);
				// Nếu chọn sắp xếp
				} else if (Constant.TYPE_SORT.equals(type)) {
					groupId = (int) session.getAttribute(Constant.SESSION_GROUP_ID);
					fullName = (String) session.getAttribute(Constant.SESSION_FULL_NAME);
					currentPage = (int) session.getAttribute(Constant.SESSION_PAGE);
					typeSort = request.getParameter("type_sort");
					sortByFullName = request.getParameter("full_name");
					sortByCodeLevel = request.getParameter("code_level");
					sortByEndDate = request.getParameter("end_date");
				// Nếu chọn trở về
				} else if (Constant.TYPE_BACK.equals(type)) {
					groupId = (int) session.getAttribute(Constant.SESSION_GROUP_ID);
					fullName = (String) session.getAttribute(Constant.SESSION_FULL_NAME);
					currentPage = (int) session.getAttribute(Constant.SESSION_PAGE);
					typeSort = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
					sortByFullName = (String) session.getAttribute(Constant.SESSION_SORT_FULL_NAME);
					sortByCodeLevel = (String) session.getAttribute(Constant.SESSION_SORT_CODE_LEVEL);
					sortByEndDate = (String) session.getAttribute(Constant.SESSION_SORT_END_DATE);
				}
				totalUser = userLogic.getTotalUsers(groupId, fullName);
				int totalPage = Common.getTotalPage(totalUser, limitRowPage);
				int offsetPage = Common.getOffset(totalPage, limitPaging, currentPage);
				// Nếu số lượng user giảm khi đang giảm quá trang hiện tại đang xem
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				listPaging = Common.getListPaging(totalUser, limitRowPage, currentPage);
				listUsers = userLogic.getListUsers(currentPage, limitRowPage, groupId, fullName, typeSort, sortByFullName, sortByCodeLevel, sortByEndDate);
				session.setAttribute(Constant.SESSION_FULL_NAME, fullName);
				session.setAttribute(Constant.SESSION_GROUP_ID, groupId);
				session.setAttribute(Constant.SESSION_PAGE, currentPage);
				session.setAttribute(Constant.SESSION_SORT_TYPE, typeSort);
				session.setAttribute(Constant.SESSION_SORT_FULL_NAME, sortByFullName);
				session.setAttribute(Constant.SESSION_SORT_CODE_LEVEL, sortByCodeLevel);
				session.setAttribute(Constant.SESSION_SORT_END_DATE, sortByEndDate);
				request.setAttribute("listUsers", listUsers);
				request.setAttribute("listPaging", listPaging);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("maxPageOffset", (offsetPage + 1) * limitPaging);
				request.setAttribute("offsetPage", offsetPage);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("fullName", Common.encodeHTML(fullName));
				request.setAttribute("groupId", groupId);
				request.setAttribute("sortFullName", sortByFullName);
				request.setAttribute("sortCodeLevel", sortByCodeLevel);
				request.setAttribute("sortEndDate", sortByEndDate);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constant.PATH_ADM002);
				dispatcher.forward(request,response);
			// Nếu chưa đăng nhập
			} else {
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath + "/");
			}
		} catch (Exception e) {
			System.out.println(this.getClass() + " method doGet " + e.getMessage());
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/error.do");
		}
	}

	
}
