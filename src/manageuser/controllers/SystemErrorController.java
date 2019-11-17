/**
 * Copyright(C) 2019  Luvina Software Company
 * SystemErrorController.java, 2019-11-13 NguyenHuuThiHieu
 */
package manageuser.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manageuser.properties.MessageErrorProperties;
import manageuser.utils.Constant;

/**
 * Thực hiện xử lí khi có lỗi hệ thống
 * Servlet implementation class SystemErrorController
 */
@WebServlet()
public class SystemErrorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Xử lí khi hệ thống có lỗi
	 * @throws ServletException 
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = MessageErrorProperties.getMsgError("er015_system_error");
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(Constant.PATH_SYS_ERROR);
		dispatcher.forward(request, response);
	}
}
