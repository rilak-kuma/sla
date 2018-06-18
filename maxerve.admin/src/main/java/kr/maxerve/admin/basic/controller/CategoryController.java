package kr.maxerve.admin.basic.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.maxerve.admin.basic.service.CategoryService;
import kr.maxerve.admin.basic.vo.ReqCategoryDVO;
import kr.maxerve.admin.basic.vo.ReqCategoryIVO;
import kr.maxerve.admin.basic.vo.ReqCategoryMVO;
import kr.maxerve.admin.basic.vo.ReqCategoryUVO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.FileDTO;

/**
* CategoryController
* @author LEEC.J
* @since 2018.06.19
* @version 1.0
* @see
*
* <pre>
* 카테고리 관리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.19     LEEC.J        최초 생성
* 2017.01.10     정재훈        회원삭제 추가
* </pre>
*/
@Controller
@RequestMapping(value="/basic/category")
public class CategoryController {
	@Resource(name="categoryService")
	private CategoryService categoryService;

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name="fileService")
	private FileService fileService;

	/**
	 * 카테고리 관리 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public void selectList(CategoryDTO vo, ModelMap model) throws Exception {
		String ctgrTypCd = vo.getCtgrTypCd();

		if (StringUtils.isEmpty(ctgrTypCd)) {
			ctgrTypCd = CommonCodeUtil._009._001.getValue();
			vo.setCtgrTypCd(ctgrTypCd);
		}

		// 카테고리 분류 목록
		CommonCodeDTO param1 = new CommonCodeDTO();
		param1.setDep1(CommonCodeUtil._009.ROOT.getValue());

		List<CommonCodeDTO> typeList = commonCodeService.selectList(param1);

		CategoryDTO param = new CategoryDTO();
		param.setCtgrTypCd(ctgrTypCd);

		// 카테고리 목록
		List<CategoryDTO> categoryList = categoryService.selectList(param);

		// 자료유형 공통코드
		List<CommonCodeDTO> referenceTypeList = commonCodeService.selectList(CommonCodeUtil._004.ROOT.getValue());

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
		model.addAttribute("typeList", typeList);
		model.addAttribute(categoryList);
		model.addAttribute(referenceTypeList);
	}

	/**
	 * 카테고리 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void insert(@Valid ReqCategoryIVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		CategoryDTO categoryInfo = null;
		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			categoryInfo = categoryService.insert(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("categoryInfo", categoryInfo);
	}

	/**
	 * 카테고리 정보
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void selectInfo(CategoryDTO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		CategoryDTO categoryInfo = categoryService.selectInfo(vo);

		FileDTO param = new FileDTO();
		param.setFilePath(categoryInfo.getCtgrImg());

		FileDTO fileInfo =  fileService.selectFile(param);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("categoryInfo", categoryInfo);
		model.addAttribute("fileInfo", fileInfo);
	}

	/**
	 * 카테고리 수정
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public void update(@Valid ReqCategoryUVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			categoryService.update(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 카테고리 삭제
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public void delete(@Valid ReqCategoryDVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			categoryService.delete(vo.getCtgrIdx(), vo.getCtgrTypCd());
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 카테고리 순서변경
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/update_order", method=RequestMethod.POST)
	public void updateOrder(ReqCategoryMVO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		categoryService.updateOrder(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
