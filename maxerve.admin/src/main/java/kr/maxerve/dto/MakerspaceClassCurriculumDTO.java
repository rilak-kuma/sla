package kr.maxerve.dto;

/**
* MakerspaceClassCurriculumDTO
* @author LEEC.J
* @since 2018.06.02
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스 교육과정
* TBL_FCT_MST
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.02     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassCurriculumDTO {
	private String mkspClsIdx = "";		// INT(11) NOT NULL COMMENT '메이커스페이스 클래스 인덱스',
	private String grpOrd = "";				// INT(11) NOT NULL COMMENT '반 순번',
	private String crclDt = "";				// VARCHAR(10) NOT NULL COMMENT '교육일자',
	private String crclNm = "";				// VARCHAR(50) NOT NULL COMMENT '교육명',

	public String getMkspClsIdx() {
		return mkspClsIdx;
	}
	public void setMkspClsIdx(String mkspClsIdx) {
		this.mkspClsIdx = mkspClsIdx;
	}
	public String getGrpOrd() {
		return grpOrd;
	}
	public void setGrpOrd(String grpOrd) {
		this.grpOrd = grpOrd;
	}
	public String getCrclDt() {
		return crclDt;
	}
	public void setCrclDt(String crclDt) {
		this.crclDt = crclDt;
	}
	public String getCrclNm() {
		return crclNm;
	}
	public void setCrclNm(String crclNm) {
		this.crclNm = crclNm;
	}
}
