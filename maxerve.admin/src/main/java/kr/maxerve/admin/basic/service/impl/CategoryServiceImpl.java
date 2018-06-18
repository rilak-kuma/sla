package kr.maxerve.admin.basic.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.basic.service.CategoryService;
import kr.maxerve.admin.basic.vo.ReqCategoryMVO;
import kr.maxerve.admin.category.dao.CategoryDAO;
import kr.maxerve.admin.file.dao.FileDAO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.dto.CategoryDTO;

/**
* CategoryServiceImpl
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
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource(name="categoryDAO")
	private CategoryDAO categoryDAO;

	@Resource(name="fileDAO")
	private FileDAO fileDAO;

	@Resource(name="propertiesService")
	private Properties propertiesService;

	@Resource(name="fileService")
	private FileService fileService;


	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.CategoryService#selectList(kr.maxerve.dto.CategoryDTO)
	 */
	@Override
	public List<CategoryDTO> selectList(CategoryDTO vo) throws Exception {
		return categoryDAO.selectList(vo);
	}


	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.CategoryService#insert(kr.maxerve.dto.CategoryDTO)
	 */
	@Override
	public CategoryDTO insert(CategoryDTO vo) throws Exception {
		String moveFile = fileService.moveFile(vo.getCtgrImg());

		vo.setCtgrImg(moveFile);

		categoryDAO.insert(vo);

		// 카테고리 순위 재배치
		categoryDAO.updateRank(vo.getCtgrTypCd());

		return categoryDAO.selectInfo(vo.getCtgrIdx());
	}


	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.CategoryService#selectInfo(kr.maxerve.dto.CategoryDTO)
	 */
	@Override
	public CategoryDTO selectInfo(CategoryDTO vo) throws Exception {
		return categoryDAO.selectInfo(vo.getCtgrIdx());
	}


	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.CategoryService#update(kr.maxerve.dto.CategoryDTO)
	 */
	@Override
	public void update(CategoryDTO vo) throws Exception {
		String moveFile = fileService.moveFile(vo.getCtgrImg());

		vo.setCtgrImg(moveFile);

		categoryDAO.update(vo);
	}


	/* (non-Javadoc)
	 * @see kr.maxerve.admin.basic.service.CategoryService#delete(java.lang.String)
	 */
	@Override
	public void delete(String ctgrIdx, String ctgrTypCd) throws Exception {
		categoryDAO.delete(ctgrIdx);

		// 카테고리 순위 재배치
		categoryDAO.updateRank(ctgrTypCd);
	}

	/**
	 * 케타고리 순서수정
	 * @param vo
	 * @throws Exception
	 */
	@Override
	public void updateOrder(ReqCategoryMVO vo) throws Exception {
		CategoryDTO param = new CategoryDTO();
		param.setCtgrIdx(vo.getCtgrIdx());
		param.setCtgrOrd(vo.getCtgrOrd());

		categoryDAO.updateOrder(param);

		param.setCtgrIdx(vo.gettCtgrIdx());
		param.setCtgrOrd(vo.gettCtgrOrd());

		categoryDAO.updateOrder(param);
	}
}
