package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import service.UserService;

@WebServlet(name="dashboardController",urlPatterns= {"/dashboard"})
public class DashboardController extends HttpServlet{
	private UserService userService = new UserService();

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<User> list = userService.getListUser();
	req.setAttribute("listUser", list);
	req.getRequestDispatcher("index.jsp").forward(req, resp);
}
}
