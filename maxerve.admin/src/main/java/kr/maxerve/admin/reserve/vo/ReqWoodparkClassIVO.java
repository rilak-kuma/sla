package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.WoodparkClassDTO;

/**
* ReqWoodparkClassIVO
* @author LEEC.J
* @since 2018.06.04
* @version 1.0
* @see
*
* <pre>
* 우드파크 클래스 등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.04     LEEC.J        최초 생성
* </pre>
*/
public class ReqWoodparkClassIVO {
	@NotEmpty
	List<WoodparkClassDTO> woodparkClassList = new ArrayList<>();

	public List<WoodparkClassDTO> getWoodparkClassList() {
		return woodparkClassList;
	}

	public void setWoodparkClassList(List<WoodparkClassDTO> woodparkClassList) {
		this.woodparkClassList = woodparkClassList;
	}
}
