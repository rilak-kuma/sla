package kr.maxerve.admin.information.controller;

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

import kr.maxerve.admin.account.service.AccountService;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.information.service.NewsletterService;
import kr.maxerve.admin.information.vo.NewsLetterMVO;
import kr.maxerve.admin.information.vo.NewsletterIVO;
import kr.maxerve.admin.information.vo.NewsletterUVO;
import kr.maxerve.admin.information.vo.ReqNewsLetterVO;
import kr.maxerve.admin.information.vo.ReqSubscriberVO;
import kr.maxerve.admin.information.vo.SubscriberMVO;
import kr.maxerve.admin.login.vo.ManagerMemberMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.ManagerMemberDTO;
import kr.maxerve.dto.NewsLetterDTO;

/**
* NewsletterController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 뉴스레터
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/information/newsletter")
public class NewsletterController {

	@Resource(name="newsletterService")
	NewsletterService newsletterService;

	@Resource(name="commonCodeService")
	CommonCodeService commonCodeService;

	@Resource(name="accountService")
	AccountService accountService;

	@Resource(name="fileService")
	FileService fileService;

	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

	/**
	 * 뉴스 레터 목록 화면
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value="/newsletterList")
	public void selectNewsletterList(ModelMap model, ReqNewsLetterVO vo) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			vo.setCreDttmOne("");;
			vo.setCreDttmTwo(sdf.format(date));
		}

		List<NewsLetterMVO> newsletterList = newsletterService.selectNewsletterList(vo);

		model.addAttribute("newsletterList",newsletterList);
		model.addAttribute("searchVO",vo);
		model.addAttribute("paginationInfo",vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 뉴스레터 상세화면
	 * @param model
	 * @param newsLttrIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/newsletterInfo")
	public void selectNewsletterInfo(ModelMap model,@RequestParam String newsLttrIdx) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		NewsLetterDTO newsletterVO =new NewsLetterMVO();
		ManagerMemberDTO memberDTO = new ManagerMemberDTO();
		ManagerMemberDTO creMbInfo = new ManagerMemberDTO();
		ManagerMemberDTO modMbInfo = new ManagerMemberDTO();

		if(StringUtils.isNotEmpty(newsLttrIdx)){
			newsletterVO = newsletterService.selectNewsletterInfo(newsLttrIdx);
		}

		//등록자정보
		if(!"0".equals(newsletterVO.getMngrMbrIdx()) && StringUtils.isNotEmpty(newsletterVO.getMngrMbrIdx()) ) {
			memberDTO.setMngrMbrIdx(newsletterVO.getMngrMbrIdx());
			creMbInfo = accountService.selectMemberInfo(memberDTO);
		}

		//수정자정보
		if(!"0".equals(newsletterVO.getModMbrIdx()) && StringUtils.isNotEmpty(newsletterVO.getModMbrIdx()) ) {
			memberDTO.setMngrMbrIdx(newsletterVO.getModMbrIdx());
			modMbInfo = accountService.selectMemberInfo(memberDTO);
		}

		model.addAttribute("newsletterVO",newsletterVO);
		model.addAttribute("creMngrMbrNm", creMbInfo.getMngrMbrNm());
		model.addAttribute("creMngrMbrDpt", creMbInfo.getMngrMbrDpt());
		model.addAttribute("modMngrMbrNm", modMbInfo.getMngrMbrNm());
		model.addAttribute("modMngrMbrDpt", modMbInfo.getMngrMbrDpt());

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 뉴스레터 등록
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/insertNewsletter", method=RequestMethod.POST)
	public void insertNewsletter(ModelMap model, @Valid NewsletterIVO vo, BindingResult bindingResult) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			NewsLetterDTO dto = vo;
			dto.setBody(fileService.moveEditor(vo.getBody()));
			if(StringUtils.isEmpty(dto.getPcYn())){
				dto.setPcYn("N");
			}

			if(StringUtils.isEmpty(dto.getMblYn())){
				dto.setMblYn("N");
			}

			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setModMbrIdx(memberInfo.getMngrMbrIdx());
			newsletterService.insertNewsletter(dto);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 뉴스레터 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateNewsletter", method=RequestMethod.POST)
	public void updateNewsletter(ModelMap model, @Valid NewsletterUVO vo, BindingResult bindingResult) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			NewsLetterDTO dto = vo;
			dto.setBody(fileService.moveEditor(vo.getBody()));
			if(StringUtils.isEmpty(dto.getPcYn())){
				dto.setPcYn("N");
			}

			if(StringUtils.isEmpty(dto.getMblYn())){
				dto.setMblYn("N");
			}

			vo.setMngrMbrIdx(memberInfo.getMngrMbrIdx());
			vo.setModMbrIdx(memberInfo.getMngrMbrIdx());
			newsletterService.insertNewsletter(dto);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 뉴스레터 삭제
	 * @param model
	 * @param newsLttrIdx
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteNewsletter", method=RequestMethod.POST)
	public void deleteNewsletter(ModelMap model, @RequestParam String newsLttrIdx) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		ManagerMemberMVO memberInfo = accountService.selectMemberInfo();

		if(memberInfo == null){
			resultCode = ResponseUtil.RESULT_CODE_FAIL_LOGIN;
		}

		if(StringUtils.isEmpty(newsLttrIdx)){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){
			NewsLetterDTO vo = new NewsLetterDTO();
			vo.setNewsLttrIdx(newsLttrIdx);
			vo.setModMbrIdx(memberInfo.getMngrMbrIdx());
			newsletterService.deleteNewsletter(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 뉴스레터 구독자 목록 화면
	 * @param model
	 * @param vo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/subscriberList","/subscriberListExcel","/subscriberListExcel_NameEmail"})
	public String selectSubscriberList(ModelMap model, ReqSubscriberVO vo, HttpServletResponse response, HttpServletRequest request) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if(StringUtils.isEmpty(vo.getCreDttm())){
			vo.setCreDttmOne("");;
			vo.setCreDttmTwo(sdf.format(date));
		}

		//구독자 목록
		List<SubscriberMVO> subscriberList = null;

		//신청 구분
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._032.ROOT.getValue());
		List<CommonCodeDTO> scbLocCds = commonCodeService.selectList(voA);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){

			subscriberList = newsletterService.selectSubscriberListExcel(vo);

			SimpleDateFormat excelSdf = new SimpleDateFormat("yyyyMMdd");

			if(request.getRequestURI().indexOf("NameEmail") > 0){
				response.setHeader("Content-Disposition", "ATTachment; Filename=subscriberList_NameEmail_"+excelSdf.format(date)+".xls");
			}else{
				response.setHeader("Content-Disposition", "ATTachment; Filename=subscriberList_"+excelSdf.format(date)+".xls");
			}
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;

		}else{

			subscriberList = newsletterService.selectSubscriberList(vo);

			//신청 구분별 구독자 수
			SubscriberMVO subscriberVO = newsletterService.selectSCBCnt();

			model.addAttribute("subscriberVO",subscriberVO);
			model.addAttribute("searchVO",vo);
			model.addAttribute("paginationInfo",vo.getPageInfo());
		}

		model.addAttribute("subscriberList",subscriberList);
		model.addAttribute("scbLocCds",scbLocCds);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return jsp;
	}
}
