package kr.maxerve.dto;

/**
* AdjustHistoryDTO
* @author LEEC.J
* @since 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 정산내역
* TBL_AJM_HSTR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
\* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class AdjustHistoryDTO {
	private String ajmYm = "";			// VARCHAR(6) NOT NULL COMMENT '정산년월',
	private String locTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '위치공통코드(010)',
	private String locIdx = "";			// INT(11) NOT NULL COMMENT '위치 인덱스',
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

	public String getAjmYm() {
		return ajmYm;
	}
	public void setAjmYm(String ajmYm) {
		this.ajmYm = ajmYm;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocIdx() {
		return locIdx;
	}
	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
}
