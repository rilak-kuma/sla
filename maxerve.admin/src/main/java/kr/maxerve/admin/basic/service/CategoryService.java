package kr.maxerve.admin.basic.service;

import java.util.List;

import kr.maxerve.admin.basic.vo.ReqCategoryMVO;
import kr.maxerve.dto.CategoryDTO;

/**
* CategoryService
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
/**
 * @author khs
 *
 */
public interface CategoryService {

	/**
	 * 카테고리 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<CategoryDTO> selectList(CategoryDTO vo) throws Exception;

	/**
	 * 카테고리 등록
	 * @param vo
	 * @throws Exception
	 */
	public CategoryDTO insert(CategoryDTO vo) throws Exception;

	/**
	 * 카테고리 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public CategoryDTO selectInfo(CategoryDTO vo) throws Exception;

	/**
	 * 카테고리 수정
	 * @param vo
	 * @throws Exception
	 */
	public void update(CategoryDTO vo) throws Exception;

	/**
	 * 카테고리 삭제
	 * @param ctgrIdx
	 * @throws Exception
	 */
	public void delete(String ctgrIdx, String ctgrTypCd) throws Exception;

	/**
	 * 카테고리 순서수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateOrder(ReqCategoryMVO vo) throws Exception;
}
