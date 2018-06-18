package kr.maxerve.admin.basic.service;

import java.util.List;

import kr.maxerve.admin.basic.vo.ReqSimpleResourceIVO;
import kr.maxerve.dto.SimpleResourceDTO;

/**
* SimpleResourceService
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 단순리소스
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
public interface SimpleResourceService {
	/**
	 * 단순리소스 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SimpleResourceDTO> selectList(SimpleResourceDTO vo) throws Exception;

	/**
	 * 단순리소스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insert(ReqSimpleResourceIVO vo) throws Exception;

	/**
	 * 단순리소스정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String selectInfo(SimpleResourceDTO vo) throws Exception;
}
