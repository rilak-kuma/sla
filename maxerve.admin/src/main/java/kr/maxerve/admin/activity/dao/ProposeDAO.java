package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.activity.vo.ProposeMVO;
import kr.maxerve.admin.activity.vo.ReqProposeVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.ProposeDTO;

@Repository("proposeDAO")
public class ProposeDAO extends BaseDAOSupport{

	public List<ProposeMVO> selecProposeList(ReqProposeVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.proposeDAO.selecProposeList",vo);
	}

	public int selecProposeListCnt(ReqProposeVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.proposeDAO.selecProposeListCnt",vo);
	}

	public ProposeMVO selectProposeInfo(String prpIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.proposeDAO.selectProposeInfo",prpIdx);
	}

	public void updatePropose(ProposeDTO vo) {
		getSqlSession().update("kr.maxerve.admin.activity.proposeDAO.updatePropose",vo);
	}

	public int selecProposeTotalCnt() {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.proposeDAO.selecProposeTotalCnt");
	}
}
