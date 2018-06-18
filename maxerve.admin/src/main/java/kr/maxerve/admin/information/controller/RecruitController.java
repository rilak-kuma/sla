package kr.maxerve.admin.information.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.information.service.RecruitService;
import kr.maxerve.admin.information.vo.RecruitMVO;
import kr.maxerve.admin.information.vo.RecruitUVO;
import kr.maxerve.admin.information.vo.ReqRecruitVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.RecruitRoomDTO;

/**
* RecruitController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 구인
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/information/recruit")
public class RecruitController {

	@Resource
	RecruitService recruitService;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="fileService")
	private FileService fileService;

	@Resource(name="memberService")
	private MemberService memberService;

	/**
	 * 구인 목록화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/recruitList","/recruitListExcel"})
	public String selectRecruitlist(ModelMap model, ReqRecruitVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception {
		Date date = new Date();
		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(new SimpleDateFormat("yyyy.MM.dd").format(date));
		}

		List<RecruitMVO> recruitList = recruitService.selectRecruitlist(vo);

		model.addAttribute("searchVO",vo);
		model.addAttribute("recruitList",recruitList);
		model.addAttribute("paginationInfo",vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, ResponseUtil.RESULT_CODE_SUCESS);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=recruitList_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}

		return jsp;
	}

	/**
	 * 구인 상세화면
	 * @param model
	 * @param rcrtRoomIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/recruitInfo")
	public void selectRecruitInfo(ModelMap model, @RequestParam String rcrtRoomIdx) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//구인 상세
		RecruitMVO recruitMVO = recruitService.selectRecruitInfo(rcrtRoomIdx);

		//첨부파일 리스트
		AttachFileDTO fileVO = new AttachFileDTO();
		fileVO.setAtcLocCd(CommonCodeUtil._010._008.getValue());
		fileVO.setAtcLocIdx(rcrtRoomIdx);
		List<FileMVO> fileList = fileService.selectAttachList(fileVO);

		//수정자정보
		if(!"0".equals(recruitMVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(recruitMVO.getMngrMbrIdx()) ){
			ManagerMemberDTO mDTO = new ManagerMemberDTO();
			mDTO.setMngrMbrIdx(recruitMVO.getMngrMbrIdx());
			ManagerMemberDTO modMbInfo = accountService.selectMemberInfo(mDTO);
			recruitMVO.setModMngrMbrNm(modMbInfo.getMngrMbrNm());
			recruitMVO.setModMngrMbrDpt(modMbInfo.getMngrMbrDpt());
		}

		model.addAttribute("fileList",fileList);
		model.addAttribute("recruitVO",recruitMVO);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 구인 수정
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRecruit", method=RequestMethod.POST)
	public void updateRecruit(ModelMap model, RecruitUVO vo) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			recruitService.updateRecruit(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 구인 삭제
	 * @param model
	 * @param rcrtRoomIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteRecruit", method=RequestMethod.POST)
	public void deleteNotice(ModelMap model, @RequestParam String rcrtRoomIdx) throws Exception {

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			RecruitRoomDTO vo = new RecruitRoomDTO();
			vo.setRcrtRoomIdx(rcrtRoomIdx);
			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setUseYn("N");
			vo.setDelYn("Y");
			recruitService.updateRecruit(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
