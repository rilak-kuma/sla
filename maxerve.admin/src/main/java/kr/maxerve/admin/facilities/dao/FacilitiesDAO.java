package kr.maxerve.admin.facilities.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.facilities.vo.MakerspaceClassMVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaVO;
import kr.maxerve.dto.EquipmentMasterDTO;
import kr.maxerve.dto.FacilitiesEquipmentDTO;
import kr.maxerve.dto.FacilitiesFloorDTO;
import kr.maxerve.dto.FacilitiesInfoDTO;
import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.FacilitiesScheduleDTO;
import kr.maxerve.dto.MakerspaceClassCurriculumDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.MakerspaceClassGroupDTO;
import kr.maxerve.dto.RentTheAreaDTO;
import kr.maxerve.dto.RentTheAreaFacilitiesDTO;
import kr.maxerve.dto.WoodparkClassDTO;
import kr.maxerve.dto.WoodparkClassScheduleDTO;

/**
* FacilitiesDAO
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
@Repository("facilitiesDAO")
public class FacilitiesDAO extends BaseDAOSupport {
	/**
	 * 시설목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectFacilitiesList(FacilitiesMasterDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectFacilitiesList", vo);
	}

	/**
	 * 시설수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(ReqFacilitiesMasterVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectCount", vo);
	}

	/**
	 * 시설목록 확장형
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectExtraList(ReqFacilitiesMasterVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectExtraList", vo);
	}

	/**
	 * 시설정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public FacilitiesMasterDTO selectInfo(String fctMstIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectInfo", fctMstIdx);
	}

	/**
	 * 시설 시간정보
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesScheduleDTO> selectTimeList(String fctMstIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectTimeList", fctMstIdx);
	}

	/**
	 * 시설정보등록
	 * @param vo
	 * @throws Exception
	 */
	public void insert(ReqFacilitiesMasterIVO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insert", vo);
	}

	/**
	 * 시설스케쥴정리
	 * @param fctMstIdx
	 * @throws Exception
	 */
	public void clearSchedule(String fctMstIdx) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.facilities.facilitiesDAO.clearSchedule", fctMstIdx);
	}

	/**
	 * 시설스케쥴등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertSchedule(FacilitiesScheduleDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertSchedule", vo);
	}

	/**
	 * 일정목록
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesScheduleDTO> selectScheduleList(String fctMstIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectScheduleList", fctMstIdx);
	}

	/**
	 * 메이커스페이스 클래스 총수
	 * @return
	 * @throws Exception
	 */
	public int selectMakerspaceClassCount() throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectMakerspaceClassCount");
	}

	/**
	 * 메이커스페이스 클래스 목록
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassDTO> selectMakerspaceClassList(ReqMakerspaceClassVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectMakerspaceClassList", vo);
	}

	/**
	 * 메이커스페이스 클래스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMakerspaceClass(MakerspaceClassDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertMakerspaceClass", vo);
	}

	/**
	 * 메이커스페이스 커리큘럼 삭제
	 * @param mkspClsIdx
	 * @throws Exception
	 */
	public void deleteMakerspaceClassCurriculum(String mkspClsIdx) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.facilities.facilitiesDAO.deleteMakerspaceClassCurriculum", mkspClsIdx);
	}

	/**
	 * 메이커스페이스 커리큘럼 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMakerspaceClassCurriculum(MakerspaceClassCurriculumDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertMakerspaceClassCurriculum", vo);
	}

	/**
	 * 메이커스페이스 클래스 반 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertMakerspaceClassGroup(MakerspaceClassGroupDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertMakerspaceClassGroup", vo);
	}

	/**
	 * 메이커스페이스 클래스 정보
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public MakerspaceClassMVO selectMakerspaceClassInfo(String mkspClsIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectMakerspaceClassInfo", mkspClsIdx);
	}

	/**
	 * 메이커스페이스 클래스 반 시간목록
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassGroupDTO> selectMakerspaceClassGroupTimeList(String mkspClsIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectMakerspaceClassGroupTimeList", mkspClsIdx);
	}

	/**
	 * 메이커스페이스 클래스 교육과정 목록
	 * @param mkspClsIdx
	 * @return
	 * @throws Exception
	 */
	public List<MakerspaceClassCurriculumDTO> selectMakerspaceClassCurriculumList(String mkspClsIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectMakerspaceClassCurriculumList", mkspClsIdx);
	}

	/**
	 * 메이커스페이스 클래스 삭제
	 * @param mkspClsIdx
	 * @throws Exception
	 */
	public void deleteMakerspaceClass(String mkspClsIdx) throws Exception {
		getSqlSession().update("kr.maxerve.admin.facilities.facilitiesDAO.deleteMakerspaceClass", mkspClsIdx);
	}

	/**
	 * 우드파크 클래스 삭제
	 * @throws Exception
	 */
	public void deleteWoodparkClass() throws Exception {
		getSqlSession().update("kr.maxerve.admin.facilities.facilitiesDAO.deleteWoodparkClass");
	}

	/**
	 * 우드파크 클래스 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertWoodparkClass(WoodparkClassDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertWoodparkClass", vo);
	}

	/**
	 * 우드파크 클래스 목록
	 * @return
	 * @throws Exception
	 */
	public List<WoodparkClassDTO> selectWoodparkClassList() throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectWoodparkClassList");
	}

	/**
	 * 우드파크 클래스 일정삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteWoodparkClassSchedule(ReqFacilitiesMasterVO vo) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.facilities.facilitiesDAO.deleteWoodparkClassSchedule", vo);
	}

	/**
	 * 우드파크 클래스 일정등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertWoodparkClassSchedule(WoodparkClassScheduleDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertWoodparkClassSchedule", vo);
	}

	/**
	 * 우드파크 클래스 일정목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<WoodparkClassScheduleDTO> selectWoodparkClassScheduleList(ReqFacilitiesMasterVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectWoodparkClassScheduleList", vo);
	}

	/**
	 * 대관목록수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectRentAreaCount(ReqRentAreaVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectRentAreaCount", vo);
	}

	/**
	 * 대관목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<RentTheAreaDTO> selectRentAreaList(ReqRentAreaVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectRentAreaList", vo);
	}

	/**
	 * 대관등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertRentArea(RentTheAreaDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertRentArea", vo);
	}

	/**
	 * 대관정보
	 * @param rtaIdx
	 * @return
	 * @throws Exception
	 */
	public RentTheAreaDTO selectRentAreaInfo(String rtaIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectRentAreaInfo", rtaIdx);
	}

	/**
	 * 장비 삭제
	 * @throws Exception
	 */
	public void deleteEquip() throws Exception {
		getSqlSession().update("kr.maxerve.admin.facilities.facilitiesDAO.deleteEquip");
	}

	/**
	 * 장비 등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertEquip(EquipmentMasterDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertEquip", vo);
	}

	/**
	 * 장비 목록
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectEquipList() throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectEquipList");
	}

	/**
	 * 시설관련 장비삭제
	 * @param fctMstIdx
	 * @throws Exception
	 */
	public void deleteFacilitiesEquip(String fctMstIdx) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.facilities.facilitiesDAO.deleteFacilitiesEquip", fctMstIdx);
	}

	/**
	 * 시설관련 장비등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertFacilitiesEquip(FacilitiesEquipmentDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertFacilitiesEquip", vo);
	}

	/**
	 * 시설관련장비목록
	 * @param fctMstIdx
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentMasterDTO> selectFacilitiesEquipList(String fctMstIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectFacilitiesEquipList", fctMstIdx);
	}

	/**
	 * 대관장소목록
	 * @param rtaIdx
	 * @return
	 * @throws Exception
	 */
	public List<FacilitiesMasterDTO> selectRentAreaFacilitiesList(String rtaIdx) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectRentAreaFacilitiesList", rtaIdx);
	}

	/**
	 * 대관장소삭제
	 * @param rtaIdx
	 * @throws Exception
	 */
	public void deleteRentAreaFacilities(String rtaIdx) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.facilities.facilitiesDAO.deleteRentAreaFacilities", rtaIdx);
	}

	/**
	 * 대관장소등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertRentAreaFacilities(RentTheAreaFacilitiesDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.facilities.facilitiesDAO.insertRentAreaFacilities", vo);
	}

	/**
	 * 미래청 전체 층정보 목록
	 * @param vo
	 * @throws Exception
	 */
	public List<FacilitiesInfoDTO> selectFloorList(FacilitiesInfoDTO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.facilities.facilitiesDAO.selectFloorList", vo);
	}

	/**
	 * 미래청 전체 층정보 상세
	 * @param vo
	 * @throws Exception
	 */
	public FacilitiesFloorDTO selectFloorInfo(FacilitiesInfoDTO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.facilities.facilitiesDAO.selectFloorInfo", vo);
	}

	/**
	 * 미래청 호실 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateFloorRoomList(FacilitiesInfoDTO vo) {
		getSqlSession().update("kr.maxerve.admin.facilities.facilitiesDAO.updateFloorRoomList", vo);
	}

	/**
	 * 미래청 전체 층별안내 수정
	 * @param vo
	 * @throws Exception
	 */
	public void updateFloorInfo(FacilitiesFloorDTO vo) {
		getSqlSession().update("kr.maxerve.admin.facilities.facilitiesDAO.updateFloorInfo", vo);
	}
}
