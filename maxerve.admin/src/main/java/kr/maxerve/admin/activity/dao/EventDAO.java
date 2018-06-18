package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.activity.vo.EventMVO;
import kr.maxerve.admin.activity.vo.ReqEventVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.EventDTO;

@Repository("eventDAO")
public class EventDAO extends BaseDAOSupport{

	public List<EventMVO> selectEventList(ReqEventVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.eventDAO.selectEventList",vo);
	}

	public int selectEventListCnt(ReqEventVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.eventDAO.selectEventListCnt",vo);
	}

	public EventMVO selectEventInfo(String evtIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.eventDAO.selectEventInfo",evtIdx);
	}

	public void updateEvent(EventDTO vo) {
		getSqlSession().update("kr.maxerve.admin.activity.eventDAO.updateEvent",vo);
	}

	public int selectEventTotalCnt() {
		return getSqlSession().selectOne("kr.maxerve.admin.activity.eventDAO.selectEventTotalCnt");
	}

}
