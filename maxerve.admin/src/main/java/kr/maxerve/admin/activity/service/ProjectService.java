package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.admin.activity.vo.ProjectMVO;
import kr.maxerve.admin.activity.vo.ReqProjectVO;
import kr.maxerve.dto.ProjectDTO;

public interface ProjectService {

	List<ProjectMVO> selectProjectList(ReqProjectVO vo) throws Exception;

	ProjectMVO selectProjectInfo(String pjtIdx) throws Exception;

	void updateProject(ProjectDTO vo) throws Exception;

	int selectProjectTotalCnt() throws Exception;

}
