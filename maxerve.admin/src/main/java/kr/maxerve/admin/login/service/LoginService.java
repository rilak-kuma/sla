package kr.maxerve.admin.login.service;

import org.springframework.security.core.Authentication;

import kr.maxerve.admin.login.vo.LoginVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;

public interface LoginService {
	public ManagerMemberMVO selectLoginInfo(LoginVO vo) throws Exception;
	public Authentication selectLoginAuth(ManagerMemberMVO vo) throws Exception;
}
