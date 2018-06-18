package kr.maxerve.admin.activity.controller;

import java.util.List;

import javax.annotation.Resource;
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
import kr.maxerve.admin.activity.service.EventService;
import kr.maxerve.admin.activity.vo.EventMVO;
import kr.maxerve.admin.activity.vo.EventUVO;
import kr.maxerve.admin.activity.vo.ReqEventVO;
import kr.maxerve.admin.basic.service.CategoryService;
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
import kr.maxerve.dto.EventDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.TagDTO;

/**
* EventController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 이벤트/행사
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/activity/event")
public class EventController {

	@Resource(name="eventService")
	EventService eventService;

	@Resource(name="categoryService")
	CategoryService categoryService;

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="memberService")
	private MemberService memberService;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="tagService")
	private TagService tagService;

	@Resource(name="commentService")
	CommentService commentService;

	/**
	 * 이벤트/행사 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/eventList")
	public void selectEventList(ModelMap model, ReqEventVO vo) throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setCtgrTypCd(CommonCodeUtil._009._003.getValue());

		//카테고리 목록
		List<CategoryDTO> ctgList = categoryService.selectList(dto);

		//이벤트/행사 목록
		List<EventMVO> eventList = eventService.selectEventList(vo);

		int eventTotalCnt = eventService.selectEventTotalCnt();

		model.addAttribute("ctgList", ctgList);
		model.addAttribute("eventList", eventList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("eventTotalCnt", eventTotalCnt);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 이벤트/행사 상세 화면
	 * @param model
	 * @param evtIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/eventInfo")
	public void selectEventInfo(ModelMap model, @RequestParam String evtIdx) throws Exception {

		EventMVO eventMVO = eventService.selectEventInfo(evtIdx);

		//테그 리스트
		AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._004.getValue());
		attachTagDTO.setTagLocIdx(evtIdx);
		List<TagDTO> tagList = tagService.selectAttachTagList(attachTagDTO);

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(CommonCodeUtil._010._004.getValue());
		fileVO.setAtcLocIdx(evtIdx);
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		//댓글정보(좋아요포함)
		CommentVO cmtVO = new CommentVO();
		cmtVO.setCmmtLocCd(CommonCodeUtil._010._004.getValue());
		cmtVO.setTblIdx(evtIdx);
		cmtVO.setLikeLocCd(CommonCodeUtil._010._013.getValue());
		List<CommentVO> commentList = commentService.selectCommentLikeList(cmtVO);

		//수정자정보
		if(!"0".equals(eventMVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(eventMVO.getMngrMbrIdx())){
			ManagerMemberDTO mDTO = new ManagerMemberDTO();
			mDTO.setMngrMbrIdx(eventMVO.getMngrMbrIdx());
			ManagerMemberDTO modMbInfo = accountService.selectMemberInfo(mDTO);
			eventMVO.setModMngrMbrNm(modMbInfo.getMngrMbrNm());
			eventMVO.setModMngrMbrDpt(modMbInfo.getMngrMbrDpt());
		}

		model.addAttribute("tagList",tagList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("commentList",commentList);
		model.addAttribute("eventVO",eventMVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 이벤트/행사 수정
	 * @param model
	 * @param uvo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateEvent", method=RequestMethod.POST)
	public void updateEvent(ModelMap model, @Valid @RequestBody EventUVO uvo, BindingResult bindingResult) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			EventDTO vo = uvo;
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			eventService.updateEvent(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 이벤트/행사 삭제
	 * @param model
	 * @param evtIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteEvent", method=RequestMethod.POST)
	public void deleteEvent(ModelMap model, @RequestParam String evtIdx) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(evtIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			EventDTO vo = new EventDTO();
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setDelYn("Y");
			vo.setUseYn("N");
			vo.setEvtIdx(evtIdx);
			eventService.updateEvent(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
