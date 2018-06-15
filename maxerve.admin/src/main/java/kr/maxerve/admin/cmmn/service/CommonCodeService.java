package kr.maxerve.admin.cmmn.service;

import java.util.List;

import kr.maxerve.admin.cmmn.vo.MultiCommonCodeVO;
import kr.maxerve.dto.CommonCodeDTO;

/**
* CommonCodeService
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 공통코드
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/


public interface CommonCodeService {
	/**
	 * 공통코드 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CommonCodeDTO> selectList(CommonCodeDTO vo) throws Exception;
	public List<CommonCodeDTO> selectList(String dep1) throws Exception;

	/**
	 * 2depth용 공통코드 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MultiCommonCodeVO> selectList2(CommonCodeDTO vo) throws Exception;
}
