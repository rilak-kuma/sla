package kr.maxerve.admin.reserve.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.maxerve.admin.calendar.service.CalendarService;
import kr.maxerve.admin.calendar.vo.CalendarVO;
import kr.maxerve.admin.cmmn.service.CommonCodeService;
import kr.maxerve.admin.facilities.service.FacilitiesService;
import kr.maxerve.admin.facilities.vo.MakerspaceClassMVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.reserve.service.ReserveService;
import kr.maxerve.admin.reserve.vo.CalenderFacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveUVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveInfoMVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveMVO;
import kr.maxerve.admin.reserve.vo.ReqEquipmentMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesReserveVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassIVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassIVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassScheduleIVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.ResponseUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.CommonCodeDTO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.FacilitiesScheduleDTO;
import kr.maxerve.dto.MakerspaceClassCurriculumDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.MakerspaceClassGroupDTO;
import kr.maxerve.dto.TimeDTO;
import kr.maxerve.dto.WoodparkClassDTO;
import kr.maxerve.dto.WoodparkClassScheduleDTO;

/**
* FacilitiesReserveController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 시설예약
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Controller
@RequestMapping(value="/reserve/facilities")
public class FacilitiesReserveController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="facilitiesService")
	private FacilitiesService facilitiesService;

	@Resource(name="commonCodeService")
	private CommonCodeService commonCodeService;

	@Resource(name="calendarService")
	private CalendarService calendarService;

	@Resource(name="reserveService")
	private ReserveService reserveService;

	@Resource(name="fileService")
	private FileService fileService;

	/**
	 * 시설예약 현황화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public void selectReserveStatus(ReqFacilitiesReserveVO vo, ModelMap model) throws Exception
	{
		logger.debug("");

		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<FacilitiesMasterDTO> facilitiesList = new ArrayList<>(); // 시설목록

		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());

		facilitiesList = facilitiesService.selectFacilitiesList(param);

		if (!facilitiesList.isEmpty() && StringUtils.isEmpty(vo.getFctMstIdx())) {
			vo.setFctMstIdx(facilitiesList.get(0).getFctMstIdx());
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(facilitiesList);
	}

	/**
	 * 시설예약등록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert")
	public void updateFacilities(ReqFacilitiesMasterVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<FacilitiesMasterDTO> facilitiesList = new ArrayList<>(); // 시설목록
		FacilitiesMasterDTO info = new FacilitiesMasterDTO();	// 시설정보
		List<FacilitiesScheduleDTO> scheduleList = new ArrayList<>(); // 스케쥴목록
		List<EquipmentMasterDTO> equipList = new ArrayList<>(); // 장비목록
		List<EquipmentMasterDTO> facilitiesEquipList = new ArrayList<>(); // 시설관련장비목록


		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());

		facilitiesList = facilitiesService.selectFacilitiesList(param);

		// 시설정보
		if (!StringUtils.isEmpty(vo.getFctMstIdx())) {
			info = facilitiesService.selectInfo(vo.getFctMstIdx());

			// 시설운영목록
			scheduleList = facilitiesService.selectScheduleList(vo.getFctMstIdx());

			// 시설관련장비목록
			facilitiesEquipList = facilitiesService.selectFacilitiesEquipList(vo.getFctMstIdx());
		}

		// 장비목록
		equipList = facilitiesService.selectEquipList();

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(facilitiesList);
		model.addAttribute(info);
		model.addAttribute(scheduleList);
		model.addAttribute(equipList);
		model.addAttribute("facilitiesEquipList", facilitiesEquipList);
	}

	/**
	 * 시설예약수정
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public void updateFacilitiesProc(@RequestBody ReqFacilitiesMasterIVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		facilitiesService.insert(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 메이커스페이스 클래스 일정목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/makerspace_class_list")
	public void makerspaceClassList(ReqMakerspaceClassVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());

		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(param);

		List<MakerspaceClassDTO> list = facilitiesService.selectMakerspaceClassList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(facilitiesList);
		model.addAttribute(list);
	}

	/**
	 * 메이커스페이스 등록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/makerspace_class_insert")
	public void insertMakerspaceClass(ReqMakerspaceClassVO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		// 시설목록
		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());

		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(param);
		MakerspaceClassMVO info = new MakerspaceClassMVO();
		List<MakerspaceClassGroupDTO> groupTimeList = new ArrayList<>();
		List<MakerspaceClassCurriculumDTO> curriculumList = new ArrayList<>();

		// 메이커스페이스 클래스 목록
		if (!StringUtils.isEmpty(vo.getMkspClsIdx())) {
			info = facilitiesService.selectMakerspaceClassInfo(vo.getMkspClsIdx());

			// 메이커스페이스 클래스 반 시간 목록
			groupTimeList = facilitiesService.selectMakerspaceClassGroupTimeList(vo.getMkspClsIdx());

			// 메이커스페이스 클래스 교육과정 목록
			curriculumList = facilitiesService.selectMakerspaceClassCurriculumList(vo.getMkspClsIdx());
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(facilitiesList);
		model.addAttribute(info);
		model.addAttribute(groupTimeList);
		model.addAttribute(curriculumList);
	}

	/**
	 * 메이커스페이스 등록
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/makerspace_class_insert", method=RequestMethod.POST)
	public void insertMakerspaceClassProc(@RequestBody ReqMakerspaceClassIVO vo, ModelMap model) throws Exception {
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		facilitiesService.insertMakerspaceClass(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 메이커스페이스 클래스 삭제
	 * @param mkspClsIdx
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/makerspace_class_delete", method=RequestMethod.POST)
	public void deleteMakerspaceClass(@RequestParam String mkspClsIdx, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		facilitiesService.deleteMakerspaceClass(mkspClsIdx);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 우드파크 목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/woodpark_list")
	public void selectWoodparkList(ReqFacilitiesMasterVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<FacilitiesMasterDTO> list = new ArrayList<>();

		vo.setRsvtPlcCd(CommonCodeUtil._002._004.getValue());

		list = facilitiesService.selectFacilitiesExtraList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);
	}

	/**
	 * 우드파크 클래스 관리 화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/woodpark_class")
	public void selectWoodparkClass(ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<CommonCodeDTO> priceTypeList = new ArrayList<>(); // 사용료 구분코드 목록
		List<WoodparkClassDTO> woodparkClassList = new ArrayList<>(); // 등록목록

		priceTypeList = commonCodeService.selectList(CommonCodeUtil._028.ROOT.getValue());
		woodparkClassList = facilitiesService.selectWoodparkClassList();

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(priceTypeList);
		model.addAttribute(woodparkClassList);
	}

	/**
	 * 우드파크 클래스 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/woodpark_class_insert", method=RequestMethod.POST)
	public void insertWoodparkClass(@Valid @RequestBody ReqWoodparkClassIVO vo, BindingResult bindingResult, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			facilitiesService.insertWoodparkClass(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 우드파크클래스 일정관리 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/woodpark_class_schedule")
	public void selectWoodparkClassSchedule(ReqFacilitiesMasterVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (StringUtils.isEmpty(vo.getYear()) || StringUtils.isEmpty(vo.getMonth())) {
			vo.setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
			vo.setMonth(StringUtils.right(("0" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1)), 2));
		}


		FacilitiesMasterDTO info = new FacilitiesMasterDTO(); // 시설정보
		List<CalendarDTO> calendarList = new ArrayList<>(); // 달력목록
		List<FacilitiesScheduleDTO> timeList = new ArrayList<>(); // 일정시간대 목록
		List<FacilitiesScheduleDTO> scheduleList = new ArrayList<>(); // 요일별 시간대 목록
		List<WoodparkClassDTO> woodparkClassList = new ArrayList<>(); // 우드파크 클래스 목록
		List<WoodparkClassScheduleDTO> woodparkClassScheduleList = new ArrayList<>(); // 우드파크 클래스 일정목록

		CalendarVO param = new CalendarVO();
		param.setSrtDt(vo.getYear() + "." + vo.getMonth() + ".01");
		param.setEndDt(vo.getYear() + "." + vo.getMonth() + ".31");

		calendarList = calendarService.selectList(param);

		if (!calendarList.get(0).getDow().equals("1")) {
			for (int i = Integer.valueOf(calendarList.get(0).getDow()) - 1; i > 0; i--) {
				calendarList.add(0, new CalendarDTO());
			}
		}

		if (calendarList.size() % 7 != 0) {
			for (int i = 7 - calendarList.size() % 7; i > 0; i--) {
				calendarList.add(new CalendarDTO());
			}
		}

		FacilitiesMasterDTO fmParam = new FacilitiesMasterDTO();
		fmParam.setRsvtPlcCd(CommonCodeUtil._002._004.getValue());
		fmParam.setUseYn("Y");
		List<FacilitiesMasterDTO> fmList = facilitiesService.selectFacilitiesList(fmParam);
		if(fmList != null && fmList.size() > 0) {

			vo.setFctMstIdx(fmList.get(0).getFctMstIdx());

			info = facilitiesService.selectInfo(vo.getFctMstIdx());
			timeList = facilitiesService.selectTimeList(vo.getFctMstIdx());
			scheduleList = facilitiesService.selectScheduleList(vo.getFctMstIdx());
			woodparkClassList = facilitiesService.selectWoodparkClassList();
			woodparkClassScheduleList = facilitiesService.selectWoodparkClassScheduleList(vo);
		}
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(info);
		model.addAttribute(calendarList);
		model.addAttribute("timeList", timeList);
		model.addAttribute(scheduleList);
		model.addAttribute(woodparkClassList);
		model.addAttribute(woodparkClassScheduleList);
	}

	/**
	 * 우드파크 클래스 일정 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/woodpark_class_schedule_insert", method=RequestMethod.POST)
	public void insertWoodparkClassSchedule(@Valid @RequestBody ReqWoodparkClassScheduleIVO vo, BindingResult bindingResult, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			facilitiesService.insertWoodparkClassSchedule(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 대여장비관리 화면
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/equip")
	public void selectEquip(ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<EquipmentMasterDTO> list = new ArrayList<>(); // 등록목록

		list = facilitiesService.selectEquipList();

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);
	}

	/**
	 * 대여장비 등록
	 * @param vo
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/equip_insert", method=RequestMethod.POST)
	public void insertEquip(@Valid @RequestBody ReqEquipmentMasterIVO vo, BindingResult bindingResult, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		if (bindingResult.hasErrors()) {
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		} else {
			facilitiesService.insertEquip(vo);
		}

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 파크투어 목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/parktour_list")
	public void selectParktourList(ReqFacilitiesMasterVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<FacilitiesMasterDTO> list = new ArrayList<>();

		vo.setRsvtPlcCd(CommonCodeUtil._002._007.getValue());

		list = facilitiesService.selectFacilitiesExtraList(vo);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(list);
	}

	/**
	 * 시설예약등록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/parktour_insert")
	public String insertParktour(ReqFacilitiesMasterVO vo, ModelMap model) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		List<FacilitiesMasterDTO> facilitiesList = new ArrayList<>(); // 시설목록
		FacilitiesMasterDTO info = new FacilitiesMasterDTO();	// 시설정보
		List<FacilitiesScheduleDTO> scheduleList = new ArrayList<>(); // 스케쥴목록
		List<EquipmentMasterDTO> equipList = new ArrayList<>(); // 장비목록
		List<EquipmentMasterDTO> facilitiesEquipList = new ArrayList<>(); // 시설관련장비목록


		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());

		facilitiesList = facilitiesService.selectFacilitiesList(param);

		// 시설정보
		if (!StringUtils.isEmpty(vo.getFctMstIdx())) {
			info = facilitiesService.selectInfo(vo.getFctMstIdx());

			// 시설운영목록
			scheduleList = facilitiesService.selectScheduleList(vo.getFctMstIdx());

			// 시설관련장비목록
			facilitiesEquipList = facilitiesService.selectFacilitiesEquipList(vo.getFctMstIdx());
		}

		// 장비목록
		equipList = facilitiesService.selectEquipList();

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute(facilitiesList);
		model.addAttribute(info);
		model.addAttribute(scheduleList);
		model.addAttribute(equipList);
		model.addAttribute("facilitiesEquipList", facilitiesEquipList);

		return "reserve/facilities/insert";
	}


	/**
	 * 모두모임방 & 야외공간 예약 현황 목록 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/reserve_list")
	public void selectReserveList(ModelMap model, ReqFacilitiesReserveVO vo) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String mainTitle = "";
		String subTitle = "";

		if(StringUtils.isEmpty(vo.getCreDttmTwo())){
			Date date = new Date();
			vo.setCreDttmOne("");
			vo.setCreDttmTwo(new SimpleDateFormat("yyyy.MM.dd").format(date));
		}

		//예약진행상태
		CommonCodeDTO voA = new CommonCodeDTO();
		voA.setDep1(CommonCodeUtil._020.ROOT.getValue());
		List<CommonCodeDTO> rsvtPgrs = commonCodeService.selectList(voA);


		if(vo.getRsvtPlcCd().equals(CommonCodeUtil._002._001.getValue())){
			mainTitle = "모두모임방 예약현황";
			subTitle = "모두모임방 예약";
			vo.setFctNm("모두모임방");
		}else if(vo.getRsvtPlcCd().equals(CommonCodeUtil._002._002.getValue())){
			mainTitle = "야외공간 예약현황";
			subTitle = "야외공간 예약";
		}

		List<FacilitiesReserveMVO> reserveList = reserveService.selectReserveList(vo);

		//시설 목록
		FacilitiesMasterDTO param = new FacilitiesMasterDTO();
		param.setRsvtPlcCd(vo.getRsvtPlcCd());
		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(param);

		model.addAttribute("facilitiesList",facilitiesList);
		model.addAttribute("reserveList",reserveList);
		model.addAttribute("rsvtPgrs",rsvtPgrs);
		model.addAttribute("searchVO",vo);
		model.addAttribute("paginationInfo",vo.getPageInfo());
		model.addAttribute("mainTitle", mainTitle);
		model.addAttribute("subTitle", subTitle);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 모두모임방 & 야외공간 예약 상세 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/reserve_info")
	public void selectReserveInfo(ModelMap model, ReqFacilitiesReserveVO vo) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		String mainTitle = "";

		if(StringUtils.isEmpty(vo.getFctRsvtIdx()) ){
			resultCode = ResponseUtil.RESULT_CODE_INVALID;
		}

		if(resultCode.equals(ResponseUtil.RESULT_CODE_SUCESS)){

			FacilitiesReserveMVO reserveInfo = reserveService.selectReserveInfo(vo.getFctRsvtIdx());

			//예약진행상태
			CommonCodeDTO voA = new CommonCodeDTO();
			voA.setDep1(CommonCodeUtil._020.ROOT.getValue());
			List<CommonCodeDTO> rsvtPgrs = commonCodeService.selectList(voA);


			if(vo.getRsvtPlcCd().equals(CommonCodeUtil._002._001.getValue())){
				mainTitle = "모두모임방 예약현황";

				//첨부파일 리스트
				AttachFileDTO fileVO = new AttachFileDTO();
				fileVO.setAtcLocCd(vo.getRsvtPlcCd());
				fileVO.setAtcLocIdx(reserveInfo.getFctRsvtIdx());
				List<FileMVO> fileList = fileService.selectAttachList(fileVO);

				model.addAttribute("fileList",fileList);

			}else if(vo.getRsvtPlcCd().equals(CommonCodeUtil._002._002.getValue())){
				mainTitle = "야외공간 예약현황";
			}

			model.addAttribute("reserveInfo",reserveInfo);
			model.addAttribute("rsvtPgrs",rsvtPgrs);
			model.addAttribute("mainTitle", mainTitle);
		}
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 모두모임방 & 야외공간 예약 수정
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/updateReserve", method=RequestMethod.POST)
	public void updateReserve(ModelMap model, @Valid FacilitiesReserveUVO vo, BindingResult bindingResult) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;
		reserveService.updateReserve(vo);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 예약현황(달력) 화면 : 미래청(회의/세미나), 모두모임방, 야외공간, 미래청(녹음/운동)
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/scheduleCalendar","/scheduleCalendarPop"})
	public String selectscheduleCalendar(ModelMap model, ReqFacilitiesMasterVO vo, HttpServletRequest request) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		FacilitiesMasterDTO info = new FacilitiesMasterDTO();			// 시설정보
		List<CalendarDTO> calendarList = new ArrayList<>();				// 달력목록
		List<FacilitiesScheduleDTO> timeList = new ArrayList<>();		// 일정시간대 목록
		List<FacilitiesScheduleDTO> scheduleList = new ArrayList<>();	// 요일별 시간대 목록
		List<TimeDTO> timeLineList;		// 시간목록

		CalendarVO param = new CalendarVO();
		Calendar srtCal = Calendar.getInstance() ;
		Calendar endCal = Calendar.getInstance() ;

		if(StringUtils.isNotEmpty(vo.getSumDay())){
			srtCal.set(Integer.valueOf(vo.getYear()), Integer.valueOf(vo.getMonth())-1, Integer.valueOf(vo.getSrtDay()));
			srtCal.set(Calendar.DATE, (srtCal.get(Calendar.DATE) + Integer.valueOf(vo.getSumDay() )));
		}else{
			srtCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY );
			vo.setSumDay("7");
		}

		endCal.set(srtCal.get(Calendar.YEAR), srtCal.get(Calendar.MONTH), srtCal.get(Calendar.DATE)+6);

		param.setSrtDt(srtCal.get(Calendar.YEAR) + "." + StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.MONTH)+1)), 2) + "." + StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.DATE)) ), 2));
		param.setEndDt(endCal.get(Calendar.YEAR) + "." + StringUtils.right(("0" + String.valueOf(endCal.get(Calendar.MONTH)+1)), 2) + "." + StringUtils.right(("0" + String.valueOf(endCal.get(Calendar.DATE)) ), 2));

		vo.setYear(String.valueOf(srtCal.get(Calendar.YEAR)));
		vo.setMonth(StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.MONTH)+1)), 2));
		vo.setSrtDay(StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.DATE))), 2));

		calendarList = calendarService.selectList(param);

		//시설별 타이틀, 디폴트시설idx, 검색조건 시간설정
		String mainTitle = "";
		if(vo.getFctMstIdx().isEmpty()){
			//미래청(회의/세미나) & 모두모임방
			if((CommonCodeUtil._002._001.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("1");
				if("1".equals(vo.getFctType())){
					vo.setFctMstIdx("9");
				}
			}

			//야외공간
			if((CommonCodeUtil._002._002.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("11");
			}

			//미래청(녹음/운동)
			if((CommonCodeUtil._002._006.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("22");
			}

			//참여동
			if((CommonCodeUtil._002._010.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("8");
			}

			//극장동
			if((CommonCodeUtil._002._008.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("34");
			}

			// 맛동
			if((CommonCodeUtil._002._009.getValue()).equals(vo.getRsvtPlcCd())){
				vo.setFctMstIdx("36");
			}
		}

		//미래청(회의/세미나) & 모두모임방
		if((CommonCodeUtil._002._001.getValue()).equals(vo.getRsvtPlcCd())){
			mainTitle = "미래청(회의/세미나)";
			if("1".equals(vo.getFctType())){
				mainTitle = "모두모임방";
			}
			vo.setSrtHm("09:00");
			vo.setEndHm("18:00");
		}

		//야외공간
		if((CommonCodeUtil._002._002.getValue()).equals(vo.getRsvtPlcCd())){
			mainTitle = "야외공간";
			vo.setSrtHm("09:00");
			vo.setEndHm("18:00");
		}

		//미래청(녹음/운동)
		if((CommonCodeUtil._002._006.getValue()).equals(vo.getRsvtPlcCd())){
			mainTitle = "미래청(녹음/운동)";
			vo.setSrtHm("09:00");
			vo.setEndHm("18:00");
		}

		// 극장동
		if((CommonCodeUtil._002._008.getValue()).equals(vo.getRsvtPlcCd())){
			mainTitle = "극장동";
			vo.setSrtHm("09:00");
			vo.setEndHm("18:00");
		}

		// 맛동
		if((CommonCodeUtil._002._009.getValue()).equals(vo.getRsvtPlcCd())){
			mainTitle = "맛동";
			vo.setSrtHm("09:00");
			vo.setEndHm("18:00");
		}

		//시설 목록
		FacilitiesMasterDTO dto = new FacilitiesMasterDTO();
		//dto.setRsvtPlcCd(CommonCodeUtil._002._006.getValue());
		dto.setRsvtPlcCd(vo.getRsvtPlcCd());
		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(dto);

		//시설 예약 목록
		vo.setSrtDt(param.getSrtDt());
		vo.setRsvtUnit(info.getRsvtUnit());
		List<CalenderFacilitiesReserveMVO> reserveList = reserveService.selectCalenderReserveStateList(vo);

		info = facilitiesService.selectInfo(vo.getFctMstIdx());
		timeList = facilitiesService.selectTimeList(vo.getFctMstIdx());
		scheduleList = facilitiesService.selectScheduleList(vo.getFctMstIdx());

		// 시간목록
		Map<String, String> param1 = new HashMap<>();
		param1.put("term", info.getRsvtUnit());
		timeLineList = calendarService.selectTimeList(param1);

		model.addAttribute("mainTitle",mainTitle);
		model.addAttribute("info",info);
		model.addAttribute("calendarList",calendarList);
		model.addAttribute("timeList", timeList);
		model.addAttribute("scheduleList",scheduleList);
		model.addAttribute("searchVO", vo);
		model.addAttribute("facilitiesList",facilitiesList);
		model.addAttribute("reserveList",reserveList);
		model.addAttribute("timeLineList", timeLineList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);
		if(request.getRequestURI().indexOf("Pop") > 0){
			jsp = "popup/" + jsp;
		}

		return jsp;
	}

	/**
	 * 메이커스페이스 예약현황 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/scheduleMakerSpace","scheduleMakerSpaceListExcel"})
	public String selectScheduleMakerSpace(ModelMap model, ReqFacilitiesMasterVO vo, ReqMakerspaceClassVO vo2, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		FacilitiesMasterDTO info = new FacilitiesMasterDTO();			// 시설정보
		List<CalendarDTO> calendarList = new ArrayList<>();				// 달력목록
		List<FacilitiesScheduleDTO> timeList = new ArrayList<>();		// 일정시간대 목록
		List<FacilitiesScheduleDTO> scheduleList = new ArrayList<>();	// 요일별 시간대 목록
		List<TimeDTO> timeLineList;		// 시간목록

		CalendarVO param = new CalendarVO();

		Calendar srtCal = Calendar.getInstance() ;
		Calendar endCal = Calendar.getInstance() ;
		if(StringUtils.isNotEmpty(vo.getSumDay())){
			srtCal.set(Integer.valueOf(vo.getYear()), Integer.valueOf(vo.getMonth())-1, Integer.valueOf(vo.getSrtDay()));
			srtCal.set(Calendar.DATE, (srtCal.get(Calendar.DATE) + Integer.valueOf(vo.getSumDay() )));
		}else{
			srtCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY );
			vo.setSumDay("7");
		}

		endCal.set(srtCal.get(Calendar.YEAR), srtCal.get(Calendar.MONTH), srtCal.get(Calendar.DATE)+6);

		param.setSrtDt(srtCal.get(Calendar.YEAR) + "." + StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.MONTH)+1)), 2) + "." + StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.DATE)) ), 2));
		param.setEndDt(endCal.get(Calendar.YEAR) + "." + StringUtils.right(("0" + String.valueOf(endCal.get(Calendar.MONTH)+1)), 2) + "." + StringUtils.right(("0" + String.valueOf(endCal.get(Calendar.DATE)) ), 2));

		vo.setYear(String.valueOf(srtCal.get(Calendar.YEAR)));
		vo.setMonth(StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.MONTH)+1)), 2));
		vo.setSrtDay(StringUtils.right(("0" + String.valueOf(srtCal.get(Calendar.DATE))), 2));

		calendarList = calendarService.selectList(param);

		//시설 목록
		FacilitiesMasterDTO dto = new FacilitiesMasterDTO();
		dto.setRsvtPlcCd(CommonCodeUtil._002._003.getValue());
		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(dto);

		//제목
		String mainTitle = "메이커스페이스";
		vo.setSrtHm("09:00");
		vo.setEndHm("18:00");
		if(vo.getFctMstIdx().isEmpty()){
			//메이커스페이스
			vo.setRsvtPlcCd(CommonCodeUtil._002._003.getValue());
			vo.setFctMstIdx("14");
		}

		info = facilitiesService.selectInfo(vo.getFctMstIdx());
		timeList = facilitiesService.selectTimeList(vo.getFctMstIdx());
		scheduleList = facilitiesService.selectScheduleList(vo.getFctMstIdx());

		// 시간목록
		Map<String, String> param1 = new HashMap<>();
		param1.put("term", info.getRsvtUnit());
		timeLineList = calendarService.selectTimeList(param1);

		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
		model.addAttribute("timeLineList", timeLineList);

		//시설 예약 목록
		if("20".equals(vo.getFctMstIdx())){
			//클랙스

			model.addAttribute("mainTitle",mainTitle);
			model.addAttribute("searchVO", vo);
			model.addAttribute("facilitiesList",facilitiesList);

			if(request.getRequestURI().indexOf("Excel") > 0){

				List<MakerspaceClassReserveMVO> reserveList = reserveService.selectMkspClassScheduleListExcel(vo);

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		        response.setHeader("Content-Disposition", "ATTachment; Filename=MkspClassScheduleList"+sdf.format(date)+".xls");
		        response.setHeader("Content-Description", "JSP Generated Data");

		        model.addAttribute("reserveList",reserveList);
		        return "empty/" + request.getRequestURI().replace(".do", "").substring(1);
			}else{

				List<MakerspaceClassReserveMVO> reserveList = reserveService.selectMkspClassScheduleList(vo);

				model.addAttribute("paginationInfo",vo.getPageInfo());
				model.addAttribute("reserveList",reserveList);
				return "reserve/facilities/scheduleMakerSpaceList";
			}

		}else{
			//장비
			vo.setSrtDt(param.getSrtDt());
			List<CalenderFacilitiesReserveMVO> reserveList = reserveService.selectCalenderReserveStateList(vo);

			model.addAttribute("mainTitle",mainTitle);
			model.addAttribute("info",info);
			model.addAttribute("reserveList",reserveList);
			model.addAttribute("calendarList",calendarList);
			model.addAttribute("timeList", timeList);
			model.addAttribute("scheduleList",scheduleList);
			model.addAttribute("searchVO", vo);
			model.addAttribute("facilitiesList",facilitiesList);

			return "reserve/facilities/scheduleMakerSpace";
		}
	}

	/**
	 * 메이커스페이스 클래스 상세 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value="/scheduleMakerSpaceClassInfo")
	public void selectMakerSpaceClassReserveInfo(ModelMap model, ReqMakerspaceClassVO vo) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//클래스 예약현황 상세
		List<MakerspaceClassReserveInfoMVO> mkspReserveList = reserveService.selectMakerSpaceClassReserveInfo(vo);

		//시설목록
		FacilitiesMasterDTO dto = new FacilitiesMasterDTO();
		dto.setRsvtPlcCd(CommonCodeUtil._002._003.getValue());
		List<FacilitiesMasterDTO> facilitiesList = facilitiesService.selectFacilitiesList(dto);

		model.addAttribute("searchVO", vo);
		model.addAttribute("mkspReserveList",mkspReserveList);
		model.addAttribute("facilitiesList",facilitiesList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);
	}

	/**
	 * 예약현황 : 메이커스페이스 클래스 상세 명단 화면
	 * @param vo
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value={"/scheduleMakerSpaceClassMbrList","scheduleMakerSpaceClassMbrListExcel"})
	public String selectMakerSpaceClassMbrList(ModelMap model, ReqMakerspaceClassVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String resultCode = ResponseUtil.RESULT_CODE_SUCESS;

		//클래스명 목록
		List<MakerspaceClassReserveInfoMVO> classList = reserveService.selectMakerSpaceClassReserveInfo(vo);

		//클래스 예약현황 상세 명단
		List<MakerspaceClassReserveInfoMVO> mkspClsMbrList = reserveService.selectMakerSpaceClassMbrList(vo);

		String jsp = request.getRequestURI().replace(".do", "").substring(1);

		if(request.getRequestURI().indexOf("Excel") > 0){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        response.setHeader("Content-Disposition", "ATTachment; Filename=MakerSpaceClassMbrList_"+sdf.format(date)+".xls");
	        response.setHeader("Content-Description", "JSP Generated Data");
	        jsp = "empty/" + jsp;
		}

		model.addAttribute("searchVO", vo);
		model.addAttribute("classList",classList);
		model.addAttribute("mkspClsMbrList",mkspClsMbrList);
		model.addAttribute(ResponseUtil.RESULT_CODE_NAME, resultCode);

		return jsp;
	}
}
