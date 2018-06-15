package kr.maxerve.admin.account.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.account.dao.ManagerMemberDAO;
import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.account.vo.ReqManagerMemberIVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberUVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.utils.StringUtil;
import kr.maxerve.admin.utils.WebUtil;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ManagerMenuDTO;

/**
* AccountServiceImpl.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name="managerMemberDAO")
	private ManagerMemberDAO managerMemberDAO;

	@Resource(name="propertiesService")
	private Properties propertiesService;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectMemberInfo()
	 */
	public ManagerMemberMVO selectMemberInfo() throws Exception {
		if(WebUtil.getCurrentRequest().isUserInRole("ROLE_USER")) {
			return (ManagerMemberMVO)SecurityContextHolder.getContext().getAuthentication().getDetails();
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#updateMyinfo(kr.maxerve.admin.account.vo.ReqManagerMemberVO)
	 */
	public void updateMyinfo(ReqManagerMemberVO vo) throws Exception {
		if (StringUtils.isEmpty(vo.getMngrMbrPwdNew())) {
			vo.setMngrMbrPwd("");
		} else {
			vo.setMngrMbrPwd(StringUtil.getSha256Enc(vo.getMngrMbrPwdNew()));
		}

		vo.setPhn(vo.getPhn().replace("-", ""));

		managerMemberDAO.updateMyinfo(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectMemberList(kr.maxerve.admin.account.vo.ReqManagerMemberVO)
	 */
	public List<ManagerMemberDTO> selectMemberList(ReqManagerMemberVO vo) throws Exception {
		// 전체 회원수 취득
		int total = managerMemberDAO.selectMemberCount(vo);

		if (vo.getPageInfo() == null) {
			vo.setPageInfo(new PaginationInfo());
		}

		// 페이징
		PaginationInfo pageInfo = vo.getPageInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPage(), 1));
		pageInfo.setTotalRecordCount(total);
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));

		return managerMemberDAO.selectMemberList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectMemberInfo(kr.maxerve.dto.ManagerMemberDTO)
	 */
	public ManagerMemberDTO selectMemberInfo(ManagerMemberDTO vo) throws Exception {
		return managerMemberDAO.selectMemberInfo(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectMenuAuthList(kr.maxerve.dto.ManagerMenuDTO)
	 */
	public List<ManagerMenuDTO> selectMenuAuthList(ManagerMenuDTO vo) throws Exception {
		return managerMemberDAO.selectMenuAuthList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectMenuAuthList(java.lang.String)
	 */
	public List<ManagerMenuDTO> selectMenuAuthList(String mngrMbrIdx) throws Exception {
		ManagerMenuDTO param = new ManagerMenuDTO();
		param.setMngrMbrIdx(mngrMbrIdx);

		return selectMenuAuthList(param);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#selectIsExistId(java.lang.String)
	 */
	public boolean selectIsExistId(String mngrMbrId) throws Exception {
		ManagerMemberDTO param = new ManagerMemberDTO();
		param.setMngrMbrId(mngrMbrId);

		return managerMemberDAO.selectIsExistId(param);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.account.service.AccountService#insertMember(kr.maxerve.dto.ManagerMemberDTO)
	 */
	public void insertMember(ReqManagerMemberIVO vo) throws Exception {
		// 비밀번호 암호화
		vo.setMngrMbrPwd(StringUtil.getSha256Enc(vo.getMngrMbrPwd()));
		
		// 회원등록
		vo.setPhn(vo.getPhn().replace("-", ""));
		managerMemberDAO.insertMember(vo);

		String mngrMbrIdx = vo.getMngrMbrIdx();

		// 권한등록
		for (ManagerMenuDTO menu : vo.getMngrMnuList()) {
			menu.setMngrMbrIdx(mngrMbrIdx);
			managerMemberDAO.insertMenuAuth(menu);
		}
	}

	public void updateMember(ReqManagerMemberUVO vo) throws Exception {
		// 회원수정
		vo.setPhn(vo.getPhn().replace("-", ""));
		managerMemberDAO.updateMember(vo);

		String mngrMbrIdx = vo.getMngrMbrIdx();

		// 권한삭제
		ManagerMenuDTO param = new ManagerMenuDTO();
		param.setMngrMbrIdx(mngrMbrIdx);
		managerMemberDAO.deleteMenuAuth(param);

		// 권한등록
		for (ManagerMenuDTO menu : vo.getMngrMnuList()) {
			menu.setMngrMbrIdx(mngrMbrIdx);
			managerMemberDAO.insertMenuAuth(menu);
		}
	}
	
	public void deleteMember(String mngrMbrIdx) throws Exception {
		// 회원삭제
		managerMemberDAO.deleteMember(mngrMbrIdx);
	}
}
