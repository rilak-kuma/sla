package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.admin.activity.vo.EventMVO;
import kr.maxerve.admin.activity.vo.ReqEventVO;
import kr.maxerve.dto.EventDTO;

public interface EventService {

	List<EventMVO> selectEventList(ReqEventVO vo) throws Exception;

	EventMVO selectEventInfo(String evtIdx) throws Exception;

	void updateEvent(EventDTO vo) throws Exception;

	int selectEventTotalCnt() throws Exception;

}
