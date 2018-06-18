package kr.maxerve.admin.reserve.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.mail.service.MailService;
import kr.maxerve.admin.reserve.service.ReserveService;
import kr.maxerve.admin.reserve.vo.ParkTourMVO;
import kr.maxerve.admin.reserve.vo.ParkTourUVO;
import kr.maxerve.admin.reserve.vo.ReqParkTourVO;
import kr.maxerve.admin.reserve.vo.TourCarMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.TourAttendDTO;

/**
* TourController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 파크투어
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/reserve/tour")
public class TourController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name="reserveService")
	private ReserveService reserveService;

	@Resource(name="mailService")
	private MailService mailService;

	@Value("#{propertiesService['mail.sender']}")
	private String mailSender;

	/**
	 * 파크투어 예약현황 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/parktourReserve_list","/parktourReserve_listExcel"})
	public String selectParktourReserveList(ModelMap model, ReqParkTourVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("파크투어 예약현황 화면");

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		Date date = new Date();

		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(new SimpleDateFormat("yyyy.MM.dd").format(date));
		}

		//예약진행상태
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._020.ROOT.getValue());
		List<CommonCodeDTO> tourPgrs = commonCodeService.selectList(voA);

		//예약목록
		List<ParkTourMVO> reserveList = new ArrayList<>();

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			reserveList = reserveService.selectParktourReserveListExcel(vo);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        response.setHeader("Content-Disposition", "ATTachment; Filename=parktourReserveList"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
			jsp = "empty/" + jsp;
		}else{
			reserveList = reserveService.selectParktourReserveList(vo);
		}

		model.addAttribute("reserveList",reserveList);
		model.addAttribute("tourPgrs", tourPgrs);
		model.addAttribute("searchVO", vo);
		model.addAttribute("paginationInfo",vo.getPageInfo());
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return jsp;
	}

	/**
	 * 파크투어 예약 상세 화면
	 * @param model
	 * @param tourAplyIdx
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={"/parktourReserve_info","/parktourReserve_infoExcel"})
	public String selectParktourReserveInfo(ModelMap model, @RequestParam String tourAplyIdx, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//파크투어 상세
		ParkTourMVO parkTourInfo = reserveService.selectParktourReserveInfo(tourAplyIdx);

		Date date = new Date();
		CalendarDTO dto = new CalendarDTO();
		dto.setClndDt(new SimpleDateFormat("yyyyMM").format(date));
		//파크투어 확정 가능한 일정
		List<CalendarDTO> tourScheduleList = reserveService.selectTourScheduleList(dto);

		//파크투어 명단
		List<TourAttendDTO> tourAttendList = reserveService.selectTourAttendList(tourAplyIdx);

		//주차차량 목록
		List<TourCarMVO> tourCarList = reserveService.selectTourCarList(tourAplyIdx);

		//예약진행상태
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._020.ROOT.getValue());
		List<CommonCodeDTO> tourPgrs = commonCodeService.selectList(voA);

		model.addAttribute("parkTourInfo",parkTourInfo);
		model.addAttribute("tourScheduleList",tourScheduleList);
		model.addAttribute("tourAttendList",tourAttendList);
		model.addAttribute("tourCarList",tourCarList);
		model.addAttribute("tourPgrs",tourPgrs);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	        response.setHeader("Content-Disposition", "ATTachment; Filename=parktourReserveList"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}

		return jsp;
	}


	/**
	 * 파크투어 예약 수정
	 * @param model
	 * @param vo
	 * @param bindingResult
	 * @throws Exception
	 */
	@RequestMapping(value="/updateParktourReserve_info", method=RequestMethod.POST)
	public void updateParktourReserveInfo(ModelMap model, @Valid @RequestBody ParkTourUVO vo, BindingResult bindingResult) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		reserveService.updateParktourReserveInfo(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}
}
