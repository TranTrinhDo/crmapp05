package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enumdata.RoleName;

//các đường dẫn để kích hoạt filter
@WebFilter(filterName = "authorFilter", urlPatterns = { "/*" })
public class AuthorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");

//		Cookie[] cookies = req.getCookies();
//		String roleUser = "";
//		for (int i = 0; i < cookies.length; i++) {
//			String name = cookies[i].getName();
//			String value = cookies[i].getValue();
//			if(name.equals("role")) {
//				roleUser = value;
//				break;
//			}
//		}
//		//lấy đường dẫn mà client đang gọi
//		String path = req.getServletPath();
//		String contextPath = req.getContextPath();
//		
//		if(roleUser.equals(RoleName.ADMIN.getName())) {
//			if(path.equals("/add-user")|| path.equals("/add-project")) {
//				chain.doFilter(request, response);
//			}else {
//				resp.sendRedirect(contextPath+ "/login");
//			}
//			
//		} else if(roleUser.equals(RoleName.LEAD.getName())) {
//			if(path.equals("/add-project")) {
//				chain.doFilter(request, response);
//			} else {
//				resp.sendRedirect(contextPath+ "/login");
//			}
//		}

		HttpSession session = req.getSession();
		if (session.getAttribute("isLogin") != null && !session.getAttribute("isLogin").equals("")) {
			// Đã login
			boolean isLogin = (boolean) session.getAttribute("isLogin");
			if (isLogin) {
				// chuyển về page chỉ định
				if (req.getServletPath().equals("/login")) {
					// nếu là trang login
					resp.sendRedirect(req.getContextPath() + "/dashboard");
				} else {
					chain.doFilter(request, response);
				}
			} else {
				// chuyển về page login
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		} else {
			// Chưa login
			// Chuyển về page login
			if (req.getServletPath().equals("/login")) {
				// Nếu là trang login
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(req.getContextPath() + "/login");
			}
		}
	}

}