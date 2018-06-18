package kr.maxerve.admin.basic.vo;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.SimpleResourceDTO;

/**
* ReqSimpleResourceIVO
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 단순리소스
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
public class ReqSimpleResourceIVO {
	@NotEmpty
	List<SimpleResourceDTO> list;

	public List<SimpleResourceDTO> getList() {
		return list;
	}

	public void setList(List<SimpleResourceDTO> list) {
		this.list = list;
	}
}
