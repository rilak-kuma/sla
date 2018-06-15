package kr.maxerve.admin.framework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service("extraParamSource")
class ExtraParamSource implements AuthenticationDetailsSource<HttpServletRequest, ExtraParam> {

	public ExtraParam buildDetails(HttpServletRequest context) {
		
		return new ExtraParam(context);
	}
}