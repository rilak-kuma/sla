package kr.maxerve.admin.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.basic.service.CategoryService;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.member.vo.MemberChannelMVO;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.member.vo.MoveInOztnMVO;
import kr.maxerve.admin.member.vo.MoveInOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyUVO;
import kr.maxerve.admin.member.vo.ReqMemberVO;
import kr.maxerve.admin.member.vo.ReqMoveInOztnVO;
import kr.maxerve.admin.tag.service.TagService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.MoveInApplyCtgrDTO;
import kr.maxerve.dto.TagDTO;

/**
* MemberController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 혁신멤버
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Resource(name="memberService")
	private MemberService memberService;

	//공통코드
	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	//카테고리 service
	@Resource(name="categoryService")
	private CategoryService categoryService;

	//파일
	@Resource(name="fileService")
	FileService fileService;

	//태그
	@Resource(name="tagService")
	TagService tagService;

	/**
	 * 입주단체현황 화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/currentState","/currentStateExcel"})
	public String selectCurrentState(ModelMap model, ReqMoveInOztnVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {

		vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());

		//입주중단체
		List<MoveInOztnMVO> moveinList = memberService.selectMoveinList(vo);

		//기간만료 단체
		List<MoveInOztnMVO> expirationList = memberService.selectExpirationList(vo);

		model.addAttribute("moveinList", moveinList);
		model.addAttribute("expirationList", expirationList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=currentState_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 입주단체 목록화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/moveInOztnList","/moveInOztnListExcel"})
	public String selectMoveInOztnList(ModelMap model, ReqMoveInOztnVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {

		//입주신청상태 : 입주확정
		vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());
		//혁신멤버 구분 : 입주업체
		vo.setMbrTypCd(CommonCodeUtil._015._001.getValue());

		if(StringUtils.isEmpty(vo.getMvinSrtDtTwo())){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

			vo.setMvinSrtDtTwo(sdf.format(date));
		}

		//입주단체 검색
		List<MoveInOztnMVO> moveinList = null;
		if(request.getRequestURI().indexOf("Excel") > 0){
			moveinList = memberService.selectMoveInOztnListExcel(vo);
		}else{
			moveinList = memberService.selectMoveInOztnList(vo);
		}

		//입주그룹공통코드
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._025.ROOT.getValue());
		List<CommonCodeDTO> mvinGrpCds = commonCodeService.selectList(voA);

		model.addAttribute("mvinGrpCds", mvinGrpCds);
		model.addAttribute("moveinList", moveinList);
		model.addAttribute("searchVO",vo);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=moveInOztnList_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 입주단체 상세화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/moveInOztnInfo","/moveInOztnInfoExcel"})
	public String selectMoveInOztnInfo(ModelMap model, ReqMoveInOztnVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (vo.getMvinAplyIdx().isEmpty()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		MoveInOztnMVO moveInOztnInfo = new MoveInOztnMVO();
		List<MoveInApplyCtgrDTO> moveInApplyCtgrList = null;

		if (resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)) {
			//혁신멤버 구분 : 입주업체
			vo.setMbrTypCd(CommonCodeUtil._015._001.getValue());
			//단체정보
			moveInOztnInfo = memberService.selectMoveInOztnInfo(vo);
			//업종목록
			moveInApplyCtgrList = memberService.selectMoveInApplyCtgrList(vo.getMvinAplyIdx());
		}

		//첨부파일
		AttachFileDTO dto = new AttachFileDTO();
		dto.setAtcLocCd("");
		dto.setAtcLocIdx("");
		List<FileMVO> fileList =  fileService.selectAttachList(dto);

		//업종 카테고리 목록
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCtgrTypCd(CommonCodeUtil._009._001.getValue());
		List<CategoryDTO> ctgrIdxList = categoryService.selectList(categoryDTO);

		//입주그룹공통코드
		CommonCodeDTO voB = new CommonCodeDTO();
		voB.setDep1(CommonCodeUtil._025.ROOT.getValue());
		List<CommonCodeDTO> mvinGrpCds = commonCodeService.selectList(voB);

		//단체형태 공통코드
		CommonCodeDTO voC = new CommonCodeDTO();
		voC.setDep1(CommonCodeUtil._014.ROOT.getValue());
		List<CommonCodeDTO> oztnTypCds = commonCodeService.selectList(voC);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("fileList", fileList);
		model.addAttribute("moveInOztnInfo", moveInOztnInfo);
		model.addAttribute("moveInApplyCtgrList", moveInApplyCtgrList);
		model.addAttribute("ctgrIdxList", ctgrIdxList);
		model.addAttribute("mvinGrpCds", mvinGrpCds);
		model.addAttribute("oztnTypCds", oztnTypCds);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=moveInOztnInfo_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 입주단체 정보수정
	 * @param model
	 * @param vo
	 * @param dto
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMoveInOztnInfo", method=RequestMethod.POST)
	public void updateMoveInOztnInfo(ModelMap model, @Valid MoveInOztnUVO vo, @Valid MoveInApplyCtgrDTO dto, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//입주단체정보 수정
		memberService.updateMoveInOztnInfo(vo, dto);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 신규입주단체 입주관리 목록화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/newMoveInAplyList")
	public void selectNewMoveInAplyList(ModelMap model, ReqMoveInOztnVO vo) throws Exception {

		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

			vo.setCreDttmTwo(sdf.format(date));
		}

		//입주단체 검색
		List<MoveInOztnMVO> moveinList = memberService.selectNewMoveInAplyList(vo);

		//입주신청상태 공통코드
		CommonCodeDTO commonVo = new CommonCodeDTO();
		commonVo.setDep1(CommonCodeUtil._022.ROOT.getValue());
		List<CommonCodeDTO> aplyPgrCds = commonCodeService.selectList(commonVo);

		model.addAttribute("moveinList", moveinList);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute("aplyPgrCds", aplyPgrCds);
		model.addAttribute("searchVO", vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 신규입주신청 상세 화면
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/newMoveInAplyInfo","/newMoveInAplyInfoExcel"})
	public String newMoveInAplyInfo(ModelMap model, ReqMoveInOztnVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (vo.getMvinAplyIdx().isEmpty()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		MoveInOztnMVO moveInOztnInfo = new MoveInOztnMVO();
		List<MoveInApplyCtgrDTO> moveInApplyCtgrList = null;
		if (resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)) {
			//혁신멤버 구분 : 입주업체
			vo.setMbrTypCd(CommonCodeUtil._015._001.getValue());
			//단체정보
			moveInOztnInfo = memberService.selectMoveInOztnInfo(vo);
			//업종
			moveInApplyCtgrList = memberService.selectMoveInApplyCtgrList(vo.getMvinAplyIdx());
		}

		//업종 카테고리 목록
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCtgrTypCd(CommonCodeUtil._009._001.getValue());
		List<CategoryDTO> ctgrIdxList = categoryService.selectList(categoryDTO);

		//입주그룹공통코드
		CommonCodeDTO voB = new CommonCodeDTO();
		voB.setDep1(CommonCodeUtil._025.ROOT.getValue());
		List<CommonCodeDTO> mvinGrpCds = commonCodeService.selectList(voB);

		//단체형태 공통코드
		CommonCodeDTO voC = new CommonCodeDTO();
		voC.setDep1(CommonCodeUtil._014.ROOT.getValue());
		List<CommonCodeDTO> oztnTypCds = commonCodeService.selectList(voC);

		//입주신청상태 공통코드
		CommonCodeDTO commonVo = new CommonCodeDTO();
		commonVo.setDep1(CommonCodeUtil._022.ROOT.getValue());
		List<CommonCodeDTO> AplyPgrCds = commonCodeService.selectList(commonVo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("moveInOztnInfo", moveInOztnInfo);
		model.addAttribute("moveInApplyCtgrList", moveInApplyCtgrList);
		model.addAttribute("ctgrIdxList", ctgrIdxList);
		model.addAttribute("mvinGrpCds", mvinGrpCds);
		model.addAttribute("oztnTypCds", oztnTypCds);
		model.addAttribute("aplyPgrCds", AplyPgrCds);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=newMoveInAplyInfo_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 신규입주신청 상태 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNewMoveInAply", method=RequestMethod.POST)
	public void updateNewMoveInAply(ModelMap model, @Valid NewMoveInApplyUVO vo, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//신규입주신청 상태 수정
		memberService.updateNewMoveInAply(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 신규입주신청 단체정보 수정
	 * @param model
	 * @param vo
	 * @param dto
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNewMoveInOztnInfo", method=RequestMethod.POST)
	public void updateNewMoveInOztnInfo(ModelMap model, @Valid NewMoveInApplyOztnUVO vo, @Valid MoveInApplyCtgrDTO dto, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//입주단체정보 수정
		memberService.updateNewMoveInOztnInfo(vo, dto);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 온라인멤버 현황 화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/onlineMemberState","/onlineMemberStateExcel"})
	public String selectOnlineMemberState(ModelMap model, ReqMemberVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {

		//전체 온라인멤버
		List<MemberMVO> onlineMemberState = memberService.selectOnlineMemberState();

		//입주그룹
		vo.setDepOne(CommonCodeUtil._025.ROOT.getValue());
		//혁신멤버 구분 : 입주업체
		vo.setMbrTypCd(CommonCodeUtil._015._001.getValue());

		//온라인 입주멤버
		List<MemberMVO> onlineMvinMbState = memberService.selectOnlineMvinMbState(vo);

		model.addAttribute("onlineMemberState", onlineMemberState);
		model.addAttribute("onlineMvinMbState", onlineMvinMbState);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=onlineMemberState_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 온라인멤버 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/onlineMemberList")
	public void selectOnlineMemberList(ModelMap model, ReqMemberVO vo) throws Exception {

		//입주상태
		vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());

		List<MemberMVO> onlineMemberList = memberService.selectOnlineMemberList(vo);

		//입주그룹공통코드
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._025.ROOT.getValue());
		List<CommonCodeDTO> mvinGrpCds = commonCodeService.selectList(voA);

		//멤버구분공통코드
		CommonCodeDTO voB = new CommonCodeDTO();
		voB.setDep1(CommonCodeUtil._015.ROOT.getValue());
		List<CommonCodeDTO> mbrTypCds = commonCodeService.selectList(voB);

		model.addAttribute("onlineMemberList", onlineMemberList);
		model.addAttribute("mvinGrpCds", mvinGrpCds);
		model.addAttribute("mbrTypCds", mbrTypCds);
		model.addAttribute("searchVO", vo);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 온라인멤버 목록 엑셀 다운로드
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/onlineMemberListExcel")
	public String selectOnlineMemberListExcel(ModelMap model, ReqMemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		//입주상태
		vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());

		List<MemberMVO> onlineMemberList = memberService.selectOnlineMemberList(vo);

		model.addAttribute("onlineMemberList", onlineMemberList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        response.setHeader("Content-Disposition", "ATTachment; Filename=onlineMemberList_"+sdf.format(date)+".xls");
        response.setHeader("Content-Description", "JSP Generated Data");

		return "empty/" + request.getRequestURI().replace(".do", "").substring(1);
	}

	/**
	 * 온라인멤버 상세 화면
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/onlineMemberInfo","/onlineMemberInfoExcel"}, method=RequestMethod.POST)
	public String selectOnlineMemberInfoExcel(ModelMap model, ReqMemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (StringUtils.isEmpty(vo.getMbrIdx())) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		//온라인멤버 상세정보
		MemberMVO memberVO = new MemberMVO();
		//채널정보
		List<MemberChannelMVO> mbChannelList = null;

		if (resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)) {
			//입주상태
			vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());
			//온라인멤버상세
			memberVO = memberService.selectOnlineMemberInfo(vo);
			//멤버채널목록
			mbChannelList = memberService.selectMblCnlList(vo.getMbrIdx());
		}

		//테그 리스트
		AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._001.getValue());
		attachTagDTO.setTagLocIdx(memberVO.getMbrIdx());
		List<TagDTO> tagList = tagService.selectAttachTagList(attachTagDTO);

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(CommonCodeUtil._010._001.getValue());
		fileVO.setAtcLocIdx(memberVO.getMbrIdx());
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		model.addAttribute("memberVO", memberVO);
		model.addAttribute("mbChannelList", mbChannelList);
		model.addAttribute("tagList", tagList);
		model.addAttribute("fileList", fileList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=onlineMemberInfo_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 입주단체 목록 화면
	 * @param model
	 * @param keyword
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/moveInOztnPopUp")
	public String selectmoveInOztnPopUpList(ModelMap model, @RequestParam(defaultValue="", required=false) String keyword, HttpServletRequest request) throws Exception {

		//시설구분 : 입주단체목록
		ReqMoveInOztnVO vo = new ReqMoveInOztnVO();
		//검색어 입력값
		vo.setOztnNm(keyword);

		//입주신청상태 : 입주확정
		vo.setAplyPgrCd(CommonCodeUtil._022._003.getValue());
		//혁신멤버 구분 : 입주업체
		vo.setMbrTypCd(CommonCodeUtil._015._001.getValue());

		List<MemberMVO> memberList = memberService.selectmoveInOztnPopUpList(vo);

		model.addAttribute("memberList", memberList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		return "popup/member/moveInOztnPopUp";
	}
}
