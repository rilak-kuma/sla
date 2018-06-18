package kr.maxerve.admin.activity.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.activity.service.ReferenceRoomService;
import kr.maxerve.admin.activity.service.YoutubeService;
import kr.maxerve.admin.activity.vo.ReferenceMVO;
import kr.maxerve.admin.activity.vo.ReferenceUVO;
import kr.maxerve.admin.activity.vo.ReqReferenceVO;
import kr.maxerve.admin.basic.service.CategoryService;
import kr.maxerve.admin.basic.service.SimpleResourceService;
import kr.maxerve.admin.basic.vo.ReqSimpleResourceIVO;
import kr.maxerve.admin.comment.service.CommentService;
import kr.maxerve.admin.comment.vo.CommentVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.tag.service.TagService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.CommentDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ReferenceRoomDTO;
import kr.maxerve.dto.ReferenceRoomYoutubeDTO;
import kr.maxerve.dto.SimpleResourceDTO;
import kr.maxerve.dto.TagDTO;

/**
* ReferenceRoomController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 자료실
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/activity/referenceRoom")
public class ReferenceRoomController {

	@Resource(name="referenceRoomService")
	ReferenceRoomService referenceRoomService;

	@Resource(name="categoryService")
	CategoryService categoryService;

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="youtubeService")
	private YoutubeService youtubeService;

	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name="commentService")
	private CommentService commentService;

	@Resource(name="simpleResourceService")
	private SimpleResourceService simpleResourceService;

	private static final String LOC_TYP_CD = CommonCodeUtil._010._007.getValue();

	/**
	 * 자료실 목록화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/referenceList")
	public void selectReferenceList(ModelMap model, ReqReferenceVO vo) throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setCtgrTypCd(CommonCodeUtil._009._004.getValue());

		//오늘날짜
		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(sdf.format(date));
		}

		//카테고리 목록
		List<CategoryDTO> ctgList = categoryService.selectList(dto);

		//카테고리별 자료수
		List<ReferenceMVO> ctgrRefCntList =  referenceRoomService.selectCtgrRefCntList(dto);

		//자료실 목록
		List<ReferenceMVO> referenceList = referenceRoomService.selectReferenceList(vo);

		model.addAttribute("ctgList", ctgList);
		model.addAttribute("ctgrRefCntList", ctgrRefCntList);
		model.addAttribute("referenceList", referenceList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("paginationInfo", vo.getPageInfo());

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

	}

	/**
	 * 자료실 목록 액샐 다운로드
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/referenceListExcel")
	public String selectReferenceListExcel(ModelMap model, ReqReferenceVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setCtgrTypCd(CommonCodeUtil._009._004.getValue());

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		//오늘날짜
		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(sdf.format(date));
		}

		//자료실 목록(엑셀다운로드용)
		List<ReferenceMVO> referenceList = referenceRoomService.selectReferenceListExcel(vo);

		model.addAttribute("referenceList", referenceList);
        response.setHeader("Content-Disposition", "ATTachment; Filename=referenceList"+(new SimpleDateFormat("yyyyMMdd").format(date))+".xls");
        response.setHeader("Content-Description", "JSP Generated Data");

		return "empty/" + request.getRequestURI().replace(".do", "").substring(1);
	}

	/**
	 * 자료실 상세 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/referenceInfo")
	public void selectReferenceInfo(ModelMap model, ReqReferenceVO vo) throws Exception {
		vo.setCtgrTypCd(CommonCodeUtil._009._004.getValue());
		ReferenceMVO referenceVO = referenceRoomService.selectReferenceInfo(vo);

		//테그 리스트
		AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(LOC_TYP_CD);
		attachTagDTO.setTagLocIdx(vo.getRefRoomIdx());
		List<TagDTO> tagList = tagService.selectAttachTagList(attachTagDTO);

		//유투브 URL 목록
		List<ReferenceRoomYoutubeDTO> yutbUrlList = null;
		if("Y".equals(referenceVO.getYutbYn())){
			yutbUrlList = youtubeService.selectYutbList(vo.getRefRoomIdx());
		}

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(LOC_TYP_CD);
		fileVO.setAtcLocIdx(vo.getRefRoomIdx());
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		//댓글정보
		CommentDTO cmtDTO = new CommentDTO();
		cmtDTO.setCmmtLocCd(LOC_TYP_CD);
		cmtDTO.setTblIdx(vo.getRefRoomIdx());
		List<CommentVO> commentList = commentService.selectCommentList(cmtDTO);

		//수정자정보
		if(!"0".equals(referenceVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(referenceVO.getMngrMbrIdx())){
			ManagerMemberDTO mDTO = new ManagerMemberDTO();
			mDTO.setMngrMbrIdx(referenceVO.getMngrMbrIdx());
			ManagerMemberDTO modMbInfo = accountService.selectMemberInfo(mDTO);
			referenceVO.setModMngrMbrNm(modMbInfo.getMngrMbrNm());
			referenceVO.setModMngrMbrDpt(modMbInfo.getMngrMbrDpt());
		}

		model.addAttribute("tagList",tagList);
		model.addAttribute("yutbUrlList",yutbUrlList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("commentList",commentList);
		model.addAttribute("referenceVO",referenceVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 자료실 수정
	 * @param model
	 * @param uvo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateReference", method=RequestMethod.POST)
	public void updateReference(ModelMap model,@Valid @RequestBody ReferenceUVO uvo, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ReferenceRoomDTO vo = uvo;
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			referenceRoomService.updateReference(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 자료실 삭제
	 * @param model
	 * @param refRoomIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteReference", method=RequestMethod.POST)
	public void deleteReference(ModelMap model, @RequestParam String refRoomIdx) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(refRoomIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ReferenceRoomDTO vo = new ReferenceRoomDTO();
			vo.setRefRoomIdx(refRoomIdx);
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setUseYn("N");
			vo.setDelYn("Y");
			referenceRoomService.updateReference(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 자료실 인기태그 목록 화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/popularTag")
	public void writePopularTag(ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<TagDTO> tagAllList;	// 자료실등록태그목록
		List<SimpleResourceDTO> tagList;	// 인기태그목록


		// 자료실등록태그목록
		AttachTagDTO param = new AttachTagDTO();
		param.setTagLocCd(LOC_TYP_CD);

		tagAllList = tagService.selectAttachTagList(param);

		// 인기태그목록
		SimpleResourceDTO param1 = new SimpleResourceDTO();
		param1.setSiplRscTypCd(CommonCodeUtil._024._004.getValue());

		tagList = simpleResourceService.selectList(param1);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(tagAllList);
		model.addAttribute(tagList);
	}

	/**
	 * 인기태그등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insertPopularTag", method=RequestMethod.POST)
	public void insertPopularTag(@Valid @RequestBody ReqSimpleResourceIVO vo, BindingResult bindingResult, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			simpleResourceService.insert(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
