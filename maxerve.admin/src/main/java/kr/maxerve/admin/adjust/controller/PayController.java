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
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.adjust.vo.ReqPaymentVO;
import kr.maxerve.admin.facilities.service.FacilitiesService;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.member.vo.ReqMemberVO;
import kr.maxerve.admin.payment.service.PaymentService;
import kr.maxerve.admin.payment.vo.PaymentMVO;
import kr.maxerve.admin.reserve.service.ReserveService;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.FacilitiesMasterDTO;

/**
* PayController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 결제
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/

@Controller
@RequestMapping(value="/adjust/pay")
public class PayController {
	@Resource(name="paymentService")
	private PaymentService paymentService;

	@Resource(name="reserveService")
	private ReserveService reserveService;

	@Resource(name="facilitiesService")
	private FacilitiesService facilitiesService;

	@Resource(name="memberService")
	private MemberService memberService;

	@Value("#{propertiesService['pagination.countPerPage']}")
	private String paginationCountPerPage;

	@Value("#{propertiesService['pagination.pagePerBlock']}")
	private String paginationPagePerBlock;

	/**
	 * 결제목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/list", "list_excel"})
	public String selectList(ReqPaymentVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		List<PaymentMVO> paymentList = new ArrayList<>();	// 결제목록

		if(jsp.indexOf("_excel") > 0){
			paymentList = paymentService.selectList(vo);

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=payment_list_"+sdf.format(date)+".xls");
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
			pageInfo.setTotalRecordCount(paymentService.selectCount(vo));

			if (pageInfo.getTotalRecordCount() > 0) {
				vo.setLimitIndex(pageInfo.getFirstRecordIndex());

				paymentList = paymentService.selectList(vo);
			}

			model.addAttribute(pageInfo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(paymentList);

		return jsp;
	}

	/**
	 * 결제상세 화면
	 * @param vo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/detail", "/detail_excel"})
	public String selectDetail(ReqPaymentVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		String jsp = request.getRequestURI().substring(1).replace(".do", "");

		PaymentMVO info;	// 결제상세
		List<String> paymentDetailList = new ArrayList<>();		// 결제상세내역
		MemberMVO buyerInfo;	// 사용자정보

		// 결제상세
		info = paymentService.selectInfo(vo.getPayIdx());

		if (CommonCodeUtil._010._018.getValue().equals(info.getLocTypCd())) { // 시설예약일경우 결제상세
			FacilitiesReserveMVO facilitiesReserveInfo = reserveService.selectReserveInfo(info.getLocIdx());
			FacilitiesMasterDTO facilitiesInfo = facilitiesService.selectInfo(facilitiesReserveInfo.getFctMstIdx());

			int timeCount = reserveService.selectMinuteCount(info.getLocIdx());

			if ("60".equals(facilitiesInfo.getRsvtUnit())) {
				timeCount = (int) Math.floor(timeCount/2);
			}

			if (timeCount == 0) {
				timeCount = 1;
			}


			String detail = "";
			detail += facilitiesReserveInfo.getSrtDttm() + " ~ " + facilitiesReserveInfo.getEndDttm() + " (총";
			detail += (timeCount * ("60".equals(facilitiesInfo.getRsvtUnit()) ? 1:30)) + ("60".equals(facilitiesInfo.getRsvtUnit()) ? "시간":"분") + ") = ";
			detail += (timeCount * Integer.valueOf(StringUtils.isEmpty(facilitiesInfo.getDftPrc()) ? "0":facilitiesInfo.getDftPrc()));
			detail += "원";

			paymentDetailList.add(detail);

			detail = "";

			List<EquipmentMasterDTO> eqipList = reserveService.selectEquipList(info.getLocIdx());

			int totalEquipPrice = 0;

			for (EquipmentMasterDTO equipmentMasterDTO : eqipList) {
				if (!StringUtils.isEmpty(detail)) {
					detail += " + ";
				}

				detail += equipmentMasterDTO.getEqpNm() + equipmentMasterDTO.getEqpTcnt() + "(";

				int equipPrice = timeCount * Integer.valueOf(equipmentMasterDTO.getEqpTcnt());

				if ("60".equals(facilitiesInfo.getRsvtUnit())) {
					equipPrice *= Integer.valueOf(equipmentMasterDTO.getEqpHourPrc());

				} else {
					equipPrice *= Integer.valueOf(equipmentMasterDTO.getEqpHalfHourPrc());
				}

				detail += equipPrice + "원)";

				totalEquipPrice += equipPrice;
			}

			detail += " = " + totalEquipPrice + "원";

			paymentDetailList.add(detail);
		}

		// 사용자정보
		ReqMemberVO param = new ReqMemberVO();
		param.setMbrIdx(info.getMbrIdx());

		buyerInfo = memberService.selectOnlineMemberInfo(param);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(info);
		model.addAttribute(paymentDetailList);
		model.addAttribute(buyerInfo);

		if(jsp.indexOf("_excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=payment_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");

	        jsp = "empty/" + jsp;
		}

		return jsp;
	}
}
