package kr.maxerve.dto;

/**
* MakerspaceClassDTO
* @author LEEC.J
* @since 2018.06.02
* @version 1.0
* @see
*
* <pre>
* 메이커스페이스 클래스
* TBL_MKSP_CLS
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.02     LEEC.J        최초 생성
* </pre>
*/
public class MakerspaceClassDTO {
	private String mkspClsIdx = "";	    		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '메이커스페이스 클래스 인덱스',
	private String titl = "";					// VARCHAR(50) NOT NULL COMMENT '제목',
	private String srtDt = "";					// VARCHAR(10) NOT NULL COMMENT '모집시작기간',
	private String endDt = "";					// VARCHAR(10) NOT NULL COMMENT '모집종료기간',
	private String oprTmInfo = "";				// VARCHAR(50) NOT NULL COMMENT '운영시간정보',
	private String rsvtMbrInfo = "";			// VARCHAR(50) NOT NULL COMMENT '예약가능회원정보',
	private String rcrtPsctInfo = "";			// VARCHAR(50) NOT NULL COMMENT '모집정원정보',
	private String prcInfo = "";				// VARCHAR(50) NOT NULL COMMENT '사용료정보',
	private String useYn = "";					// VARCHAR(1) NOT NULL COMMENT '사용여부',
	private String delYn = "";					// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
	private String creDttm = "";				// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',

	public String getMkspClsIdx() {
		return mkspClsIdx;
	}
	public void setMkspClsIdx(String mkspClsIdx) {
		this.mkspClsIdx = mkspClsIdx;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getOprTmInfo() {
		return oprTmInfo;
	}
	public void setOprTmInfo(String oprTmInfo) {
		this.oprTmInfo = oprTmInfo;
	}
	public String getRsvtMbrInfo() {
		return rsvtMbrInfo;
	}
	public void setRsvtMbrInfo(String rsvtMbrInfo) {
		this.rsvtMbrInfo = rsvtMbrInfo;
	}
	public String getRcrtPsctInfo() {
		return rcrtPsctInfo;
	}
	public void setRcrtPsctInfo(String rcrtPsctInfo) {
		this.rcrtPsctInfo = rcrtPsctInfo;
	}
	public String getPrcInfo() {
		return prcInfo;
	}
	public void setPrcInfo(String prcInfo) {
		this.prcInfo = prcInfo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
}
