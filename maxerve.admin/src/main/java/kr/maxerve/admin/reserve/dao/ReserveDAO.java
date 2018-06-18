package kr.maxerve.admin.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.reserve.vo.CalenderFacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesReserveMVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveInfoMVO;
import kr.maxerve.admin.reserve.vo.MakerspaceClassReserveMVO;
import kr.maxerve.admin.reserve.vo.ParkTourMVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesReserveVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqParkTourVO;
import kr.maxerve.admin.reserve.vo.TourCarMVO;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.FacilitiesReserveDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.TourApplyDTO;
import kr.maxerve.dto.TourAttendDTO;


@Repository("reserveDAO")
public class ReserveDAO extends BaseDAOSupport{

	public List<FacilitiesReserveMVO> selectReserveList(ReqFacilitiesReserveVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectReserveList",vo);
	}

	public int selectReserveListCnt(ReqFacilitiesReserveVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectReserveListCnt",vo);
	}

	public FacilitiesReserveMVO selectReserveInfo(String fctRsvtIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectReserveInfo",fctRsvtIdx);
	}

	public void updateReserve(FacilitiesReserveDTO vo) {
		getSqlSession().update("kr.maxerve.admin.reserve.reserveDAO.updateReserve",vo);
	}

	public List<ParkTourMVO> selectParktourReserveList(ReqParkTourVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectParktourReserveList",vo);
	}

	public int selectParktourReserveListCnt(ReqParkTourVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectParktourReserveListCnt",vo);
	}

	public List<ParkTourMVO> selectParktourReserveListExcel(ReqParkTourVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectParktourReserveListExcel",vo);
	}

	public ParkTourMVO selectParktourReserveInfo(String tourAplyIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectParktourReserveInfo",tourAplyIdx);
	}

	public List<CalendarDTO> selectTourScheduleList(CalendarDTO dto) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectTourScheduleList", dto);
	}

	public List<TourAttendDTO> selectTourAttendList(String tourAplyIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectTourAttendList",tourAplyIdx);
	}

	public List<TourCarMVO> selectTourCarList(String tourAplyIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectTourCarList",tourAplyIdx);
	}

	public void updateParktourReserveInfo(TourApplyDTO vo) {
		getSqlSession().update("kr.maxerve.admin.reserve.reserveDAO.updateParktourReserveInfo",vo);
	}

	public List<CalenderFacilitiesReserveMVO> selectCalenderReserveStateList(ReqFacilitiesMasterVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectCalenderReserveStateList",vo);
	}

	public List<MakerspaceClassReserveMVO> selectMkspClassScheduleList(ReqFacilitiesMasterVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectMkspClassScheduleList",vo);
	}

	public int selectMkspClassScheduleListCnt(ReqFacilitiesMasterVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectMkspClassScheduleListCnt",vo);
	}

	public List<MakerspaceClassReserveMVO> selectMkspClassScheduleListExcel(ReqFacilitiesMasterVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectMkspClassScheduleListExcel",vo);
	}

	public List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassReserveInfo(MakerspaceClassDTO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectMakerSpaceClassReserveInfo",vo);
	}

	public List<MakerspaceClassReserveInfoMVO> selectMakerSpaceClassMbrList(ReqMakerspaceClassVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectMakerSpaceClassMbrList",vo);
	}

	/**
	 * 예약시간
	 * @param fctRsvtIdx
	 * @return
	 * @throws Exception
	 */
	public int selectMinuteCount(String fctRsvtIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.reserveDAO.selectMinuteCount",fctRsvtIdx);
	}

	/**
	 * 예약장비목록
	 * @param fctRsvtIdx
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectEquipList(String fctRsvtIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.reserveDAO.selectEquipList",fctRsvtIdx);
	}
}
