package kr.maxerve.admin.basic.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.CategoryDTO;

/**
* ReqCategoryDVO
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 카테고리삭제
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
public class ReqCategoryDVO extends CategoryDTO {
	@NotEmpty
	private String ctgrIdx = "";		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '카테고리 인덱스'
	@NotEmpty
	private String ctgrTypCd = "";	// VARCHAR(50) NOT NULL COMMENT '카테고리 종류 공통코드(009)'

	public String getCtgrIdx() {
		return ctgrIdx;
	}

	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}

	public String getCtgrTypCd() {
		return ctgrTypCd;
	}

	public void setCtgrTypCd(String ctgrTypCd) {
		this.ctgrTypCd = ctgrTypCd;
	}
}
