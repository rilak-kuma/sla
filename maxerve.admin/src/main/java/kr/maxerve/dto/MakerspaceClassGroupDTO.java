package kr.maxerve.dto;

/**
* MakerspaceClassGroupDTO
* @author LEEC.J
* @since 2018.06.02
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스 반
* TBL_MKSP_CLS_GRP
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassGroupDTO {
	private String mkspClsGrpIdx = "";  		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '메이커스페이스 클래스 그룹 인덱스',
	private String mkspClsIdx = "";	    		// INT(11) NOT NULL COMMENT '메이커스페이스 클래스 인덱스',
	private String srtTm = "";					// VARCHAR(5) NOT NULL COMMENT '시작시분',
	private String endTm = "";					// VARCHAR(5) NOT NULL COMMENT '종료시분',
	private String grpNm = "";					// VARCHAR(50) NOT NULL COMMENT '그룹명',
	private String grpOrd = "";					// INT(11) NOT NULL COMMENT '반순번(종합반은 0)',
	private String prc = "";					// VARCHAR(10) NOT NULL COMMENT '가격',
	private String psct = "";					// VARCHAR(10) NOT NULL COMMENT '인원',
	private String formUrl = "";				// VARCHAR(200) NOT NULL COMMENT '신청서 URL',

	public String getMkspClsGrpIdx() {
		return mkspClsGrpIdx;
	}
	public void setMkspClsGrpIdx(String mkspClsGrpIdx) {
		this.mkspClsGrpIdx = mkspClsGrpIdx;
	}
	public String getMkspClsIdx() {
		return mkspClsIdx;
	}
	public void setMkspClsIdx(String mkspClsIdx) {
		this.mkspClsIdx = mkspClsIdx;
	}
	public String getSrtTm() {
		return srtTm;
	}
	public void setSrtTm(String srtTm) {
		this.srtTm = srtTm;
	}
	public String getEndTm() {
		return endTm;
	}
	public void setEndTm(String endTm) {
		this.endTm = endTm;
	}
	public String getGrpNm() {
		return grpNm;
	}
	public void setGrpNm(String grpNm) {
		this.grpNm = grpNm;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getPsct() {
		return psct;
	}
	public void setPsct(String psct) {
		this.psct = psct;
	}
	public String getGrpOrd() {
		return grpOrd;
	}
	public void setGrpOrd(String grpOrd) {
		this.grpOrd = grpOrd;
	}
	public String getFormUrl() {
		return formUrl;
	}
	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}
}
