package ua.lviv.trainapplogos.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ua.lviv.trainapplogos.domain.UserAccount;
import ua.lviv.trainapplogos.dto.UserLogin;
import ua.lviv.trainapplogos.service.UserAccountService;
import ua.lviv.trainapplogos.service.impl.UserAccountServiceImpl;

@WebServlet("/login") //set end-point
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccountService userService = UserAccountServiceImpl.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserAccount user = userService.getUserByEmail(email);
		
		RequestDispatcher redirect;
		
		if (user == null) {
			redirect = request.getRequestDispatcher("login.jsp");
			redirect.forward(request, response);
		} else {
			if (user.getPassword().equals(password)) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userId", user.getId());
				session.setAttribute("role", user.getRole().toString());
				
				UserLogin userLogin = new UserLogin();
				userLogin.destinationUrl = "cabinet.jsp";
				userLogin.userEmail = user.getEmail();
				
			    String json = new Gson().toJson(userLogin);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    response.getWriter().write(json);
			} else {
				redirect = request.getRequestDispatcher("login.jsp");
				redirect.forward(request, response);
			}
		}
	}

}

