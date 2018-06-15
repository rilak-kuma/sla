package kr.maxerve.admin.framework;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import kr.maxerve.admin.login.service.LoginService;
import kr.maxerve.admin.login.vo.LoginVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.utils.StringUtil;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="loginService")
	private LoginService loginService;

	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		String uid = (String)arg0.getPrincipal();
		String upw = (String)arg0.getCredentials();

		LoginVO searchParam = new LoginVO();
		searchParam.setUid(uid);
		try {
			searchParam.setPwd(StringUtil.getSha256Enc(upw));
		} catch (Exception e1) {
			searchParam.setPwd(upw);
		}

		ManagerMemberMVO memberInfo = null;
		try {
			memberInfo = loginService.selectLoginInfo(searchParam);
		} catch (Exception e) {
			logger.error("error", e);
			throw new BadCredentialsException("Bad credentials");
		}

		if (memberInfo != null) {
			try {
				return loginService.selectLoginAuth(memberInfo);
			} catch (AuthenticationException ex) {
				throw ex;
			} catch (Exception ex) {
				throw new AuthenticationCredentialsNotFoundException("Bad credentials");
			}
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}

	public boolean supports(Class<?> arg0) {
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}
