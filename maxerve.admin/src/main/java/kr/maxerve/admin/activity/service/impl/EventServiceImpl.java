package kr.maxerve.admin.activity.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.activity.dao.EventDAO;
import kr.maxerve.admin.activity.service.EventService;
import kr.maxerve.admin.activity.vo.EventMVO;
import kr.maxerve.admin.activity.vo.ReqEventVO;
import kr.maxerve.dto.EventDTO;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Resource(name="eventDAO")
	EventDAO eventDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;
	
	@Override
	public List<EventMVO> selectEventList(ReqEventVO vo) throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(eventDAO.selectEventListCnt(vo));												// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return eventDAO.selectEventList(vo);
	}

	@Override
	public EventMVO selectEventInfo(String evtIdx) throws Exception {
		return eventDAO.selectEventInfo(evtIdx);
	}

	@Override
	public void updateEvent(EventDTO vo) throws Exception {
		eventDAO.updateEvent(vo);
	}

	@Override
	public int selectEventTotalCnt() throws Exception {
		return eventDAO.selectEventTotalCnt();
	}

}
