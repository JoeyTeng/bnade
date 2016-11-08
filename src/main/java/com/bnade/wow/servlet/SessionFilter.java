package com.bnade.wow.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.bnade.wow.po.User;

@WebFilter(urlPatterns = "/page/user/*")
public class SessionFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {
			req.setAttribute("title", "用户未登录");
			req.setAttribute("message", "用户未登录");
			req.getServletContext().getRequestDispatcher("/message.jsp").forward(req, resp);
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
