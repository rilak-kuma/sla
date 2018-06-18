package kr.maxerve.admin.activity.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
import kr.maxerve.admin.activity.service.ProposeService;
import kr.maxerve.admin.activity.service.SympathyTalkService;
import kr.maxerve.admin.activity.vo.ProposeMVO;
import kr.maxerve.admin.activity.vo.ProposeUVO;
import kr.maxerve.admin.activity.vo.ReqProposeVO;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkReferenceVO;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkVO;
import kr.maxerve.admin.activity.vo.SympathyTalkIVO;
import kr.maxerve.admin.activity.vo.SympathyTalkMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkReferenceMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkUVO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
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
import kr.maxerve.dto.CommentDTO;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ProposeDTO;
import kr.maxerve.dto.SympathyTalkDTO;
import kr.maxerve.dto.TagDTO;

/**
* ProposeController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 제안
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/activity/propose")
public class ProposeController {

	@Resource(name="proposeService")
	private ProposeService proposeService;

	@Resource(name="commentService")
	private CommentService commentService;

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name="sympathyTalkService")
	private SympathyTalkService sympathyTalkService;

	/**
	 * 제안 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/proposeList")
	public void selecProposetList(ModelMap model, ReqProposeVO vo) throws Exception{

		//제안 댓글수 가져오기위해서...
		vo.setCmmtLocCd(CommonCodeUtil._010._002.getValue());

		List<ProposeMVO> proposeList = proposeService.selecProposeList(vo);

		//제안으로 등록된 게시물 총 건수
		int proposeTotalCnt = proposeService.selecProposeTotalCnt();

		model.addAttribute("proposeList",proposeList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("proposeTotalCnt",proposeTotalCnt);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 제안 상세 화면
	 * @param model
	 * @param prpIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/proposeInfo")
	public void selectProposeInfo(ModelMap model, String prpIdx) throws Exception{

		//테그 리스트
		AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._002.getValue());
		attachTagDTO.setTagLocIdx(prpIdx);
		List<TagDTO> tagList = tagService.selectAttachTagList(attachTagDTO);

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(CommonCodeUtil._010._002.getValue());
		fileVO.setAtcLocIdx(prpIdx);
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		//댓글정보
		CommentDTO cmtDTO = new CommentDTO();
		cmtDTO.setCmmtLocCd(CommonCodeUtil._010._002.getValue());
		cmtDTO.setTblIdx(prpIdx);
		List<CommentVO> commentList = commentService.selectCommentList(cmtDTO);

		ProposeMVO proposeMVO = proposeService.selectProposeInfo(prpIdx);

		//수정자정보
		if(!"0".equals(proposeMVO.getMngrMbrIdx())  && StringUtils.isNotEmpty(proposeMVO.getMngrMbrIdx())){
			ManagerMemberDTO mDTO = new ManagerMemberDTO();
			mDTO.setMngrMbrIdx(proposeMVO.getMngrMbrIdx());
			ManagerMemberDTO modMbInfo = accountService.selectMemberInfo(mDTO);
			proposeMVO.setModMngrMbrNm(modMbInfo.getMngrMbrNm());
			proposeMVO.setModMngrMbrDpt(modMbInfo.getMngrMbrDpt());
		}

		model.addAttribute("tagList",tagList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("commentList",commentList);
		model.addAttribute("proposeVO",proposeMVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 제안수정
	 * @param model
	 * @param uvo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updatePropose", method=RequestMethod.POST)
	public void updatePropose(ModelMap model, @Valid @RequestBody ProposeUVO uvo, BindingResult bindingResult) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ProposeDTO vo = uvo;
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setBody(fileService.moveEditor(vo.getBody()));
			proposeService.updatePropose(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 제안 삭제
	 * @param model
	 * @param prpIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePropose", method=RequestMethod.POST)
	public void deletePropose(ModelMap model, @RequestParam String prpIdx) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(prpIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ProposeDTO vo = new ProposeDTO();
			vo.setPrpIdx(prpIdx);
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setUseYn("N");
			vo.setDelYn("Y");
			proposeService.updatePropose(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 공감토크 등록
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/insert_fsw", method=RequestMethod.POST)
	public void insertFsw(ModelMap model, @Valid @RequestBody SympathyTalkIVO vo, BindingResult bindingResult) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			sympathyTalkService.insertFsw(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 공감토크 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/fswList")
	public void selectFswList(ModelMap model, ReqSympathyTalkVO vo) throws Exception {

		//공감토크 목록
		List<SympathyTalkMVO> fswList = sympathyTalkService.selectFswList(vo);

		//진행중 공감토크 목록
		List<SympathyTalkMVO> useFswList = sympathyTalkService.selectUseFswList();

		//공감토크로 등록된 게시물 총 건수
		int sympathyTalkTotalCnt = sympathyTalkService.selectSympathyTalkTotalCnt(vo);

		model.addAttribute("useFswList",useFswList);
		model.addAttribute("fswList",fswList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("sympathyTalkTotalCnt", sympathyTalkTotalCnt);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 공감토크 상세 화면
	 * @param model
	 * @param fswTalkIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/fswInfo")
	public void selectFswInfo(ModelMap model, String fswTalkIdx) throws Exception {

		List<TagDTO> tagList = null;
		List<FileMVO> fileList = null;
		List<CommonCodeDTO> fswTalkTyp = null;
		List<SympathyTalkReferenceMVO> SymTalkReferenceList = null;
		SympathyTalkMVO fswTalkVO = new SympathyTalkMVO();

		if(StringUtils.isNotEmpty(fswTalkIdx)){

			//연관 게시물 목록
			SymTalkReferenceList = sympathyTalkService.selectUseSymTalkReferenceList(fswTalkIdx);

			//테그 리스트
			AttachTagDTO attachTagDTO = new AttachTagDTO();
			attachTagDTO.setTagLocCd(CommonCodeUtil._010._003.getValue());
			attachTagDTO.setTagLocIdx(fswTalkIdx);
			tagList = tagService.selectAttachTagList(attachTagDTO);

			//첨부파일 리스트
			AttachFileDTO fileVO = new AttachFileDTO();
			fileVO.setAtcLocCd(CommonCodeUtil._010._003.getValue());
			fileVO.setAtcLocIdx(fswTalkIdx);
			fileList = fileService.selectAttachList(fileVO);

			fswTalkVO = sympathyTalkService.selectFswInfo(fswTalkIdx);
		}

		//공감토크 종류 공통코드(007)
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._007.ROOT.getValue());
		fswTalkTyp = commonCodeService.selectList(voA);

		//노출순서 MAX값
		Integer maxOrd = sympathyTalkService.selectMaxOrd();
		fswTalkVO.setMaxOrd(maxOrd.toString() );

		model.addAttribute("SymTalkReferenceList",SymTalkReferenceList);
		model.addAttribute("fswTalkTyp",fswTalkTyp);
		model.addAttribute("tagList",tagList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("fswTalkVO",fswTalkVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 공감토크 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/update_fsw", method=RequestMethod.POST)
	public void updatetFsw(ModelMap model,@Valid @RequestBody SympathyTalkUVO vo, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			sympathyTalkService.updateFsw(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 공감토크 삭제
	 * @param model
	 * @param fswTalkIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/delete_fsw", method=RequestMethod.POST)
	public void deletetFsw(ModelMap model, @RequestParam String fswTalkIdx) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(fswTalkIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			SympathyTalkDTO vo = new SympathyTalkDTO();
			vo.setFswTalkIdx(fswTalkIdx);
			vo.setUseYn("N");
			vo.setDelYn("Y");
			sympathyTalkService.deletetFsw(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 공감토크 연관 게시물 검색 화면
	 * @param model
	 * @param vo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/symTalkReferenceList")
	public String selectSymTalkReferenceList(ModelMap model, ReqSympathyTalkReferenceVO vo, HttpServletRequest request) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<SympathyTalkReferenceMVO> selectSymTalkReferenceList = sympathyTalkService.selectSymTalkReferenceList(vo);

		model.addAttribute("searchVO", vo);
		model.addAttribute("selectSymTalkReferenceList", selectSymTalkReferenceList);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return "popup/" + request.getRequestURI().replace(".do", "").substring(1);
	}
}
