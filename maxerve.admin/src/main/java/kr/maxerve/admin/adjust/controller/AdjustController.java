package kr.maxerve.admin.adjust.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.adjust.service.AdjustService;
import kr.maxerve.admin.adjust.vo.AdjustHistoryMVO;
import kr.maxerve.admin.adjust.vo.AdjustOrganizationMVO;
import kr.maxerve.admin.adjust.vo.AdjustSummaryMVO;
import kr.maxerve.admin.adjust.vo.PaymentSummaryMVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqPaymentVO;
import kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO;
import kr.maxerve.admin.payment.service.PaymentService;
import kr.maxerve.admin.payment.vo.PaymentMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.DateTimeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AdjustHistoryDTO;
import kr.maxerve.dto.AdjustOrganizationDTO;
import kr.maxerve.dto.PayFeesDTO;

/**
* AdjustController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/adjust/adjust")
public class AdjustController {

	@Resource(name="adjustService")
	private AdjustService adjustService;

	@Resource(name="paymentService")
	private PaymentService paymentService;

	@Value("#{propertiesService['pagination.countPerPage']}")
	private String paginationCountPerPage;

	@Value("#{propertiesService['pagination.pagePerBlock']}")
	private String paginationPagePerBlock;

	/**
	 * 정산단체등록화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/organization_write")
	public void writeOrganization(ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		AdjustOrganizationMVO woodpark = new AdjustOrganizationMVO();		// 우드파크
		AdjustOrganizationMVO makerspace = new AdjustOrganizationMVO();	// 메이커스페이스

		// 정산단체목록
		List<AdjustOrganizationMVO> list = adjustService.selectOrganizationList();

		for (AdjustOrganizationMVO adjustOrganizationMVO : list) {
			if (CommonCodeUtil._002._004.getValue().equals(adjustOrganizationMVO.getLocTypCd())) { // 우드파크
				woodpark = adjustOrganizationMVO;
			} else if (CommonCodeUtil._002._003.getValue().equals(adjustOrganizationMVO.getLocTypCd())) { // 메이커스페이스
				makerspace = adjustOrganizationMVO;
			}
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("woodpark", woodpark);
		model.addAttribute("makerspace", makerspace);
	}

	/**
	 * 정산단체등록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/organization_insert", method=RequestMethod.POST)
	public void insertOrganization(@RequestBody List<AdjustOrganizationDTO> vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		adjustService.insertOrganization(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 수수료관리화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fees_write")
	public void writeFees(ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		String card = "";		// 신용카드
		String bank = "";		// 계좌이체
		String kakaopay = "";	// 카카오페이

		card = adjustService.selectPayFees("CARD");
		bank = adjustService.selectPayFees("BANK");
		kakaopay = adjustService.selectPayFees("KAKAOPAY");

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("card", card);
		model.addAttribute("bank", bank);
		model.addAttribute("kakaopay", kakaopay);
	}

	/**
	 * 수수료등록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/fees_insert", method=RequestMethod.POST)
	public void insertFees(@RequestBody List<PayFeesDTO> vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		adjustService.insertPayFees(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 정산목록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/summary_list", "/summary_list_excel"})
	public String selectSummaryList(ReqAdjustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<AdjustSummaryMVO> summaryList = new ArrayList<>();		// 정산목록
		String jsp = request.getRequestURI().substring(1).replace(".do", "");


		if(jsp.indexOf("_excel") > 0){
			summaryList = adjustService.selectSummaryList(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=adjust_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			PaginationInfo pageInfo;	// 페이징

			if (vo.getCountPerPage() < 1) {
				vo.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(vo.getPage()));
			pageInfo.setRecordCountPerPage(vo.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(adjustService.selectSummaryCount(vo));

			if (pageInfo.getTotalRecordCount() > 0) {
				vo.setLimitIndex(pageInfo.getFirstRecordIndex());

				summaryList = adjustService.selectSummaryList(vo);
			}

			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(summaryList);

		return jsp;
	}

	/**
	 * 결제목록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/payment_list", "/payment_list_excel"})
	public String selectPayList(ReqAdjustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<PaymentMVO> paymentList = new ArrayList<>();	// 결제목록

		// 결제목록
		ReqPaymentVO param = new ReqPaymentVO();
		String dt = vo.getDt();

		switch (dt.length()) {
		case 4:
			param.setSrtDt(dt + ".01.01");
			param.setEndDt(dt + ".12.31");
			break;

		case 7:
			param.setSrtDt(dt + ".01");
			param.setEndDt(dt + "." + DateTimeUtil.fnLastday(Integer.valueOf(StringUtils.left(dt, 4)), Integer.valueOf(StringUtils.right(dt, 2))));
			break;

		case 10:
			param.setSrtDt(dt);
			param.setEndDt(dt);
			break;
		}

		param.setPage(vo.getPaymentPage());
		param.setSrchLocTyp(vo.getSrchLocTyp());	// 결제항목
		param.setPayMthd(vo.getPayMthd());		// 결제수단
		param.setSrchStatus(vo.getSrchStatus());	// 상태

		if(jsp.indexOf("_excel") > 0){
			// 결제목록
			paymentList = paymentService.selectList(param);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=adjust_payment_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			AdjustSummaryMVO summaryInfo;		// 정산요약
			PaymentSummaryMVO paymentSummaryMVO;		// 정산결졔요약
			PaginationInfo pageInfo;	// 페이징

			// 정산결제요약
			paymentSummaryMVO = adjustService.selectPaymentSummary(vo.getDt());

			// 정산요약
			summaryInfo = adjustService.selectSummaryList(vo).get(0);

			// 결제목록

			if (param.getCountPerPage() < 1) {
				param.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(param.getPage()));
			pageInfo.setRecordCountPerPage(param.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(paymentService.selectCount(param));

			if (pageInfo.getTotalRecordCount() > 0) {
				param.setLimitIndex(pageInfo.getFirstRecordIndex());

				paymentList = paymentService.selectList(param);
			}

			model.addAttribute(summaryInfo);
			model.addAttribute(paymentSummaryMVO);
			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(paymentList);

		return jsp;
	}

	/**
	 * 단체별정산목록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/summary_organization_list", "/summary_organization_list_excel"})
	public String selectSummaryList(ReqOrganizationSummaryVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<AdjustSummaryMVO> summaryList = new ArrayList<>();		// 정산목록

		if(jsp.indexOf("_excel") > 0){
			// 결제목록
			summaryList = adjustService.selectSummaryOrganizationList(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=organization_adjust_month_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			PaginationInfo pageInfo;	// 페이징

			if (vo.getCountPerPage() < 1) {
				vo.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(vo.getPage()));
			pageInfo.setRecordCountPerPage(vo.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(adjustService.selectSummaryOrganizationCount(vo));

			if (pageInfo.getTotalRecordCount() > 0) {
				vo.setLimitIndex(pageInfo.getFirstRecordIndex());

				// 결제목록
				summaryList = adjustService.selectSummaryOrganizationList(vo);
			}

			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(summaryList);

		return jsp;
	}

	/**
	 * 업체별정산목록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/organization_list", "/organization_list_excel"})
	public String selectOrganizationList(ReqOrganizationSummaryVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String jsp = request.getRequestURI().substring(1).replace(".do", "");
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<AdjustHistoryMVO> organizationList = new ArrayList<>();	// 단체목록

		// 단체목록
		ReqAdjustHistoryVO param = new ReqAdjustHistoryVO();
		String dt = vo.getDt();

		param.setSrtDt(dt + ".01");
		param.setEndDt(dt + "." + DateTimeUtil.fnLastday(Integer.valueOf(StringUtils.left(dt, 4)), Integer.valueOf(StringUtils.right(dt, 2))));
		param.setOztnNm(vo.getOztnNm());
		param.setAjmYn(vo.getAjmYn());

		if(jsp.indexOf("_excel") > 0){
			// 단체목록
			organizationList = adjustService.selectHistoryList(param);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=organization_adjust_list_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		} else {
			AdjustSummaryMVO summaryInfo;		// 정산요약
			PaginationInfo pageInfo;	// 페이징

			// 정산요약
			summaryInfo = adjustService.selectSummaryOrganizationList(vo).get(0);

			if (param.getCountPerPage() < 1) {
				param.setCountPerPage(Integer.valueOf(paginationCountPerPage));
			}

			// 단체목록
			pageInfo = new PaginationInfo();
			pageInfo.setCurrentPageNo(Integer.valueOf(vo.getOrganizationPage()));
			pageInfo.setRecordCountPerPage(param.getCountPerPage());
			pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
			pageInfo.setTotalRecordCount(adjustService.selectHistoryCount(param));

			if (pageInfo.getTotalRecordCount() > 0) {
				param.setLimitIndex(pageInfo.getFirstRecordIndex());

				organizationList = adjustService.selectHistoryList(param);
			}

			model.addAttribute(summaryInfo);
			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(organizationList);

		return jsp;
	}

	/**
	 * 단체별 결제목록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/organization_payment_list")
	public void selectOrganizationPayList(ReqOrganizationSummaryVO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		AdjustHistoryMVO adjustHistoryInfo;	// 단체요약
		List<PaymentMVO> paymentList = new ArrayList<>();	// 결제목록
		PaginationInfo pageInfo;	// 페이징

		// 단체요약
		ReqAdjustHistoryVO param = new ReqAdjustHistoryVO();
		String dt = vo.getDt();

		param.setSrtDt(dt + ".01");
		param.setEndDt(dt + "." + DateTimeUtil.fnLastday(Integer.valueOf(StringUtils.left(dt, 4)), Integer.valueOf(StringUtils.right(dt, 2))));
		param.setLocTypCd(vo.getLocTypCd());
		param.setLocIdx(vo.getLocIdx());

		adjustHistoryInfo = adjustService.selectHistoryList(param).get(0);

		// 결제목록
		ReqPaymentVO param1 = new ReqPaymentVO();

		param1.setOztnLocTypCd(vo.getLocTypCd());
		param1.setOztnLocIdx(vo.getLocIdx());

		param1.setSrtDt(dt + ".01");
		param1.setEndDt(dt + "." + DateTimeUtil.fnLastday(Integer.valueOf(StringUtils.left(dt, 4)), Integer.valueOf(StringUtils.right(dt, 2))));

		param1.setPage(vo.getPaymentPage());

		if (param1.getCountPerPage() < 1) {
			param1.setCountPerPage(Integer.valueOf(paginationCountPerPage));
		}

		pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(Integer.valueOf(param1.getPage()));
		pageInfo.setRecordCountPerPage(param1.getCountPerPage());
		pageInfo.setPageSize(Integer.valueOf(paginationPagePerBlock));
		pageInfo.setTotalRecordCount(paymentService.selectCount(param1));

		if (pageInfo.getTotalRecordCount() > 0) {
			param1.setLimitIndex(pageInfo.getFirstRecordIndex());

			paymentList = paymentService.selectList(param1);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(adjustHistoryInfo);
		model.addAttribute(paymentList);
		model.addAttribute(pageInfo);
	}

	/**
	 * 정산완료등록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/history_insert", method=RequestMethod.POST)
	public void insertHistory(AdjustHistoryDTO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		adjustService.insertHistory(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
