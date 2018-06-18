package kr.maxerve.dto;

/**
* FacilitiesInfoDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 호수별시설안내
* TBL_FCT_INFO
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesInfoDTO {
	private String roomNm = "";		// VARCHAR(50) NOT NULL COMMENT '호실',
	private String flr = "";			// INT(11) NOT NULL,
	private String fctTypCd = "";	// VARCHAR(50) NOT NULL COMMENT '시설구분 공통코드(031)',
	private String locIdx = "";		// VARCHAR(50) NOT NULL COMMENT '관계지 인덱스',
	private String fctNm = "";		// VARCHAR(50) NOT NULL COMMENT '시설명',
	private String fctCord = "";		// VARCHAR(200) NOT NULL COMMENT '시설 좌표',
	private String url = "";		// VARCHAR(200) NOT NULL COMMENT '연결정보',
	private String useYn = "";		// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getRoomNm() {
		return roomNm;
	}
	public void setRoomNm(String roomNm) {
		this.roomNm = roomNm;
	}
	public String getFlr() {
		return flr;
	}
	public void setFlr(String flr) {
		this.flr = flr;
	}
	public String getFctTypCd() {
		return fctTypCd;
	}
	public void setFctTypCd(String fctTypCd) {
		this.fctTypCd = fctTypCd;
	}
	public String getLocIdx() {
		return locIdx;
	}
	public void setLocIdx(String locIdx) {
		this.locIdx = locIdx;
	}
	public String getFctCord() {
		return fctCord;
	}
	public void setFctCord(String fctCord) {
		this.fctCord = fctCord;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String getFctNm() {
		return fctNm;
	}
	public void setFctNm(String fctNm) {
		this.fctNm = fctNm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
