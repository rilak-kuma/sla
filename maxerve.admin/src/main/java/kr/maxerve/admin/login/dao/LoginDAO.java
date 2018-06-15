package kr.maxerve.admin.login.dao;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.login.vo.LoginVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;

@Repository("loginDAO")
public class LoginDAO extends BaseDAOSupport {
	public ManagerMemberMVO selectLoginInfo(LoginVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.login.loginDAO.selectLoginInfo", vo);
	}
}
