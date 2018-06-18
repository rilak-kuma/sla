package kr.maxerve.admin.category.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.CategoryDTO;

/**
* CategoryDAO
* @author LEEC.J
* @since 2018.06.24
* @version 1.0
* @see
*
* <pre>
* 카테고리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.24     LEEC.J        최초 생성
* </pre>
*/
@Repository("categoryDAO")
public class CategoryDAO extends BaseDAOSupport {
	/**
	 * 단순리소스 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> selectList(CategoryDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.category.categoryDAO.selectList", vo);
	}

	/**
	 * 카테고리 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insert(CategoryDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.category.categoryDAO.insert", vo);
	}

	/**
	 * 카테고리 정보
	 * @param ctgrIdx
	 * @return
	 * @throws Exception
	 */
	public CategoryDTO selectInfo(String ctgrIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.category.categoryDAO.selectInfo", ctgrIdx);
	}

	/**
	 * 카테고리 수정
	 * @param vo
	 * @throws Exception
	 */
	public void update(CategoryDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.category.categoryDAO.update", vo);
	}

	/**
	 * 카테고리 삭제
	 * @param ctgrIdx
	 * @throws Exception
	 */
	public void delete(String ctgrIdx) throws Exception {
		getSqlSession().update("kr.maxerve.admin.category.categoryDAO.delete", ctgrIdx);
	}

	/**
	 * 카테고리 순위 재배치
	 * @param ctgrTypCd
	 * @throws Exception
	 */
	public void updateRank(String ctgrTypCd) throws Exception {
		getSqlSession().update("kr.maxerve.admin.category.categoryDAO.updateRank", ctgrTypCd);
	}

	/**
	 * 카테고리 순서수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateOrder(CategoryDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.category.categoryDAO.updateOrder", vo);
	}
}
