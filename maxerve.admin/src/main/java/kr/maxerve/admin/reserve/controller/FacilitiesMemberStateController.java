package kr.maxerve.admin.reserve.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.reserve.service.FacilitiesMemberStateService;
import kr.maxerve.admin.reserve.vo.FacilitiesMemberMVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberInfoVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CommonCodeDTO;

/**
* FacilitiesMemberStateController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 월회원
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/reserve/fctMember")
public class FacilitiesMemberStateController {

	@Resource(name="facilitiesMemberStateService")
	FacilitiesMemberStateService facilitiesMemberStateService;

	@Resource(name="commonCodeService")
	CommonCodeService commonCodeService;

	/**
	 * 월회원 현황 화면
	 * @param model
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/facilitiesMemberState","/facilitiesMemberStateExcel"})
	public String selectFacilitiesMemberState(ModelMap model, ReqFacilitiesMemberVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//장소 명
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._002.ROOT.getValue());
		List<CommonCodeDTO> commonCdList = commonCodeService.selectList(voA);
		String titl = "";
		for(int i = 0; i < commonCdList.size() ; i++){
			if(vo.getLocTypCd().equals(commonCdList.get(i).getCmmnCd()) ){
				titl = commonCdList.get(i).getCmmnCdNm();
			}
		}

		List<FacilitiesMemberMVO> memberList = new ArrayList<>();

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){

			memberList = facilitiesMemberStateService.selectFacilitiesMemberStateListExcel(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=facilitiesMemberState_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}else{
			memberList = facilitiesMemberStateService.selectFacilitiesMemberStateList(vo);
		}

		model.addAttribute("titl",titl);
		model.addAttribute("memberList", memberList);
		model.addAttribute("searchVO",vo);
		model.addAttribute("paginationInfo", vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return jsp;
	}

	/**
	 * 월회원 목록 화면
	 * @param model
	 * @param locTypCd
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/facilitiesMemberInfo","/facilitiesMemberInfoExcel"})
	public String selectFacilitiesMemberInfo(ModelMap model,@RequestParam String locTypCd, ReqFacilitiesMemberInfoVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//장소 명
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._002.ROOT.getValue());
		List<CommonCodeDTO> commonCdList = commonCodeService.selectList(voA);
		String titl = "";
		for(int i = 0; i < commonCdList.size() ; i++){
			if(vo.getLocTypCd().equals(commonCdList.get(i).getCmmnCd()) ){
				titl = commonCdList.get(i).getCmmnCdNm();
			}
		}

		//월회원 목록
		List<MemberMVO> memberList = new ArrayList<>();

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){

			memberList = facilitiesMemberStateService.selectFacilitiesMemberListExcel(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=facilitiesMemberState_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}else{
			memberList = facilitiesMemberStateService.selectFacilitiesMemberList(vo);
		}

		model.addAttribute("titl",titl);
		model.addAttribute("memberList", memberList);
		model.addAttribute("searchVO",vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return jsp;
	}
}
