package kr.maxerve.admin.reserve.service;

import java.util.List;

import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.reserve.vo.FacilitiesMemberMVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberInfoVO;
import kr.maxerve.admin.reserve.vo.ReqFacilitiesMemberVO;

public interface FacilitiesMemberStateService {

	List<FacilitiesMemberMVO> selectFacilitiesMemberStateList(ReqFacilitiesMemberVO vo) throws Exception;

	List<FacilitiesMemberMVO> selectFacilitiesMemberStateListExcel(ReqFacilitiesMemberVO vo) throws Exception;

	List<MemberMVO> selectFacilitiesMemberList(ReqFacilitiesMemberInfoVO vo) throws Exception;

	List<MemberMVO> selectFacilitiesMemberListExcel(ReqFacilitiesMemberInfoVO vo) throws Exception;

}
