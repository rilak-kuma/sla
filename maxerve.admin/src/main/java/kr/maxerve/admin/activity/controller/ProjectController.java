package kr.maxerve.admin.activity.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.activity.service.ProjectService;
import kr.maxerve.admin.activity.vo.ProjectMVO;
import kr.maxerve.admin.activity.vo.ProjectUVO;
import kr.maxerve.admin.activity.vo.ReqProjectVO;
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
import kr.maxerve.dto.CommentDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.ProjectDTO;
import kr.maxerve.dto.TagDTO;

/**
* ProjectController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 프로젝트
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/activity/project")
public class ProjectController {

	@Resource(name="projectService")
	ProjectService projectService;

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
	 * 프로젝트 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/projectList")
	public void selectProjectList(ModelMap model, ReqProjectVO vo) throws Exception {
		CategoryDTO dto = new CategoryDTO();
		dto.setCtgrTypCd(CommonCodeUtil._009._002.getValue());

		//카테고리 목록
		List<CategoryDTO> ctgList = categoryService.selectList(dto);

		//프로젝트 목록
		List<ProjectMVO> projectList = projectService.selectProjectList(vo);

		//프로젝트로 등록된 게시물 총 건수
		int projectTotalCnt = projectService.selectProjectTotalCnt();

		model.addAttribute("ctgList", ctgList);
		model.addAttribute("projectList", projectList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("projectTotalCnt", projectTotalCnt);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 프로젝트 상세 화면
	 * @param model
	 * @param pjtIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/projectInfo")
	public void selectProjectInfo(ModelMap model, String pjtIdx) throws Exception {

		ProjectMVO projectMVO = projectService.selectProjectInfo(pjtIdx);

		//테그 리스트
		AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._004.getValue());
		attachTagDTO.setTagLocIdx(pjtIdx);
		List<TagDTO> tagList = tagService.selectAttachTagList(attachTagDTO);

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(CommonCodeUtil._010._004.getValue());
		fileVO.setAtcLocIdx(pjtIdx);
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		//댓글정보
		CommentDTO cmtDTO = new CommentDTO();
		cmtDTO.setCmmtLocCd(CommonCodeUtil._010._004.getValue());
		cmtDTO.setTblIdx(pjtIdx);
		List<CommentVO> commentList = commentService.selectCommentList(cmtDTO);

		//수정자정보
		if(!"0".equals(projectMVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(projectMVO.getMngrMbrIdx())){
			ManagerMemberDTO mDTO = new ManagerMemberDTO();
			mDTO.setMngrMbrIdx(projectMVO.getMngrMbrIdx());
			ManagerMemberDTO modMbInfo = accountService.selectMemberInfo(mDTO);
			projectMVO.setModMngrMbrNm(modMbInfo.getMngrMbrNm());
			projectMVO.setModMngrMbrDpt(modMbInfo.getMngrMbrDpt());
		}

		model.addAttribute("tagList",tagList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("commentList",commentList);
		model.addAttribute("projectVO",projectMVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);
	}

	/**
	 * 프로젝트 수정
	 * @param model
	 * @param uvo
	 * @throws Exception
	 */
	@RequestMapping(value="/updateProject", method=RequestMethod.POST)
	public void updateProject(ModelMap model, @Valid @RequestBody ProjectUVO uvo) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ProjectDTO vo = uvo;
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			projectService.updateProject(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 프로젝트 삭제
	 * @param model
	 * @param pjtIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteProject", method=RequestMethod.POST)
	public void deleteProject(ModelMap model, @RequestParam String pjtIdx) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(pjtIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			ProjectDTO vo = new ProjectDTO();
			vo.setPjtIdx(pjtIdx);
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setDelYn("Y");
			vo.setUseYn("N");
			projectService.updateProject(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
