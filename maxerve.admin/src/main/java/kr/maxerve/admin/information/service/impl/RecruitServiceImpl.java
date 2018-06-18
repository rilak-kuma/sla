package kr.maxerve.admin.information.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.information.dao.RecruitDAO;
import kr.maxerve.admin.information.service.RecruitService;
import kr.maxerve.admin.information.vo.RecruitMVO;
import kr.maxerve.admin.information.vo.ReqRecruitVO;
import kr.maxerve.dto.RecruitRoomDTO;

@Service("recruitService")
public class RecruitServiceImpl implements RecruitService {

	@Resource(name="recruitDAO")
	private RecruitDAO recruitDAO;
	
	@Resource(name="propertiesService")
	private Properties propertiesService;
	
	@Override
	public List<RecruitMVO> selectRecruitlist(ReqRecruitVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(recruitDAO.selectRecruitlistCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return recruitDAO.selectRecruitlist(vo);
	}

	@Override
	public RecruitMVO selectRecruitInfo(String rcrtRoomIdx) throws Exception {
		return recruitDAO.selectRecruitInfo(rcrtRoomIdx);
	}

	@Override
	public void updateRecruit(RecruitRoomDTO vo) throws Exception {
		recruitDAO.updateRecruit(vo);
	}
}
