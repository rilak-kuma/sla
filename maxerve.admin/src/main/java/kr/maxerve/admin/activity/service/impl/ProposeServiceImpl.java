package kr.maxerve.admin.activity.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.activity.dao.ProposeDAO;
import kr.maxerve.admin.activity.service.ProposeService;
import kr.maxerve.admin.activity.vo.ProposeMVO;
import kr.maxerve.admin.activity.vo.ReqProposeVO;
import kr.maxerve.dto.ProposeDTO;

@Service("proposeService")
public class ProposeServiceImpl implements ProposeService {

	@Resource(name="proposeDAO")
	ProposeDAO proposeDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;
	
	@Override
	public List<ProposeMVO> selecProposeList(ReqProposeVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(proposeDAO.selecProposeListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return proposeDAO.selecProposeList(vo);
	}

	@Override
	public ProposeMVO selectProposeInfo(String prpIdx) throws Exception {
		return proposeDAO.selectProposeInfo(prpIdx);
	}

	@Override
	public void updatePropose(ProposeDTO vo) throws Exception {
		proposeDAO.updatePropose(vo);
	}

	@Override
	public int selecProposeTotalCnt() throws Exception {
		return proposeDAO.selecProposeTotalCnt();
	}

}
