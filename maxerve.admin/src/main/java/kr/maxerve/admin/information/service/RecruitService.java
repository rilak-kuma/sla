package kr.maxerve.admin.information.service;

import java.util.List;

import kr.maxerve.admin.information.vo.RecruitMVO;
import kr.maxerve.admin.information.vo.ReqRecruitVO;
import kr.maxerve.dto.RecruitRoomDTO;

public interface RecruitService {
	
	List<RecruitMVO> selectRecruitlist(ReqRecruitVO vo) throws Exception;

	RecruitMVO selectRecruitInfo(String rcrtRoomIdx) throws Exception;

	void updateRecruit(RecruitRoomDTO vo) throws Exception;
}
