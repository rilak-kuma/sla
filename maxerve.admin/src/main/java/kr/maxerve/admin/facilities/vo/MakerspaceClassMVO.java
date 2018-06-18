package kr.maxerve.admin.facilities.vo;

import java.util.ArrayList;
import java.util.List;

import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.MakerspaceClassGroupDTO;

/**
* MakerspaceClassMVO
* @author LEEC.J
* @since 2018.06.03
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.03     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassMVO extends MakerspaceClassDTO {
	List<MakerspaceClassGroupDTO> groupList = new ArrayList<>(); // 반 목록

	public List<MakerspaceClassGroupDTO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<MakerspaceClassGroupDTO> groupList) {
		this.groupList = groupList;
	}
}
