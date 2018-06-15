package kr.maxerve.admin.account.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.account.vo.ReqManagerMemberVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ManagerMenuDTO;

/**
* ManagerMemberDAO.java
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
@Repository("managerMemberDAO")
public class ManagerMemberDAO extends BaseDAOSupport {
	/**
	 * 회원정보 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateMyinfo(ManagerMemberDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.account.managerMemberDAO.updateMyinfo", vo);
	}

	/**
	 * 회원수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectMemberCount(ReqManagerMemberVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.account.managerMemberDAO.selectMemberCount", vo);
	}

	/**
	 * 회원목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ManagerMemberDTO> selectMemberList(ReqManagerMemberVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.account.managerMemberDAO.selectMemberList", vo);
	}

	/**
	 * 회원정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ManagerMemberDTO selectMemberInfo(ManagerMemberDTO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.account.managerMemberDAO.selectMemberInfo", vo);
	}

	/**
	 * 메뉴권한목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ManagerMenuDTO> selectMenuAuthList(ManagerMenuDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.account.managerMemberDAO.selectMenuAuthList", vo);
	}

	/**
	 * 메뉴권한그룹목록
	 * @param mngrMbrIdx
	 * @return
	 * @throws Exception
	 */
	public List<ManagerMenuDTO> selectMenuAuthGrpList(String mngrMbrIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.account.managerMemberDAO.selectMenuAuthGrpList", mngrMbrIdx);
	}

	/**
	 * 메뉴권한삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteMenuAuth(ManagerMenuDTO vo) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.account.managerMemberDAO.deleteMenuAuth", vo);
	}

	/**
	 * 메뉴권한등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMenuAuth(ManagerMenuDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.account.managerMemberDAO.insertMenuAuth", vo);
	}

	/**
	 * 아이디 존재여부
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean selectIsExistId(ManagerMemberDTO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.account.managerMemberDAO.selectIsExistId", vo);
	}

	/**
	 * 회원등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMember(ManagerMemberDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.account.managerMemberDAO.insertMember", vo);
	}

	/**
	 * 회원정보수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateMember(ManagerMemberDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.account.managerMemberDAO.updateMember", vo);
	}
	
	/**
	 * 회원삭제
	 * @param mngrMbrIdx
	 * @throws Exception
	 */
	public void deleteMember(String mngrMbrIdx) throws Exception {
		getSqlSession().update("kr.maxerve.admin.account.managerMemberDAO.deleteMember", mngrMbrIdx);
	}
}
