package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.admin.activity.vo.ProposeMVO;
import kr.maxerve.admin.activity.vo.ReqProposeVO;
import kr.maxerve.dto.ProposeDTO;

public interface ProposeService {

	List<ProposeMVO> selecProposeList(ReqProposeVO vo) throws Exception;

	ProposeMVO selectProposeInfo(String prpIdx) throws Exception;

	void updatePropose(ProposeDTO vo) throws Exception;

	int selecProposeTotalCnt() throws Exception;

}
