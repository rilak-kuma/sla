package kr.maxerve.dto;

/**
* RentTheAreaDTO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 대관
* TBL_RTA
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class RentTheAreaDTO {
	private String rtaIdx = "";	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '대관 인덱스'
	private String titl = "";	// VARCHAR(50) NOT NULL COMMENT '제목',
	private String rtaSrtDttm = "";	// DATETIME NOT NULL COMMENT '대관 시작일시'
	private String rtaEndDttm = "";	// DATETIME NOT NULL COMMENT '대관 종료 일시'
	private String useYn = "";		// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '사용여부'
	private String delYn = "";		// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";	 	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'
	private String modDttm = "";	 	// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
	private String mbrId = "";
	
	public String getMbrId() {
		return mbrId;
	}
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	public String getRtaSrtDttm() {
		return rtaSrtDttm;
	}
	public void setRtaSrtDttm(String rtaSrtDttm) {
		this.rtaSrtDttm = rtaSrtDttm;
	}
	public String getRtaEndDttm() {
		return rtaEndDttm;
	}
	public void setRtaEndDttm(String rtaEndDttm) {
		this.rtaEndDttm = rtaEndDttm;
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
	public String getModDttm() {
		return modDttm;
	}
	public void setModDttm(String modDttm) {
		this.modDttm = modDttm;
	}
	public String getRtaIdx() {
		return rtaIdx;
	}
	public void setRtaIdx(String rtaIdx) {
		this.rtaIdx = rtaIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
}
