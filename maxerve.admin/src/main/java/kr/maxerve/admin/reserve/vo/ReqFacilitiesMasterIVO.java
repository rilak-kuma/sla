package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import kr.maxerve.dto.FacilitiesMasterDTO;
import kr.maxerve.dto.FacilitiesScheduleDTO;

/**
* ReqFacilitiesMasterIVO
* @author LEEC.J
* @since 2018.06.01
* @version 1.0
* @see
*
* <pre>
* 시설예약등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.01     LEEC.J        최초 생성
* </pre>
*/
public class ReqFacilitiesMasterIVO extends FacilitiesMasterDTO {
	private List<FacilitiesScheduleDTO> facilitiesScheduleList = new ArrayList<>(); // 운영일정
	private List<String> equipList = new ArrayList<>();

	public List<FacilitiesScheduleDTO> getFacilitiesScheduleList() {
		return facilitiesScheduleList;
	}

	public void setFacilitiesScheduleList(List<FacilitiesScheduleDTO> facilitiesScheduleList) {
		this.facilitiesScheduleList = facilitiesScheduleList;
	}

	public List<String> getEquipList() {
		return equipList;
	}

	public void setEquipList(List<String> equipList) {
		this.equipList = equipList;
	}
}
