package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.activity.vo.ReferenceMVO;
import kr.maxerve.admin.activity.vo.ReqReferenceVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.ReferenceRoomDTO;

@Repository("referenceRoomDAO")
public class ReferenceRoomDAO extends BaseDAOSupport{

	public List<ReferenceMVO> selectReferenceList(ReqReferenceVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.referenceRoomDAO.selectReferenceList", vo);
	}
	
	public int selectReferenceListCnt(ReqReferenceVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.referenceRoomDAO.selectReferenceListCnt", vo);
	}
	
	public List<ReferenceMVO> selectCtgrRefCntList(CategoryDTO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.referenceRoomDAO.selectCtgrRefCntList", vo);
	}

	public ReferenceMVO selectReferenceInfo(ReqReferenceVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.referenceRoomDAO.selectReferenceInfo", vo);
	}

	public void updateReference(ReferenceRoomDTO vo) {
		getSqlSession().update("kr.maxerve.admin.activity.referenceRoomDAO.updateReference", vo);
	}

	public List<ReferenceMVO> selectReferenceListExcel(ReqReferenceVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.referenceRoomDAO.selectReferenceListExcel",vo);
	}
}
