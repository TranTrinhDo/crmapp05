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
			String email = "";
			String password = "";

			// lấy toàn bộ cookie mà client truyền lên
			Cookie[] cookies = req.getCookies();

			// duyệt qua từng cookie mà client truyền lên
			for (int i = 0; i < cookies.length; i++) {
				// lấy tên cookie duyệt được và lưu vào trong biến cookieName
				String cookieName = cookies[i].getName();
				// lấy giá trị cookie duyệt được và lưu vào trong biến cookieValue
				String cookieValue = cookies[i].getValue();
				if (cookieName.equals("email")) {
					email = cookieValue;
				}

				if (cookieName.equals("passsword")) {
					password = cookieValue;
				}
			}

			req.setAttribute("email", email);
			req.setAttribute("password", password);
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		boolean isLogin = loginService.checkLogin(email, password);
		String remember = req.getParameter("remember");
		List<User> list = loginService.getListUserByEmailAndPassWord(email, password);
		try {
			if (isLogin == true) {
				System.out.println("Đăng nhập thành công");
				Cookie ckRole = new Cookie("role", String.valueOf(list.get(0).getRole().getId()));
				resp.addCookie(ckRole);
				if (remember != null) {
					// người dùng có checkbox lưu mật khẩu

					// Tạo ra cookie tên là email
					Cookie ckUserName = new Cookie("email", email);
					// tạo ra cookie lưu password
					Cookie ckPassWord = new Cookie("password", password);

					// server yêu cầu client tạo ra 2 cookie và lưu phía client
					resp.addCookie(ckUserName);
					resp.addCookie(ckPassWord);
				}
				resp.sendRedirect("dashboard");

			} else {
				resp.setContentType("text/html;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<b>Đăng nhập thất bại. Vui lòng kiểm tra email hoặc mật khẩu.</b></font>");
				RequestDispatcher rd = req.getRequestDispatcher("login");
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
