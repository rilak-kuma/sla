package kr.maxerve.admin.information.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.information.vo.RecruitMVO;
import kr.maxerve.admin.information.vo.ReqRecruitVO;
import kr.maxerve.dto.RecruitRoomDTO;

@Repository("recruitDAO")
public class RecruitDAO extends BaseDAOSupport{

	public List<RecruitMVO> selectRecruitlist(ReqRecruitVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.information.recruitDAO.selectRecruitlist", vo);
	}

	public int selectRecruitlistCnt(ReqRecruitVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.recruitDAO.selectRecruitlistCnt", vo);
	}

	public RecruitMVO selectRecruitInfo(String rcrtRoomIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.recruitDAO.selectRecruitInfo", rcrtRoomIdx);
	}

	public void updateRecruit(RecruitRoomDTO vo) {
		getSqlSession().update("kr.maxerve.admin.information.recruitDAO.updateRecruit", vo);
	}

}
