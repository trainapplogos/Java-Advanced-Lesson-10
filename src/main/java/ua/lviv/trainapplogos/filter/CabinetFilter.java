package ua.lviv.trainapplogos.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import ua.lviv.trainapplogos.domain.UserRole;
import ua.lviv.trainapplogos.shared.FilterService;

@WebFilter("/cabinet.jsp")
public class CabinetFilter implements Filter {
private FilterService filterService = FilterService.getFilterService();
	
	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		filterService.doFilterValidation(request, response, chain, Arrays.asList(UserRole.USER, UserRole.ADMINISTRATOR));
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}

/*
http://localhost:8080/JA-Lesson-10-Homework/cabinet.jsp
http://localhost:8080/JA-Lesson-10-Homework/product?id=3
http://localhost:8080/JA-Lesson-10-Homework/createProduct.jsp
http://localhost:8080/JA-Lesson-10-Homework/bucket.jsp

 */
