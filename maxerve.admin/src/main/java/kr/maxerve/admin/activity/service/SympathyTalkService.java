package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.admin.activity.vo.ReqSympathyTalkReferenceVO;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkVO;
import kr.maxerve.admin.activity.vo.SympathyTalkIVO;
import kr.maxerve.admin.activity.vo.SympathyTalkMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkReferenceMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkUVO;
import kr.maxerve.dto.SympathyTalkDTO;

public interface SympathyTalkService {

	void insertFsw(SympathyTalkIVO vo) throws Exception;

	List<SympathyTalkMVO> selectFswList(ReqSympathyTalkVO vo) throws Exception;

	List<SympathyTalkMVO> selectUseFswList() throws Exception;

	SympathyTalkMVO selectFswInfo(String fswTalkIdx) throws Exception;

	void updateFsw(SympathyTalkUVO vo) throws Exception;

	int selectMaxOrd() throws Exception;

	void deletetFsw(SympathyTalkDTO vo) throws Exception;

	int selectSympathyTalkTotalCnt(ReqSympathyTalkVO vo) throws Exception;

	List<SympathyTalkReferenceMVO> selectSymTalkReferenceList(ReqSympathyTalkReferenceVO vo) throws Exception;

	List<SympathyTalkReferenceMVO> selectUseSymTalkReferenceList(String fswTalkIdx) throws Exception;
}
