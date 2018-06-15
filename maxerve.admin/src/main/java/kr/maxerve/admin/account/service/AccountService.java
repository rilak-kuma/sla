package kr.maxerve.admin.account.service;

import java.util.List;

import kr.maxerve.admin.account.vo.ReqManagerMemberIVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberUVO;
import kr.maxerve.admin.account.vo.ReqManagerMemberVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ManagerMenuDTO;

/**
* AccountService.java
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
public interface AccountService {
	// 권한관리 검색종류
	public static enum searchType {
		NAME("1"),
		ID("2");

		private final String value;

		private searchType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 로그인된 사용자 정보 취득
	 * @return
	 * @throws Exception
	 */
	public ManagerMemberMVO selectMemberInfo() throws Exception;
	/**
	 * 내정보 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateMyinfo(ReqManagerMemberVO vo) throws Exception;
	/**
	 * 회원목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ManagerMemberDTO> selectMemberList(ReqManagerMemberVO vo) throws Exception;

	/**
	 * 회원정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ManagerMemberDTO selectMemberInfo(ManagerMemberDTO vo) throws Exception;

	/**
	 * 메뉴권한목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ManagerMenuDTO> selectMenuAuthList(ManagerMenuDTO vo) throws Exception;
	public List<ManagerMenuDTO> selectMenuAuthList(String mngrMbrIdx) throws Exception;

	/**
	 * 아이디 존재여부
	 * @param mngrMbrId
	 * @return
	 * @throws Exception
	 */
	public boolean selectIsExistId(String mngrMbrId) throws Exception;

	/**
	 * 회원등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMember(ReqManagerMemberIVO vo) throws Exception;

	/**
	 * 회원수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateMember(ReqManagerMemberUVO vo) throws Exception;
	
	/**
	 * 회원삭제
	 * @param mngrMbrIdx
	 * @throws Exception
	 */
	public void deleteMember(String mngrMbrIdx) throws Exception;
}
