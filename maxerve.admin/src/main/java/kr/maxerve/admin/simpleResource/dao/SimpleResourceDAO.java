package kr.maxerve.admin.simpleResource.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.SimpleResourceDTO;

/**
* SimpleResourceDAO
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
@Repository("simpleResourceDAO")
public class SimpleResourceDAO extends BaseDAOSupport {
	/**
	 * 단순리소스 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SimpleResourceDTO> selectList(SimpleResourceDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.simpleResource.simpleResourceDAO.selectList", vo);
	}

	/**
	 * 단순리소스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insert(SimpleResourceDTO vo) throws Exception {
		getSqlSession().selectList("kr.maxerve.admin.simpleResource.simpleResourceDAO.insert", vo);
	}

	/**
	 * 단순리소스 삭제
	 * @param vo
	 * @throws Exception
	 */
	public void delete(SimpleResourceDTO vo) throws Exception {
		getSqlSession().selectList("kr.maxerve.admin.simpleResource.simpleResourceDAO.delete", vo);
	}

	/**
	 * 단순리소스정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String selectInfo(SimpleResourceDTO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.simpleResource.simpleResourceDAO.selectInfo", vo);
	}
}
