package kr.maxerve.admin.login.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import kr.maxerve.admin.account.dao.ManagerMemberDAO;
import kr.maxerve.admin.login.dao.LoginDAO;
import kr.maxerve.admin.login.service.LoginService;
import kr.maxerve.admin.login.vo.LoginVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.dto.ManagerMenuDTO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name="loginDAO")
	private LoginDAO loginDAO;

	@Resource(name="propertiesService")
	private Properties propertiesService;

	@Resource(name="managerMemberDAO")
	private ManagerMemberDAO managerMemberDAO;

	public ManagerMemberMVO selectLoginInfo(LoginVO vo) throws Exception {
		return loginDAO.selectLoginInfo(vo);
	}

	public Authentication selectLoginAuth(ManagerMemberMVO vo) throws Exception {
		String uid = vo.getMngrMbrId();

		ManagerMenuDTO param = new ManagerMenuDTO();
		param.setMngrMbrIdx(vo.getMngrMbrIdx());

		List<ManagerMenuDTO> menuList = managerMemberDAO.selectMenuAuthList(param);

		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));

		for (ManagerMenuDTO menuInfo : menuList) {
			roles.add(new SimpleGrantedAuthority(menuInfo.getMnuCd()));

			if ("Y".equals(menuInfo.getWrtYn())) {
				roles.add(new SimpleGrantedAuthority(menuInfo.getMnuCd() + "Y"));
			}
		}

		List<ManagerMenuDTO> menuGroupList = managerMemberDAO.selectMenuAuthGrpList(vo.getMngrMbrIdx());

		for (ManagerMenuDTO menuInfo : menuGroupList) {
			roles.add(new SimpleGrantedAuthority(menuInfo.getMnuCd()));
		}

		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(uid, "", roles);

		result.setDetails(vo);

		return result;
	}
}
