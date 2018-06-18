package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.EquipmentMasterDTO;

/**
* ReqEquipmentMasterIVO
* @author LEEC.J
* @since 2018.06.04
* @version 1.0
* @see
*
* <pre>
* 장비등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.04     LEEC.J        최초 생성
* </pre>
*/
public class ReqEquipmentMasterIVO {
	@NotEmpty
	private List<EquipmentMasterDTO> equipList = new ArrayList<>(); // 장비목록

	public List<EquipmentMasterDTO> getEquipList() {
		return equipList;
	}

	public void setEquipList(List<EquipmentMasterDTO> equipList) {
		this.equipList = equipList;
	}
}
