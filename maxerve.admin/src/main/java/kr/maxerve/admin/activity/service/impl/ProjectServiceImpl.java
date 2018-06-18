package kr.maxerve.admin.activity.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.activity.dao.ProjectDAO;
import kr.maxerve.admin.activity.service.ProjectService;
import kr.maxerve.admin.activity.vo.ProjectMVO;
import kr.maxerve.admin.activity.vo.ReqProjectVO;
import kr.maxerve.dto.ProjectDTO;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{

	@Resource(name="projectDAO")
	ProjectDAO projectDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;
	
	@Override
	public List<ProjectMVO> selectProjectList(ReqProjectVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(projectDAO.selectProjectListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return projectDAO.selectProjectList(vo);
	}

	@Override
	public ProjectMVO selectProjectInfo(String pjtIdx) throws Exception {
		return projectDAO.selectProjectInfo(pjtIdx);
	}

	@Override
	public void updateProject(ProjectDTO vo) throws Exception {
		projectDAO.updateProject(vo);
	}

	@Override
	public int selectProjectTotalCnt() throws Exception {
		return projectDAO.selectProjectTotalCnt();
	}

}
