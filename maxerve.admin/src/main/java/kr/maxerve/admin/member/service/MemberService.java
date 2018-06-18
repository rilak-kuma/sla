package kr.maxerve.admin.member.service;

import java.util.List;

import kr.maxerve.admin.member.vo.MemberChannelMVO;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.member.vo.MoveInOztnMVO;
import kr.maxerve.admin.member.vo.MoveInOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyUVO;
import kr.maxerve.admin.member.vo.ReqMemberVO;
import kr.maxerve.admin.member.vo.ReqMoveInOztnVO;
import kr.maxerve.dto.MoveInApplyCtgrDTO;

public interface MemberService {
	public List<MoveInOztnMVO> selectMoveinList(ReqMoveInOztnVO vo) throws Exception;

	public List<MoveInOztnMVO> selectExpirationList(ReqMoveInOztnVO vo) throws Exception;

	public List<MoveInOztnMVO> selectMoveInOztnList(ReqMoveInOztnVO vo) throws Exception;
	
	public List<MoveInOztnMVO> selectMoveInOztnListExcel(ReqMoveInOztnVO vo) throws Exception;

	public List<MemberMVO> selectmoveInOztnPopUpList(ReqMoveInOztnVO vo) throws Exception;	

	public MoveInOztnMVO selectMoveInOztnInfo(ReqMoveInOztnVO vo)  throws Exception;
	
	public void updateMoveInOztnInfo(MoveInOztnUVO vo, MoveInApplyCtgrDTO dto) throws Exception;

	public List<MoveInApplyCtgrDTO> selectMoveInApplyCtgrList(String mvinAplyIdx) throws Exception;;

	public void insertMoveInApplyCtgr(MoveInApplyCtgrDTO dto) throws Exception;

	public List<MoveInOztnMVO> selectNewMoveInAplyList(ReqMoveInOztnVO vo) throws Exception;
	
	public void updateNewMoveInAply(NewMoveInApplyUVO vo) throws Exception;

	public void updateNewMoveInOztnInfo(NewMoveInApplyOztnUVO vo, MoveInApplyCtgrDTO dto) throws Exception;

	public List<MemberMVO> selectOnlineMemberList(ReqMemberVO vo) throws Exception;

	public MemberMVO selectOnlineMemberInfo(ReqMemberVO vo) throws Exception;

	public List<MemberChannelMVO> selectMblCnlList(String mbrIdx) throws Exception;

	public List<MemberMVO> selectOnlineMemberState() throws Exception;

	public List<MemberMVO> selectOnlineMvinMbState(ReqMemberVO vo) throws Exception;

}
