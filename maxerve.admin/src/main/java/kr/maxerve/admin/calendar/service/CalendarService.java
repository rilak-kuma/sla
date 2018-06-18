package kr.maxerve.admin.calendar.service;

import java.util.List;
import java.util.Map;

import kr.maxerve.admin.calendar.vo.CalendarVO;
import kr.maxerve.dto.CalendarDTO;
import kr.maxerve.dto.TimeDTO;

/**
* CalendarService
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
public interface CalendarService {
	/**
	 * 달력목록
	 * @param clndDt
	 * @return
	 * @throws Exception
	 */
	public List<CalendarDTO> selectList(CalendarVO vo) throws Exception;

	/**
	 * 시간목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<TimeDTO> selectTimeList(Map<String, String> vo) throws Exception;
}
