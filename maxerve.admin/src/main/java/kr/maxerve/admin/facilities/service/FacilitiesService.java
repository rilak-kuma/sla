package kr.maxerve.admin.facilities.service;

import java.util.List;

import kr.maxerve.admin.facilities.vo.FloorRoomMVO;
import kr.maxerve.admin.facilities.vo.MakerspaceClassMVO;
import kr.maxerve.admin.reserve.vo.ReqEquipmentMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassIVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaIVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassIVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassScheduleIVO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.FacilitiesFloorDTO;
import kr.maxerve.dto.FacilitiesInfoDTO;
import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.FacilitiesScheduleDTO;
import kr.maxerve.dto.MakerspaceClassCurriculumDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.MakerspaceClassGroupDTO;
import kr.maxerve.dto.RentTheAreaDTO;
import kr.maxerve.dto.WoodparkClassDTO;
import kr.maxerve.dto.WoodparkClassScheduleDTO;

public interface FacilitiesService {
	public enum SortType
	{
		DATE_ASC("1"),
		DATE_DESC("2"),
		STATUS_ASC("3"),
		STATUS_DESC("4");

		private final String value;

		private SortType(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	/**
	 * 시설목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectFacilitiesList(FacilitiesMasterDTO vo) throws Exception;

	/**
	 * 시설목록 확장형
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectFacilitiesExtraList(ReqFacilitiesMasterVO vo) throws Exception;

	/**
	 * 시설정보
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public FacilitiesMasterDTO selectInfo(String fctMstIdx) throws Exception;

	/**
	 * 시설 시간목록
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesScheduleDTO> selectTimeList(String fctMstIdx) throws Exception;

	/**
	 * 시설정보등록
	 * @param vo
	 * @throws Exception
	 */
	public void insert(ReqFacilitiesMasterIVO vo) throws Exception;

	/**
	 * 일정목록
	 * @param fctMstIdx
	 * @throws Exception
	 */
	public List<FacilitiesScheduleDTO> selectScheduleList(String fctMstIdx) throws Exception;

	/**
	 * 메이커스페이스 클래스 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassDTO> selectMakerspaceClassList(ReqMakerspaceClassVO vo) throws Exception;

	/**
	 * 메이커스페이스 클래스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMakerspaceClass(ReqMakerspaceClassIVO vo) throws Exception;

	/**
	 * 메이커스페이스 클래스 정보
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public MakerspaceClassMVO selectMakerspaceClassInfo(String mkspClsIdx) throws Exception;

	/**
	 * 메이커스페이스 반 시간목록
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassGroupDTO> selectMakerspaceClassGroupTimeList(String mkspClsIdx) throws Exception;

	/**
	 * 메이커스페이스 클래스 교육과정 목록
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassCurriculumDTO> selectMakerspaceClassCurriculumList(String mkspClsIdx) throws Exception;

	/**
	 * 메이커스페이스 삭제
	 * @param mkspClsIdx
	 * @throws Exception
	 */
	public void deleteMakerspaceClass(String mkspClsIdx) throws Exception;

	/**
	 * 우드파크 클래스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertWoodparkClass(ReqWoodparkClassIVO vo) throws Exception;

	/**
	 * 우드파크 클래스 목록
	 * @return
	 * @throws Exception
	 */
	public List<WoodparkClassDTO> selectWoodparkClassList() throws Exception;

	/**
	 * 우드파크 클래스 일정등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertWoodparkClassSchedule(ReqWoodparkClassScheduleIVO vo) throws Exception;

	/**
	 * 우드파크 클래스 일정목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<WoodparkClassScheduleDTO> selectWoodparkClassScheduleList(ReqFacilitiesMasterVO vo) throws Exception;

	/**
	 * 대관목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<RentTheAreaDTO> selectRentAreaList(ReqRentAreaVO vo) throws Exception;

	/**
	 * 대관정보
	 * @param rtaidx
	 * @return
	 * @throws Exception
	 */
	public RentTheAreaDTO selectRentAreaInfo(String rtaIdx) throws Exception;

	/**
	 * 대관등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertRentArea(ReqRentAreaIVO vo) throws Exception;

	/**
	 * 장비 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertEquip(ReqEquipmentMasterIVO vo) throws Exception;

	/**
	 * 장비 목록
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectEquipList() throws Exception;

	/**
	 * 시설관련장비목록
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectFacilitiesEquipList(String fctMstIdx) throws Exception;

	/**
	 * 대관장소목록
	 * @param rtaIdx
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectRentAreaFacilitiesList(String rtaIdx) throws Exception;

	public List<FacilitiesInfoDTO> selectFloorList(FacilitiesInfoDTO vo) throws Exception;

	public FacilitiesFloorDTO selectFloorInfo(FacilitiesInfoDTO vo) throws Exception;

	public void updateFloorRoomList(FloorRoomMVO vo) throws Exception;
}
