package kr.maxerve.admin.reserve.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.reserve.dao.FacilitiesMemberStateDAO;
import kr.maxerve.admin.reserve.service.FacilitiesMemberStateService;
import kr.maxerve.admin.reserve.vo.FacilitiesMemberMVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberInfoVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberVO;

@Service("facilitiesMemberStateService")
public class FacilitiesMemberStateServiceImpl implements FacilitiesMemberStateService{

	@Resource(name="facilitiesMemberStateDAO")
	FacilitiesMemberStateDAO facilitiesMemberStateDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;
	
	@Override
	public List<FacilitiesMemberMVO> selectFacilitiesMemberStateList(ReqFacilitiesMemberVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(facilitiesMemberStateDAO.selectFacilitiesMemberStateListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return facilitiesMemberStateDAO.selectFacilitiesMemberStateList(vo);
	}

	@Override
	public List<FacilitiesMemberMVO> selectFacilitiesMemberStateListExcel(ReqFacilitiesMemberVO vo) {
		return facilitiesMemberStateDAO.selectFacilitiesMemberStateListExcel(vo);
	}

	@Override
	public List<MemberMVO> selectFacilitiesMemberList(ReqFacilitiesMemberInfoVO vo) throws Exception {
		return facilitiesMemberStateDAO.selectFacilitiesMemberList(vo);
	}

	@Override
	public List<MemberMVO> selectFacilitiesMemberListExcel(ReqFacilitiesMemberInfoVO vo) throws Exception {
		return facilitiesMemberStateDAO.selectFacilitiesMemberListExcel(vo);
	}

}
