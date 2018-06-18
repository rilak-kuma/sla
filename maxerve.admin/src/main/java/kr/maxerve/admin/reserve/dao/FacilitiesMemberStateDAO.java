package kr.maxerve.admin.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesMemberMVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberInfoVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberVO;

@Repository("facilitiesMemberStateDAO")
public class FacilitiesMemberStateDAO extends BaseDAOSupport{

	public List<FacilitiesMemberMVO> selectFacilitiesMemberStateList(ReqFacilitiesMemberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.facilitiesMemberStateDAO.selectFacilitiesMemberStateList",vo);
	}

	public int selectFacilitiesMemberStateListCnt(ReqFacilitiesMemberVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.reserve.facilitiesMemberStateDAO.selectFacilitiesMemberStateListCnt",vo);
	}

	public List<FacilitiesMemberMVO> selectFacilitiesMemberStateListExcel(ReqFacilitiesMemberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.facilitiesMemberStateDAO.selectFacilitiesMemberStateListExcel",vo);
	}

	public List<MemberMVO> selectFacilitiesMemberList(ReqFacilitiesMemberInfoVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.facilitiesMemberStateDAO.selectFacilitiesMemberList",vo);
	}

	public List<MemberMVO> selectFacilitiesMemberListExcel(ReqFacilitiesMemberInfoVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.reserve.facilitiesMemberStateDAO.selectFacilitiesMemberListExcel",vo);
	}
}
