package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.activity.vo.ReqSympathyTalkReferenceVO;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkVO;
import kr.maxerve.admin.activity.vo.SympathyTalkMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkReferenceMVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.SympathyTalkDTO;
import kr.maxerve.dto.SympathyTalkReferenceDTO;

@Repository("sympathyTalkDAO")
public class SympathyTalkDAO extends BaseDAOSupport{


	public void insertFsw(SympathyTalkDTO vo) {
		getSqlSession().insert("kr.maxerve.admin.activity.sympathyTalkDAO.insertFsw",vo);
	}

	public int selectFswListCnt(ReqSympathyTalkVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectFswListCnt",vo);
	}

	public List<SympathyTalkMVO> selectFswList(ReqSympathyTalkVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.sympathyTalkDAO.selectFswList",vo);
	}

	public List<SympathyTalkMVO> selectUseFswList() {
		return getSqlSession().selectList("kr.maxerve.admin.activity.sympathyTalkDAO.selectUseFswList");
	}

	public SympathyTalkMVO selectFswInfo(String fswTalkIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectFswInfo",fswTalkIdx);
	}

	public void updateFsw(SympathyTalkDTO vo) {
		getSqlSession().update("kr.maxerve.admin.activity.sympathyTalkDAO.updateFsw",vo);
	}

	public int selectMaxOrd() {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectMaxOrd");
	}

	public String selectFswTalkIdx(SympathyTalkDTO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectFswTalkIdx", vo);
	}

	public int selectSympathyTalkTotalCnt(ReqSympathyTalkVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectSympathyTalkTotalCnt", vo);
	}

	public List<SympathyTalkReferenceMVO> selectSymTalkReferenceList(ReqSympathyTalkReferenceVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.sympathyTalkDAO.selectSymTalkReferenceList",vo);
	}

	public int selectSymTalkReferenceListCnt(ReqSympathyTalkReferenceVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectSymTalkReferenceListCnt");
	}

	public void deleteSympathyTalkReference(String fswTalkIdx) {
		getSqlSession().update("kr.maxerve.admin.activity.sympathyTalkDAO.deleteSympathyTalkReference", fswTalkIdx);
	}

	public void insertSympathyTalkReference(SympathyTalkReferenceDTO vo) {
		getSqlSession().update("kr.maxerve.admin.activity.sympathyTalkDAO.insertSympathyTalkReference",vo);
	}

	public List<SympathyTalkReferenceMVO> selectUseSymTalkReferenceList(String fswTalkIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.sympathyTalkDAO.selectUseSymTalkReferenceList");
	}
}
