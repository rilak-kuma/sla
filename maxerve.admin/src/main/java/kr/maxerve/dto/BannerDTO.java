package kr.maxerve.dto;

/**
* BannerDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 배너
* TBL_BNNR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class BannerDTO {
	
	private String bnnrIdx=""; 		//int(11) NOT NULL AUTO_INCREMENT COMMENT '베너 인덱스',
	private String plfCd=""; 			//varchar(50) NOT NULL COMMENT '플랫폼 공통코드(027)',
	private String bnnrLocCd=""; 		//varchar(50) NOT NULL COMMENT '베너소재지 공통코드(010)',
	private String bnnrRscLocCd=""; 	//varchar(50) NOT NULL COMMENT '배너등록지 공통코드(010)',
	private String bnnrRscLocIdx=""; 	//int(11) NOT NULL COMMENT '배너등록지 인덱스(이미지등록시 0)',
	private String titl=""; 			//varchar(50) NOT NULL COMMENT '제목',
	private String info=""; 			//varchar(200) NOT NULL COMMENT '정보',
	private String filePath=""; 		//varchar(200) NOT NULL COMMENT '배너파일',
	private String ord=""; 			//int(11) NOT NULL COMMENT '순서',
	private String link=""; 			//varchar(200) NOT NULL COMMENT '링크',
	private String newWndYn=""; 		//varchar(1) NOT NULL COMMENT '새창여부',
	private String useYn=""; 			//varchar(1) NOT NULL DEFAULT 'Y' COMMENT '전시여부',
	private String useSrtDt=""; 		//varchar(10) NOT NULL COMMENT '전시시작일',
	private String useEndDt=""; 		//varchar(10) NOT NULL COMMENT '전시종료일',
	private String useLmtYn=""; 		//varchar(1) NOT NULL COMMENT '기간설정 사용여부',
	private String delYn=""; 			//varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
	private String creDttm=""; 		//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm=""; 		//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
	
	public String getBnnrIdx() {
		return bnnrIdx;
	}
	public void setBnnrIdx(String bnnrIdx) {
		this.bnnrIdx = bnnrIdx;
	}
	public String getPlfCd() {
		return plfCd;
	}
	public void setPlfCd(String plfCd) {
		this.plfCd = plfCd;
	}
	public String getBnnrLocCd() {
		return bnnrLocCd;
	}
	public void setBnnrLocCd(String bnnrLocCd) {
		this.bnnrLocCd = bnnrLocCd;
	}
	public String getBnnrRscLocCd() {
		return bnnrRscLocCd;
	}
	public void setBnnrRscLocCd(String bnnrRscLocCd) {
		this.bnnrRscLocCd = bnnrRscLocCd;
	}
	public String getBnnrRscLocIdx() {
		return bnnrRscLocIdx;
	}
	public void setBnnrRscLocIdx(String bnnrRscLocIdx) {
		this.bnnrRscLocIdx = bnnrRscLocIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getNewWndYn() {
		return newWndYn;
	}
	public void setNewWndYn(String newWndYn) {
		this.newWndYn = newWndYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseSrtDt() {
		return useSrtDt;
	}
	public void setUseSrtDt(String useSrtDt) {
		this.useSrtDt = useSrtDt;
	}
	public String getUseEndDt() {
		return useEndDt;
	}
	public void setUseEndDt(String useEndDt) {
		this.useEndDt = useEndDt;
	}
	public String getUseLmtYn() {
		return useLmtYn;
	}
	public void setUseLmtYn(String useLmtYn) {
		this.useLmtYn = useLmtYn;
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
}
