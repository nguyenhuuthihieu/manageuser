/**
 * Copyright(C) 2019  Luvina Software Company
 * LoginController.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manageuser.utils.Common;
import manageuser.utils.Constant;
import manageuser.validates.ValidateUser;

/**
 * Controller xử lý cho màn hình login
 *  @author NguyenHuuThiHieu
 */
@WebServlet()
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Thực hiện xử lí khi bấm vào button đăng nhập
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			String uName = request.getParameter("loginId");
			String pass = request.getParameter("password");
			ValidateUser validateUser = new ValidateUser();
			List<String> listError;
			listError = validateUser.validateLogin(uName, pass);
			if (listError.isEmpty()) {
				HttpSession session = request.getSession();
		        session.setAttribute("LOGIN_NAME", uName);
		        String contextPath = request.getContextPath();
		        response.sendRedirect(contextPath + "/listUser.do?type=DEFAULT");
			} else {
				request.setAttribute("uName", Common.encodeHTML(uName));
				request.setAttribute("listError", listError);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constant.PATH_ADM001);
				dispatcher.forward(request,response);
			}
		} catch (Exception e) {
			System.out.println(this.getClass() + e.getMessage());
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/error.do");
		}
	}
}
