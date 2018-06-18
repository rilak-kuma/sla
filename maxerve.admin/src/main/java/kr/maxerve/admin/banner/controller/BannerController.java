package kr.maxerve.admin.banner.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.banner.service.BannerService;
import kr.maxerve.admin.banner.vo.BannerIVO;
import kr.maxerve.admin.banner.vo.BannerMVO;
import kr.maxerve.admin.banner.vo.BannerUVO;
import kr.maxerve.admin.banner.vo.ReqBannerVO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.FileDTO;

/**
* BannerController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 배너관리
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/

@Controller
@RequestMapping(value="/banner")
public class BannerController {

	@Resource(name="bannerService")
	private BannerService bannerService;

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name="fileService")
	private FileService fileService;

	/**
	 * 메인배너등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertMainBanner", method=RequestMethod.POST)
	public void insertMainBanner(@Valid BannerIVO vo, BindingResult bindingResult, ModelMap model) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._011.getValue());
		bannerService.insertBanner(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 메인배너목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/mainBannerList")
	public void selectMainBannerList(ModelMap model, ReqBannerVO vo) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._011.getValue());

		if(StringUtils.isEmpty(vo.getPlfCd())){
			vo.setPlfCd(CommonCodeUtil._027._001.getValue());
		}

		//전체 배너 목록
		List<BannerMVO> bannerList = bannerService.selectBannerList(vo);

		//진행중 배너 목록
		List<BannerMVO> useBannerList = bannerService.selectUseBannerList(vo);

		//플랫폼리스트
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._027.ROOT.getValue());
		List<CommonCodeDTO> plfList = commonCodeService.selectList(voA);

		model.addAttribute("searchVO",vo);
		model.addAttribute("plfList",plfList);
		model.addAttribute("useBannerList",useBannerList);
		model.addAttribute("bannerList",bannerList);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 메인배너 상세화면
	 * @param model
	 * @param bnnrIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/mainBannerInfo")
	public void selectMainBannerInfo(ModelMap model, String bnnrIdx) throws Exception{

		BannerMVO bannerVO = new BannerMVO();
		FileDTO fileInfo = new FileDTO();

		if(StringUtils.isNotEmpty(bnnrIdx)){
			bannerVO = bannerService.selectBannerInfo(bnnrIdx);
			//이미지배너 파일
			if(StringUtils.isNotEmpty(bannerVO.getFilePath())){
				FileDTO param = new FileDTO();
				param.setFilePath(bannerVO.getFilePath());

				fileInfo =  fileService.selectFile(param);
			}
		}

		ReqBannerVO vo = new ReqBannerVO();
		vo.setBnnrLocCd(CommonCodeUtil._010._011.getValue());
		vo.setDep1(CommonCodeUtil._027.ROOT.getValue());

		//배너에 적용되어있는 최종순번 조회
		List<BannerMVO> ordList = bannerService.selectOrdList(vo);

		model.addAttribute("ordList",ordList);
		model.addAttribute("bannerVO",bannerVO);
		model.addAttribute("fileInfo",fileInfo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 메인배너 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMainBanner", method=RequestMethod.POST)
	public void updateMainBanner(ModelMap model, @Valid  BannerUVO vo, BindingResult bindingResult) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._011.getValue());
		bannerService.updateBanner(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 혁신멤버 배너목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/innoBannerList")
	public void selectInnoBannerList(ModelMap model, ReqBannerVO vo) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._001.getValue());

		if(StringUtils.isEmpty(vo.getPlfCd())){
			vo.setPlfCd(CommonCodeUtil._027._001.getValue());
		}

		//전체 배너 목록
		List<BannerMVO> bannerList = bannerService.selectBannerList(vo);

		//진행중 배너 목록
		List<BannerMVO> useBannerList = bannerService.selectUseBannerList(vo);

		//플랫폼리스트
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._027.ROOT.getValue());
		List<CommonCodeDTO> plfList = commonCodeService.selectList(voA);

		model.addAttribute("searchVO",vo);
		model.addAttribute("plfList",plfList);
		model.addAttribute("useBannerList",useBannerList);
		model.addAttribute("bannerList",bannerList);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 혁신멤버 배너등록
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/insertInnoBanner", method=RequestMethod.POST)
	public void insertInnoBanner(ModelMap model, @Valid BannerIVO vo, BindingResult bindingResult) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._001.getValue());
		bannerService.insertBanner(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 혁신멤버 배너 상세화면
	 * @param model
	 * @param bnnrIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/innoBannerInfo")
	public void selectInnoBannerInfo(ModelMap model, String bnnrIdx) throws Exception{

		BannerMVO bannerVO = new BannerMVO();
		FileDTO fileInfo = new FileDTO();

		if(StringUtils.isNotEmpty(bnnrIdx)){
			bannerVO = bannerService.selectBannerInfo(bnnrIdx);
			//이미지배너 파일
			if(StringUtils.isNotEmpty(bannerVO.getFilePath())){
				FileDTO param = new FileDTO();
				param.setFilePath(bannerVO.getFilePath());

				fileInfo =  fileService.selectFile(param);
			}
		}

		ReqBannerVO vo = new ReqBannerVO();
		vo.setBnnrLocCd(CommonCodeUtil._010._001.getValue());
		vo.setDep1(CommonCodeUtil._027.ROOT.getValue());

		//배너에 적용되어있는 최종순번 조회
		List<BannerMVO> ordList = bannerService.selectOrdList(vo);

		model.addAttribute("ordList",ordList);
		model.addAttribute("bannerVO",bannerVO);
		model.addAttribute("fileInfo",fileInfo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 혁신멤버 배너 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateInnoBanner", method=RequestMethod.POST)
	public void updateInnoBanner(ModelMap model, @Valid  BannerUVO vo, BindingResult bindingResult) throws Exception{

		vo.setBnnrLocCd(CommonCodeUtil._010._001.getValue());
		bannerService.updateBanner(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 배너삭제
	 * @param model
	 * @param bnnrIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteBanner", method=RequestMethod.POST)
	public void deleteBanner(ModelMap model, @RequestParam String bnnrIdx) throws Exception{

		bannerService.deleteBanner(bnnrIdx);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

}
