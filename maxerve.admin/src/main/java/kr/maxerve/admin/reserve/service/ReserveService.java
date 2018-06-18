package kr.maxerve.admin.reserve.service;

import java.util.List;

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
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.TourAttendDTO;

public interface ReserveService {

	List<FacilitiesReserveMVO> selectReserveList(ReqFacilitiesReserveVO vo) throws Exception;

	FacilitiesReserveMVO selectReserveInfo(String fctRsvtIdx) throws Exception;

	void updateReserve(FacilitiesReserveUVO vo) throws Exception;

	List<ParkTourMVO> selectParktourReserveList(ReqParkTourVO vo) throws Exception;

	List<ParkTourMVO> selectParktourReserveListExcel(ReqParkTourVO vo) throws Exception;

	ParkTourMVO selectParktourReserveInfo(String tourAplyIdx) throws Exception;

	List<CalendarDTO> selectTourScheduleList(CalendarDTO dto) throws Exception;

	List<TourAttendDTO> selectTourAttendList(String tourAplyIdx) throws Exception;

	List<TourCarMVO> selectTourCarList(String tourAplyIdx) throws Exception;

	void updateParktourReserveInfo(ParkTourUVO vo) throws Exception;

	List<CalenderFacilitiesReserveMVO> selectCalenderReserveStateList(ReqFacilitiesMasterVO vo) throws Exception;

	List<MakerspaceClassReserveMVO> selectMkspClassScheduleList(ReqFacilitiesMasterVO vo) throws Exception;

	List<MakerspaceClassReserveMVO> selectMkspClassScheduleListExcel(ReqFacilitiesMasterVO vo) throws Exception;

	List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassReserveInfo(ReqMakerspaceClassVO vo) throws Exception;

	List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassMbrList(ReqMakerspaceClassVO vo) throws Exception;

	/**
	 * 예약시간카운트
	 * @param fctRsvtIdx
	 * @return
	 * @throws Exception
	 */
	public int selectMinuteCount(String fctRsvtIdx) throws Exception;

	/**
	 * 예약장비목록
	 * @param fctRsvtIdx
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectEquipList(String fctRsvtIdx) throws Exception;

}
