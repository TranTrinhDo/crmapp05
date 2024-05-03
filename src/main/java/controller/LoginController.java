package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.MySQLConfig;
import entity.Role;
import entity.User;
import service.LoginService;

@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private LoginService loginService = new LoginService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		if (servletPath.equals("/login")) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		boolean isLogin = loginService.checkLogin(email, password);
		if (isLogin == true) {
			System.out.println("Đăng nhập thành công");
		}

		if (isLogin) {
			HttpSession session = req.getSession();
			session.setAttribute("isLogin", true);
//			session.setMaxInactiveInterval(10 * 60);
			resp.sendRedirect(req.getContextPath() + "/dashboard");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
