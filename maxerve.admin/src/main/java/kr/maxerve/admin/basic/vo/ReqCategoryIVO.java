package kr.maxerve.admin.basic.vo;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.CategoryDTO;

/**
* ReqCategoryIVO
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 카테고리등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* </pre>
*/
public class ReqCategoryIVO extends CategoryDTO {
	@NotEmpty
	private String ctgrTypCd = "";	// VARCHAR(50) NOT NULL COMMENT '카테고리 종류 공통코드(009)'
	@NotEmpty
	private String ctgrNm = "";		// 카테고리명
	@NotEmpty
	private String ctgrImg = "";		// VARCHAR(200) NOT NULL COMMENT '카테고리 이미지'

	public String getCtgrTypCd() {
		return ctgrTypCd;
	}
	public void setCtgrTypCd(String ctgrTypCd) {
		this.ctgrTypCd = ctgrTypCd;
	}
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	public String getCtgrImg() {
		return ctgrImg;
	}
	public void setCtgrImg(String ctgrImg) {
		this.ctgrImg = ctgrImg;
	}
}
