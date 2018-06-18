package kr.maxerve.admin.activity.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.activity.dao.ReferenceRoomDAO;
import kr.maxerve.admin.activity.service.ReferenceRoomService;
import kr.maxerve.admin.activity.vo.ReferenceMVO;
import kr.maxerve.admin.activity.vo.ReqReferenceVO;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.ReferenceRoomDTO;

@Service("referenceRoomService")
public class ReferenceRoomServiceImpl implements ReferenceRoomService{

	@Resource(name="referenceRoomDAO")
	ReferenceRoomDAO referenceRoomDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;
	
	@Override
	public List<ReferenceMVO> selectReferenceList(ReqReferenceVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(referenceRoomDAO.selectReferenceListCnt(vo));									// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return referenceRoomDAO.selectReferenceList(vo);
	}

	@Override
	public List<ReferenceMVO> selectCtgrRefCntList(CategoryDTO dto) throws Exception {
		return referenceRoomDAO.selectCtgrRefCntList(dto);
	}

	@Override
	public ReferenceMVO selectReferenceInfo(ReqReferenceVO vo) throws Exception {
		return referenceRoomDAO.selectReferenceInfo(vo);
	}

	@Override
	public void updateReference(ReferenceRoomDTO vo) throws Exception {
		referenceRoomDAO.updateReference(vo);
	}

	@Override
	public List<ReferenceMVO> selectReferenceListExcel(ReqReferenceVO vo) throws Exception {
		return referenceRoomDAO.selectReferenceListExcel(vo);
	}
}
