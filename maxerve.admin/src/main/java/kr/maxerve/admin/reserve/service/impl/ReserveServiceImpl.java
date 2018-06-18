package kr.maxerve.admin.reserve.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.mail.service.MailService;
import kr.maxerve.admin.mail.vo.MailVO;
import kr.maxerve.admin.reserve.dao.ReserveDAO;
import kr.maxerve.admin.reserve.service.ReserveService;
import kr.maxerve.admin.reserve.vo.CalenderFacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveUVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveInfoMVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveMVO;
import kr.maxerve.admin.reserve.vo.ParkTourMVO;
import kr.maxerve.admin.reserve.vo.ParkTourUVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesReserveVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqParkTourVO;
import kr.maxerve.admin.reserve.vo.TourCarMVO;
import kr.maxerve.admin.sms.service.SmsService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.TourApplyDTO;
import kr.maxerve.dto.TourAttendDTO;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="reserveDAO")
	ReserveDAO reserveDAO;

	@Resource(name="propertiesService")
	Properties propertiesService;

	@Resource(name="mailService")
	private MailService mailService;

	@Resource(name="smsService")
	private SmsService smsService;

	@Value("#{propertiesService['mail.sender']}")
	private String mailSender;

	@Override
	public List<FacilitiesReserveMVO> selectReserveList(ReqFacilitiesReserveVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(reserveDAO.selectReserveListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return reserveDAO.selectReserveList(vo);
	}

	@Override
	public FacilitiesReserveMVO selectReserveInfo(String fctRsvtIdx) throws Exception {
		return reserveDAO.selectReserveInfo(fctRsvtIdx);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updateReserve(FacilitiesReserveUVO vo) throws Exception {

		reserveDAO.updateReserve(vo);

		if (vo.getRsvtPgr().equals(CommonCodeUtil._020._002.getValue())) {
			FacilitiesReserveMVO reserveInfo = this.selectReserveInfo(vo.getFctRsvtIdx());

			Map mailModel = new HashMap<>();
			mailModel.put("fctNm", reserveInfo.getFctNm());
			mailModel.put("dttm", reserveInfo.getSrtDttm().substring(0, 16).replace("-", ".") + " ~ " + reserveInfo.getEndDttm().substring(0, 16).replace("-", "."));

			// 시설예약 확정메일발송
			try {
				MailVO param1 = new MailVO();
				param1.setFrom(mailSender);
				param1.setTo(reserveInfo.getRsvtEmil());
				param1.setSubject("[서울혁신파크]결제안내: " + reserveInfo.getFctNm());
				param1.setTemplate("mail/reservation.vm");
				param1.setModel(mailModel);

				mailService.sendMail(param1);
			} catch (Exception e) {
				logger.error("survey email post error!!", e);
			}
		}
	}

	@Override
	public List<ParkTourMVO> selectParktourReserveList(ReqParkTourVO vo) {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(reserveDAO.selectParktourReserveListCnt(vo));									// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return reserveDAO.selectParktourReserveList(vo);
	}

	@Override
	public List<ParkTourMVO> selectParktourReserveListExcel(ReqParkTourVO vo) throws Exception {
		return reserveDAO.selectParktourReserveListExcel(vo);
	}

	@Override
	public ParkTourMVO selectParktourReserveInfo(String tourAplyIdx) throws Exception {
		return reserveDAO.selectParktourReserveInfo(tourAplyIdx);
	}

	@Override
	public List<CalendarDTO> selectTourScheduleList(CalendarDTO dto) throws Exception {
		return reserveDAO.selectTourScheduleList(dto);
	}

	@Override
	public List<TourAttendDTO> selectTourAttendList(String tourAplyIdx) throws Exception {
		return reserveDAO.selectTourAttendList(tourAplyIdx);
	}

	@Override
	public List<TourCarMVO> selectTourCarList(String tourAplyIdx) throws Exception {
		return reserveDAO.selectTourCarList(tourAplyIdx);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void updateParktourReserveInfo(ParkTourUVO vo) throws Exception {

		TourApplyDTO dto = vo;
		reserveDAO.updateParktourReserveInfo(dto);

		TourApplyDTO applyInfo = reserveDAO.selectParktourReserveInfo(vo.getTourAplyIdx());
		SimpleDateFormat sdformat = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		String tourDttm = applyInfo.getTourDttm();
		cal.set(Integer.parseInt(tourDttm.substring(0,4)),//2017
				 Integer.parseInt(tourDttm.substring(6,7)) -1,
				 Integer.parseInt(tourDttm.substring(8,10)),
				 Integer.parseInt(tourDttm.substring(11,13)),
				 Integer.parseInt(tourDttm.substring(14,16))
				);
		cal.add(Calendar.HOUR, 1);		  // 1시간 더하기

		if (vo.getTourPgr().equals(CommonCodeUtil._020._003.getValue())) {

			Map mailModel = new HashMap<>();
			mailModel.put("tourDsdDttm", applyInfo.getTourDsdDttm() + "~" + sdformat.format(cal.getTime()));
			mailModel.put("oztnNm", applyInfo.getOztnNm());
			mailModel.put("psct", applyInfo.getPsct());

			// 투어 완료메일발송
			MailVO param1 = new MailVO();
			param1.setFrom(mailSender);
			param1.setTo(applyInfo.getAppyEmil());
			param1.setSubject("[서울혁신파크]파크투어 신청이 확정되었습니다.");
			param1.setTemplate("mail/tour.vm");
			param1.setModel(mailModel);

			mailService.sendMail(param1);

			//SMS
			//smsService.sendReserve("024003", "020002", applyInfo.getAppyPhnNmbr());//024003	020002	신청하신 투어 예약이 승인되었습니다.

		}else if (vo.getTourPgr().equals(CommonCodeUtil._020._005.getValue())) {

			Map mailModel = new HashMap<>();
			mailModel.put("tourDsdDttm", applyInfo.getTourDsdDttm() + "~" + sdformat.format(cal.getTime()));
			mailModel.put("oztnNm", applyInfo.getOztnNm());
			mailModel.put("psct", applyInfo.getPsct());

			Map date = new HashMap<>();
			date.put("format","%Y.%m.%d %H:%i");
			date.put("min","0");

			mailModel.put("dttm", applyInfo.getCreDttm().substring(0,16));//접수일
			mailModel.put("period", applyInfo.getTourDsdDttm() + "~" + sdformat.format(cal.getTime()));
			mailModel.put("oztnNm", applyInfo.getOztnNm());//단체명
			mailModel.put("psct", applyInfo.getPsct());//투어인원
			mailModel.put("status", "신청취소");//상태

			// 투어 완료메일발송
				MailVO param1 = new MailVO();
				param1.setFrom(mailSender);
				param1.setTo(applyInfo.getAppyEmil());
				param1.setSubject("[서울혁신파크]파크투어 신청이 취소되었습니다.");
				param1.setTemplate("mail/park_tour.vm");
				param1.setModel(mailModel);

				mailService.sendMail(param1);

				//SMS
				smsService.sendReserve("024003", "020005", applyInfo.getAppyPhnNmbr());//024003	020005	신청하신 투어 예약이 취소되었습니다.
		}
	}

	@Override
	public List<CalenderFacilitiesReserveMVO> selectCalenderReserveStateList(ReqFacilitiesMasterVO vo) throws Exception {
		return reserveDAO.selectCalenderReserveStateList(vo);
	}

	@Override
	public List<MakerspaceClassReserveMVO> selectMkspClassScheduleList(ReqFacilitiesMasterVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPage(), NumberUtils.INTEGER_ONE));						// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(reserveDAO.selectMkspClassScheduleListCnt(vo));								// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return reserveDAO.selectMkspClassScheduleList(vo);
	}

	@Override
	public List<MakerspaceClassReserveMVO> selectMkspClassScheduleListExcel(ReqFacilitiesMasterVO vo) throws Exception {
		return reserveDAO.selectMkspClassScheduleListExcel(vo);
	}

	@Override
	public List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassReserveInfo(ReqMakerspaceClassVO vo) throws Exception {
		MakerspaceClassDTO dto = vo;
		return reserveDAO.selectMakerSpaceClassReserveInfo(dto);
	}

	@Override
	public List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassMbrList(ReqMakerspaceClassVO vo) throws Exception {
		return reserveDAO.selectMakerSpaceClassMbrList(vo);
	}

	/**
	 * @param fctRsvtIdx
	 * @return
	 * @throws Exception
	 */
	@Override
	public int selectMinuteCount(String fctRsvtIdx) throws Exception {
		return reserveDAO.selectMinuteCount(fctRsvtIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.reserve.service.ReserveService#selectEquipList(java.lang.String)
	 */
	@Override
	public List<EquipmentMasterDTO> selectEquipList(String fctRsvtIdx) throws Exception {
		return reserveDAO.selectEquipList(fctRsvtIdx);
	}

}
