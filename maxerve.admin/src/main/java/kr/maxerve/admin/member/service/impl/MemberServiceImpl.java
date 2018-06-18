package kr.maxerve.admin.member.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.member.dao.MemberDAO;
import kr.maxerve.admin.member.service.MemberService;
import kr.maxerve.admin.member.vo.MemberChannelMVO;
import kr.maxerve.admin.member.vo.MemberMVO;
import kr.maxerve.admin.member.vo.MoveInOztnMVO;
import kr.maxerve.admin.member.vo.MoveInOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyOztnUVO;
import kr.maxerve.admin.member.vo.NewMoveInApplyUVO;
import kr.maxerve.admin.member.vo.ReqMemberVO;
import kr.maxerve.admin.member.vo.ReqMoveInOztnVO;
import kr.maxerve.dto.MoveInApplyCtgrDTO;
import kr.maxerve.dto.MoveInApplyDTO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="memberDAO")
	private MemberDAO memberDAO;


	@Resource(name="propertiesService")
	private Properties propertiesService;

	@Override
	public List<MoveInOztnMVO> selectMoveinList(ReqMoveInOztnVO vo) throws Exception {
		return memberDAO.selectMoveinList(vo);
	}

	@Override
	public List<MoveInOztnMVO> selectExpirationList(ReqMoveInOztnVO vo) throws Exception {
		return memberDAO.selectExpirationList(vo);
	}

	@Override
	public List<MoveInOztnMVO> selectMoveInOztnList(ReqMoveInOztnVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(memberDAO.selectMoveInOztnListCnt(vo));										// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return memberDAO.selectMoveInOztnList(vo);
	}


	@Override
	public List<MoveInOztnMVO> selectMoveInOztnListExcel(ReqMoveInOztnVO vo) throws Exception {
		return memberDAO.selectMoveInOztnListExcel(vo);
	}

	@Override
	public List<MemberMVO> selectmoveInOztnPopUpList(ReqMoveInOztnVO vo) throws Exception {
		return memberDAO.selectmoveInOztnPopUpList(vo);
	}

	@Override
	public MoveInOztnMVO selectMoveInOztnInfo(ReqMoveInOztnVO vo) throws Exception {
		return memberDAO.selectMoveInOztnInfo(vo);
	}

	@Override
	public void updateMoveInOztnInfo(MoveInOztnUVO vo, MoveInApplyCtgrDTO ctgrdto) throws Exception {

		//입주단체 업종 등록을 위해 기존 업종 삭제
		memberDAO.deleteMoveInApplyCtgr(ctgrdto);

		//입주단체 업종 등록
		StringTokenizer ctgrIdxs = new StringTokenizer(ctgrdto.getCtgrIdx(), ",");

        while(ctgrIdxs.hasMoreElements()){
        	String ctgrIdx = ctgrIdxs.nextToken();

        	ctgrdto.setCtgrIdx(ctgrIdx);
			memberDAO.insertMoveInApplyCtgr(ctgrdto);
        }
        MoveInApplyDTO dto = vo;
		memberDAO.updateMoveInOztnInfo(dto);
	}

	@Override
	public List<MoveInApplyCtgrDTO> selectMoveInApplyCtgrList(String mvinAplyIdx) {
		return memberDAO.selectMoveInApplyCtgrList(mvinAplyIdx);
	}

	@Override
	public void insertMoveInApplyCtgr(MoveInApplyCtgrDTO dto) throws Exception {
		memberDAO.insertMoveInApplyCtgr(dto);
	}

	@Override
	public List<MoveInOztnMVO> selectNewMoveInAplyList(ReqMoveInOztnVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(memberDAO.selectNewMoveInAplyListCnt(vo));										// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return memberDAO.selectNewMoveInAplyList(vo);
	}

	@Override
	public void updateNewMoveInAply(NewMoveInApplyUVO vo) throws Exception {
		MoveInApplyDTO dto = vo;
		memberDAO.updateNewMoveInAply(dto);
	}

	@Override
	public void updateNewMoveInOztnInfo(NewMoveInApplyOztnUVO vo, MoveInApplyCtgrDTO ctgrdto) throws Exception {

		//입주단체 업종 등록을 위해 기존 업종 삭제
		memberDAO.deleteMoveInApplyCtgr(ctgrdto);

		//입주단체 업종 등록
		StringTokenizer ctgrIdxs = new StringTokenizer(ctgrdto.getCtgrIdx(), ",");

        while(ctgrIdxs.hasMoreElements()){
        	String ctgrIdx = ctgrIdxs.nextToken();

        	ctgrdto.setCtgrIdx(ctgrIdx);
			memberDAO.insertMoveInApplyCtgr(ctgrdto);
        }

		memberDAO.updateNewMoveInOztnInfo(vo);
	}

	@Override
	public List<MemberMVO> selectOnlineMemberList(ReqMemberVO vo) throws Exception {
		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(memberDAO.selectOnlineMemberListCnt(vo));										// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return memberDAO.selectOnlineMemberList(vo);
	}

	@Override
	public MemberMVO selectOnlineMemberInfo(ReqMemberVO vo) throws Exception {

		List<MemberMVO> mblCtgrList = memberDAO.selectMblCtgrList(vo.getMbrIdx());
		String MbrCtgrCdNm = "";
		if(mblCtgrList.size() > NumberUtils.INTEGER_ZERO){
			for(int i = 0; i < mblCtgrList.size() ; i++){
				if(i == 0){
					MbrCtgrCdNm += mblCtgrList.get(i).getMbrCtgrCdNm();
				}else{
					MbrCtgrCdNm += ", "+mblCtgrList.get(i).getMbrCtgrCdNm();
				}
			}
		}

		MemberMVO memberVO = memberDAO.selectOnlineMemberInfo(vo);
		memberVO.setMbrCtgrCdNm(MbrCtgrCdNm);

		return memberVO;
	}

	@Override
	public List<MemberChannelMVO> selectMblCnlList(String mbrIdx) throws Exception {
		return memberDAO.selectMblCnlList(mbrIdx);
	}

	@Override
	public List<MemberMVO> selectOnlineMemberState() throws Exception {
		return memberDAO.selectOnlineMemberState();
	}

	@Override
	public List<MemberMVO> selectOnlineMvinMbState(ReqMemberVO vo) throws Exception {
		return memberDAO.selectOnlineMvinMbState(vo);
	}
}
