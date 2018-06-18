package kr.maxerve.admin.adjust.vo;

import java.util.List;

import kr.maxerve.dto.AdjustOrganizationDTO;

/**
* ReqAdjustOrganizationIVO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산단체등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
public class ReqAdjustOrganizationIVO {
	private List<AdjustOrganizationDTO> list;

	public List<AdjustOrganizationDTO> getList() {
		return list;
	}

	public void setList(List<AdjustOrganizationDTO> list) {
		this.list = list;
	}
}
