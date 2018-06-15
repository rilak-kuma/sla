package kr.maxerve.admin.framework;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class AjaxSessionTimeoutFilter implements Filter {
	private String ajaxHeader;
	

	public String getAjaxHeader() {
		return ajaxHeader;
	}

	public void setAjaxHeader(String ajaxHeader) {
		this.ajaxHeader = ajaxHeader;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if (!req.getRequestURI().endsWith(".json")) {
			chain.doFilter(req, res);
		} else {
			try {
				chain.doFilter(req, res);
			} catch (AccessDeniedException e) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
			} catch (AuthenticationException e) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
	}

	public void destroy() {
		
	}
}
