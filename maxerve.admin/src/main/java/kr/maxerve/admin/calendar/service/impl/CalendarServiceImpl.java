package kr.maxerve.admin.calendar.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.calendar.dao.CalendarDAO;
import kr.maxerve.admin.calendar.service.CalendarService;
import kr.maxerve.admin.calendar.vo.CalendarVO;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.TimeDTO;

/**
* CalendarServiceImpl
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 달력
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
@Service("calendarService")
public class CalendarServiceImpl implements CalendarService {
	@Resource(name="calendarDAO")
	private CalendarDAO calendarDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.calendar.service.CalendarService#selectList(kr.maxerve.admin.calendar.vo.CalendarVO)
	 */
	@Override
	public List<CalendarDTO> selectList(CalendarVO vo) throws Exception {
		return calendarDAO.selectList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.calendar.service.CalendarService#selectTimeList(java.util.Map)
	 */
	@Override
	public List<TimeDTO> selectTimeList(Map<String, String> vo) throws Exception {
		return calendarDAO.selectTimeList(vo);
	}

}
