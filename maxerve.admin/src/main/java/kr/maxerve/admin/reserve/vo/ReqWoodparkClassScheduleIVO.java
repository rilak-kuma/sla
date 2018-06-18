package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.WoodparkClassScheduleDTO;

/**
* ReqWoodparkClassScheduleIVO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 우드파크 클래스 일정등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class ReqWoodparkClassScheduleIVO extends ReqFacilitiesMasterVO {
	@NotEmpty
	private String fctMstIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '시설 인덱스'
	@NotEmpty
	private String year = "";	// 년도
	@NotEmpty
	private String month = "";	// 월
	@NotEmpty
	List<WoodparkClassScheduleDTO> woodparkClassScheduleList = new ArrayList<>();

	public List<WoodparkClassScheduleDTO> getWoodparkClassScheduleList() {
		return woodparkClassScheduleList;
	}

	public void setWoodparkClassScheduleList(List<WoodparkClassScheduleDTO> woodparkClassScheduleList) {
		this.woodparkClassScheduleList = woodparkClassScheduleList;
	}

	public String getFctMstIdx() {
		return fctMstIdx;
	}

	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
