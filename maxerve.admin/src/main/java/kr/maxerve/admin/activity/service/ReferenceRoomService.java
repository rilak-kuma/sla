package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.admin.activity.vo.ReferenceMVO;
import kr.maxerve.admin.activity.vo.ReqReferenceVO;
import kr.maxerve.dto.CategoryDTO;
import kr.maxerve.dto.ReferenceRoomDTO;

public interface ReferenceRoomService {

	List<ReferenceMVO> selectReferenceList(ReqReferenceVO vo) throws Exception;

	List<ReferenceMVO> selectCtgrRefCntList(CategoryDTO dto) throws Exception;

	ReferenceMVO selectReferenceInfo(ReqReferenceVO vo) throws Exception;

	void updateReference(ReferenceRoomDTO vo) throws Exception;

	List<ReferenceMVO> selectReferenceListExcel(ReqReferenceVO vo) throws Exception;
	
}
