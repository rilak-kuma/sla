package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.activity.vo.ProjectMVO;
import kr.maxerve.admin.activity.vo.ReqProjectVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.ProjectDTO;

@Repository("projectDAO")
public class ProjectDAO extends BaseDAOSupport {

	public List<ProjectMVO> selectProjectList(ReqProjectVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.projectDAO.selectProjectList",vo);
	}

	public int selectProjectListCnt(ReqProjectVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.projectDAO.selectProjectListCnt",vo);
	}
	
	public ProjectMVO selectProjectInfo(String pjtIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.projectDAO.selectProjectInfo",pjtIdx);
	}

	public void updateProject(ProjectDTO vo) {
		 getSqlSession().update("kr.maxerve.admin.activity.projectDAO.updateProject",vo);
	}

	public int selectProjectTotalCnt() {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.projectDAO.selectProjectTotalCnt");
	}
}
