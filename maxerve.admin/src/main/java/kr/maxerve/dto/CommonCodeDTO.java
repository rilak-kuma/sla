package kr.maxerve.dto;

/**
* CommonCodeDTO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class CommonCodeDTO {
	private String cmmnCd = "";			// varchar(50) NOT NULL COMMENT '공통코드'
	private String dep1 = "";			// varchar(50) NOT NULL COMMENT '뎁스1'
	private String dep2 = "";			// varchar(50) NOT NULL COMMENT '뎁스2'
	private String dep3 = "";			// varchar(50) NOT NULL COMMENT '뎁스3'
	private String cmmnCdNm = "";		// varchar(50) NOT NULL COMMENT '공통코드명'
	private String ord = "";			// TINYINT(4) NOT NULL DEFAULT '0' COMMENT '순서',
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	public String getCmmnCd() {
		return cmmnCd;
	}
	public void setCmmnCd(String cmmnCd) {
		this.cmmnCd = cmmnCd;
	}
	public String getDep1() {
		return dep1;
	}
	public void setDep1(String dep1) {
		this.dep1 = dep1;
	}
	public String getDep2() {
		return dep2;
	}
	public void setDep2(String dep2) {
		this.dep2 = dep2;
	}
	public String getDep3() {
		return dep3;
	}
	public void setDep3(String dep3) {
		this.dep3 = dep3;
	}
	public String getCmmnCdNm() {
		return cmmnCdNm;
	}
	public void setCmmnCdNm(String cmmnCdNm) {
		this.cmmnCdNm = cmmnCdNm;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
}
