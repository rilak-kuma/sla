package kr.maxerve.admin.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.member.vo.MemberChannelMVO;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.member.vo.MoveInOztnMVO;
import kr.maxerve.admin.member.vo.ReqMemberVO;
import kr.maxerve.admin.member.vo.ReqMoveInOztnVO;
import kr.maxerve.dto.MoveInApplyCtgrDTO;
import kr.maxerve.dto.MoveInApplyDTO;

@Repository("memberDAO")
public class MemberDAO extends BaseDAOSupport {
	public List<MoveInOztnMVO> selectMoveinList(ReqMoveInOztnVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMoveinList", vo);
	}

	public List<MoveInOztnMVO> selectExpirationList(ReqMoveInOztnVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectExpirationList", vo);
	}

	public List<MoveInOztnMVO> selectMoveInOztnList(ReqMoveInOztnVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMoveInOztnList", vo);
	}

	public int selectMoveInOztnListCnt(ReqMoveInOztnVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.member.memberDAO.selectMoveInOztnListCnt", vo);
	}

	public List<MoveInOztnMVO> selectMoveInOztnListExcel(ReqMoveInOztnVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMoveInOztnListExcel", vo);
	}

	public List<MemberMVO> selectmoveInOztnPopUpList(ReqMoveInOztnVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectmoveInOztnPopUpList", vo);
	}
	
	public MoveInOztnMVO selectMoveInOztnInfo(ReqMoveInOztnVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.member.memberDAO.selectMoveInOztnInfo", vo);
	}
	
	public void updateMoveInOztnInfo(MoveInApplyDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.member.memberDAO.updateMoveInOztnInfo", vo);
	}

	public List<MoveInApplyCtgrDTO> selectMoveInApplyCtgrList(String mvinAplyIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMoveInApplyCtgrList", mvinAplyIdx);
	}

	public void insertMoveInApplyCtgr(MoveInApplyCtgrDTO dto) {
		getSqlSession().insert("kr.maxerve.admin.member.memberDAO.insertMoveInApplyCtgr", dto);
	}

	public void deleteMoveInApplyCtgr(MoveInApplyCtgrDTO dto) {
		getSqlSession().delete("kr.maxerve.admin.member.memberDAO.deleteMoveInApplyCtgr", dto);
	}

	public List<MoveInOztnMVO> selectNewMoveInAplyList(ReqMoveInOztnVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectNewMoveInAplyList", vo);
	}

	public int selectNewMoveInAplyListCnt(ReqMoveInOztnVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.member.memberDAO.selectNewMoveInAplyListCnt", vo);
	}
	
	public void updateNewMoveInAply(MoveInApplyDTO vo) {
		getSqlSession().update("kr.maxerve.admin.member.memberDAO.updateNewMoveInAply", vo);
	}

	public void updateNewMoveInOztnInfo(MoveInApplyDTO vo) {
		getSqlSession().update("kr.maxerve.admin.member.memberDAO.updateNewMoveInOztnInfo", vo);
	}

	public List<MemberMVO> selectOnlineMemberList(ReqMemberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectOnlineMemberList", vo);
	}
	
	public int selectOnlineMemberListCnt(ReqMemberVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.member.memberDAO.selectOnlineMemberListCnt", vo);
	}
	
	public MemberMVO selectOnlineMemberInfo(ReqMemberVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.member.memberDAO.selectOnlineMemberInfo", vo);
	}

	public List<MemberMVO> selectMblCtgrList(String mbrIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMblCtgrList", mbrIdx);
	}
	
	public List<MemberChannelMVO> selectMblCnlList(String mbrIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectMblCnlList", mbrIdx);
	}

	public List<MemberMVO> selectOnlineMemberState() {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectOnlineMemberState");
	}

	public List<MemberMVO> selectOnlineMvinMbState(ReqMemberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.member.memberDAO.selectOnlineMvinMbState", vo);
	}
}
