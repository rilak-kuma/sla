package kr.maxerve.admin.facilities.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.facilities.dao.FacilitiesDAO;
import kr.maxerve.admin.facilities.service.FacilitiesService;
import kr.maxerve.admin.facilities.vo.FloorRoomMVO;
import kr.maxerve.admin.facilities.vo.MakerspaceClassMVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.reserve.vo.ReqEquipmentMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterIVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassIVO;
import kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaIVO;
import kr.maxerve.admin.reserve.vo.ReqRentAreaVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassIVO;
import kr.maxerve.admin.reserve.vo.ReqWoodparkClassScheduleIVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
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
* FacilitiesServiceImpl
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
@Service("facilitiesService")
public class FacilitiesServiceImpl implements FacilitiesService {

	@Resource(name="facilitiesDAO")
	FacilitiesDAO facilitiesDAO;

	@Value("#{propertiesService['pagination.pagePerBlock']  }")
	private String pagePerBlock;

	@Value("#{propertiesService['pagination.countPerPage'] }")
	private String countPerPage;

	@Resource(name="fileService")
	FileService fileService;
	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectFacilitiesList(kr.maxerve.dto.FacilitiesMasterDTO)
	 */
	@Override
	public List<FacilitiesMasterDTO> selectFacilitiesList(FacilitiesMasterDTO vo) throws Exception {
		return facilitiesDAO.selectFacilitiesList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectInfo(java.lang.String)
	 */
	@Override
	public FacilitiesMasterDTO selectInfo(String fctMstIdx) throws Exception {
		return facilitiesDAO.selectInfo(fctMstIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#insert(kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterIVO)
	 */
	@Override
	public void insert(ReqFacilitiesMasterIVO vo) throws Exception {
		facilitiesDAO.insert(vo);

		// 요일정보
		facilitiesDAO.clearSchedule(vo.getFctMstIdx());
		for (FacilitiesScheduleDTO scheduleInfo : vo.getFacilitiesScheduleList()) {
			FacilitiesScheduleDTO param = new FacilitiesScheduleDTO();
			param.setFctMstIdx(vo.getFctMstIdx());
			param.setFctDow(scheduleInfo.getFctDow());
			param.setSrtTm(scheduleInfo.getSrtTm());
			param.setEndTm(scheduleInfo.getEndTm());

			facilitiesDAO.insertSchedule(param);
		}

		// 관련시설 삭제
		facilitiesDAO.deleteFacilitiesEquip(vo.getFctMstIdx());

		// 관련시설등록
		for (String eqpMstIdx : vo.getEquipList()) {
			FacilitiesEquipmentDTO param = new FacilitiesEquipmentDTO();

			param.setEqpMstIdx(eqpMstIdx);
			param.setFctMstIdx(vo.getFctMstIdx());
			facilitiesDAO.insertFacilitiesEquip(param);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectScheduleList(java.lang.String)
	 */
	@Override
	public List<FacilitiesScheduleDTO> selectScheduleList(String fctMstIdx) throws Exception {
		return facilitiesDAO.selectScheduleList(fctMstIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectMakerspaceClassList(kr.maxerve.admin.reserve.vo.ReqMakerspaceClassVO)
	 */
	@Override
	public List<MakerspaceClassDTO> selectMakerspaceClassList(ReqMakerspaceClassVO vo) throws Exception {
		int totalCount = facilitiesDAO.selectMakerspaceClassCount();

		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPage(), 1));
		pageInfo.setPageSize(NumberUtils.toInt(pagePerBlock));
		pageInfo.setRecordCountPerPage(NumberUtils.toInt(vo.getCountPerPage(), NumberUtils.toInt(countPerPage)));
		pageInfo.setTotalRecordCount(totalCount);

		vo.setPageInfo(pageInfo);

		return facilitiesDAO.selectMakerspaceClassList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#insertMakerspaceClass(kr.maxerve.admin.reserve.vo.ReqMakerspaceClassIVO)
	 */
	@Override
	public void insertMakerspaceClass(ReqMakerspaceClassIVO vo) throws Exception {
		// 클래스 등록
		facilitiesDAO.insertMakerspaceClass(vo);

		// 그룹등록
		for (MakerspaceClassGroupDTO groupInfo : vo.getGroupList()) {
			groupInfo.setMkspClsIdx(vo.getMkspClsIdx());

			facilitiesDAO.insertMakerspaceClassGroup(groupInfo);
		}

		// 커리큘럼 삭제
		facilitiesDAO.deleteMakerspaceClassCurriculum(vo.getMkspClsIdx());

		// 커리큘럼 등록
		for (MakerspaceClassCurriculumDTO curriculumInfo : vo.getCurriculumList()) {
			curriculumInfo.setMkspClsIdx(vo.getMkspClsIdx());

			facilitiesDAO.insertMakerspaceClassCurriculum(curriculumInfo);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectMakerspaceClassInfo(java.lang.String)
	 */
	@Override
	public MakerspaceClassMVO selectMakerspaceClassInfo(String mkspClsIdx) throws Exception {
		return facilitiesDAO.selectMakerspaceClassInfo(mkspClsIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectMakerspaceClassGroupTimeList(java.lang.String)
	 */
	@Override
	public List<MakerspaceClassGroupDTO> selectMakerspaceClassGroupTimeList(String mkspClsIdx) throws Exception {
		return facilitiesDAO.selectMakerspaceClassGroupTimeList(mkspClsIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectMakerspaceClassCurriculumList(java.lang.String)
	 */
	@Override
	public List<MakerspaceClassCurriculumDTO> selectMakerspaceClassCurriculumList(String mkspClsIdx) throws Exception {
		return facilitiesDAO.selectMakerspaceClassCurriculumList(mkspClsIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#deleteMakerspaceClass(java.lang.String)
	 */
	@Override
	public void deleteMakerspaceClass(String mkspClsIdx) throws Exception {
		facilitiesDAO.deleteMakerspaceClass(mkspClsIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectTimeList(java.lang.String)
	 */
	@Override
	public List<FacilitiesScheduleDTO> selectTimeList(String fctMstIdx) throws Exception {
		return facilitiesDAO.selectTimeList(fctMstIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectFacilitiesExtraList(kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO)
	 */
	@Override
	public List<FacilitiesMasterDTO> selectFacilitiesExtraList(ReqFacilitiesMasterVO vo) throws Exception {
		int totalCount = facilitiesDAO.selectCount(vo);

		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPage(), 1));
		pageInfo.setPageSize(NumberUtils.toInt(pagePerBlock));
		pageInfo.setRecordCountPerPage(NumberUtils.toInt(vo.getCountPerPage(), NumberUtils.toInt(countPerPage)));
		pageInfo.setTotalRecordCount(totalCount);

		vo.setPageInfo(pageInfo);

		return facilitiesDAO.selectExtraList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#insertWoodparkClass(kr.maxerve.admin.reserve.vo.ReqWoodparkClassIVO)
	 */
	@Override
	public void insertWoodparkClass(ReqWoodparkClassIVO vo) throws Exception {
		// 우드파크 클래스 삭제
		facilitiesDAO.deleteWoodparkClass();

		for (WoodparkClassDTO woodparkClassInfo : vo.getWoodparkClassList()) {
			facilitiesDAO.insertWoodparkClass(woodparkClassInfo);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectWoodparkClassList()
	 */
	@Override
	public List<WoodparkClassDTO> selectWoodparkClassList() throws Exception {
		return facilitiesDAO.selectWoodparkClassList();
	}

	@Override
	public void insertWoodparkClassSchedule(ReqWoodparkClassScheduleIVO vo) throws Exception {

		FacilitiesMasterDTO fmParam = new FacilitiesMasterDTO();
		fmParam.setRsvtPlcCd(CommonCodeUtil._002._004.getValue());
		fmParam.setUseYn("Y");
		List<FacilitiesMasterDTO> fmList = facilitiesDAO.selectFacilitiesList(fmParam);
		if(fmList != null && fmList.size() > 0) {
			vo.setFctMstIdx(fmList.get(0).getFctMstIdx());
		}

		// 일정삭제
		facilitiesDAO.deleteWoodparkClassSchedule(vo);

		// 일정등록
		for (WoodparkClassScheduleDTO scheduleInfo : vo.getWoodparkClassScheduleList()) {
			scheduleInfo.setFctMstIdx(vo.getFctMstIdx());

			facilitiesDAO.insertWoodparkClassSchedule(scheduleInfo);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectWoodparkClassScheduleList(kr.maxerve.admin.reserve.vo.ReqFacilitiesMasterVO)
	 */
	@Override
	public List<WoodparkClassScheduleDTO> selectWoodparkClassScheduleList(ReqFacilitiesMasterVO vo) throws Exception {
		return facilitiesDAO.selectWoodparkClassScheduleList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectRentAreaList(kr.maxerve.admin.reserve.vo.ReqRentAreaVO)
	 */
	@Override
	public List<RentTheAreaDTO> selectRentAreaList(ReqRentAreaVO vo) throws Exception {
		int totalCount = facilitiesDAO.selectRentAreaCount(vo);

		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPage(), 1));
		pageInfo.setPageSize(NumberUtils.toInt(pagePerBlock));
		pageInfo.setRecordCountPerPage(NumberUtils.toInt(vo.getCountPerPage(), NumberUtils.toInt(countPerPage)));
		pageInfo.setTotalRecordCount(totalCount);

		vo.setPageInfo(pageInfo);

		return facilitiesDAO.selectRentAreaList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectRentAreaInfo(java.lang.String)
	 */
	@Override
	public RentTheAreaDTO selectRentAreaInfo(String rtaIdx) throws Exception {
		return facilitiesDAO.selectRentAreaInfo(rtaIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#insertRentArea(kr.maxerve.admin.reserve.vo.ReqRentAreaIVO)
	 */
	@Override
	public void insertRentArea(ReqRentAreaIVO vo) throws Exception {
		if (StringUtils.isEmpty(vo.getRtaSrtDttm())) {
			vo.setRtaSrtDttm(vo.getRtaSrtDt() + " " + vo.getRtaSrtTm());
		}

		if (StringUtils.isEmpty(vo.getRtaEndDttm())) {
			vo.setRtaEndDttm(vo.getRtaEndDt() + " " + vo.getRtaEndTm());
		}

		facilitiesDAO.insertRentArea(vo);

		// 대관장소삭제
		facilitiesDAO.deleteRentAreaFacilities(vo.getRtaIdx());

		// 대관장소저장
		for (String fctMstIdx : vo.getRtaFctList()) {
			RentTheAreaFacilitiesDTO param = new RentTheAreaFacilitiesDTO();
			param.setFctMstIdx(fctMstIdx);
			param.setRtaIdx(vo.getRtaIdx());

			facilitiesDAO.insertRentAreaFacilities(param);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#insertEquip(kr.maxerve.admin.reserve.vo.ReqEquipmentMasterIVO)
	 */
	@Override
	public void insertEquip(ReqEquipmentMasterIVO vo) throws Exception {
		// 장비 삭제
		facilitiesDAO.deleteEquip();

		for (EquipmentMasterDTO equipInfo : vo.getEquipList()) {
			facilitiesDAO.insertEquip(equipInfo);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectEquipList()
	 */
	@Override
	public List<EquipmentMasterDTO> selectEquipList() throws Exception {
		return facilitiesDAO.selectEquipList();
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectFacilitiesEquipList(java.lang.String)
	 */
	@Override
	public List<EquipmentMasterDTO> selectFacilitiesEquipList(String fctMstIdx) throws Exception {
		return facilitiesDAO.selectFacilitiesEquipList(fctMstIdx);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.facilities.service.FacilitiesService#selectRentAreaFacilitiesList(java.lang.String)
	 */
	@Override
	public List<FacilitiesMasterDTO> selectRentAreaFacilitiesList(String rtaIdx) throws Exception {
		return facilitiesDAO.selectRentAreaFacilitiesList(rtaIdx);
	}

	@Override
	public List<FacilitiesInfoDTO> selectFloorList(FacilitiesInfoDTO vo) throws Exception {
		return facilitiesDAO.selectFloorList(vo);
	}

	@Override
	public FacilitiesFloorDTO selectFloorInfo(FacilitiesInfoDTO vo) throws Exception {
		return facilitiesDAO.selectFloorInfo(vo);
	}

	@Override
	public void updateFloorRoomList(FloorRoomMVO vo) throws Exception {
		System.out.println("=============="+vo.getRoomList().size()+"=====================");
		//층별 호실 정보 수정
		for(FacilitiesInfoDTO dto : vo.getRoomList()){
			if(StringUtils.isEmpty(dto.getLocIdx())){
				dto.setLocIdx("0");
			}
			System.out.println("dto.getRoomNm()" + dto.getRoomNm());
			facilitiesDAO.updateFloorRoomList(dto);
		}

		//층별안내 이미지등록(첨부파일처리)
		FacilitiesFloorDTO floorDTO = new FacilitiesFloorDTO();
		floorDTO.setFilePath(fileService.moveFile(vo.getFilePath()) );
		floorDTO.setFileInfo(vo.getFileInfo());
		floorDTO.setFlr(vo.getFlr());

		facilitiesDAO.updateFloorInfo(floorDTO);

	}
}
