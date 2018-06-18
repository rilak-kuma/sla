package kr.maxerve.dto;

/**
* RecruitRoomDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 구인
* TBL_RCRT_ROOM
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class RecruitRoomDTO {
	private String rcrtRoomIdx = "";		// int(11) NOT NULL AUTOINCREMENT COMMENT '구인 인덱스'
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '회원 인덱스'
	private String mngrMbrIdx = "";		// INT(11) NULL DEFAULT NULL COMMENT '수정자 인덱스',
	private String oztnNm = "";			// VARCHAR(50) NOT NULL COMMENT '단체명',
	private String titl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String fld = "";				// varchar(50) NOT NULL COMMENT '모집분야'
	private String psct = "";			// varchar(50) NOT NULL COMMENT '모집인원'
	private String body = "";			// varchar(50) NOT NULL COMMENT '모집정보'
	private String sal = "";				// varchar(50) NOT NULL COMMENT '급여정보'
	private String bnf = "";				// varchar(50) NOT NULL COMMENT '복리후생'
	private String appyEmilYn = "";		// varchar(1) NOT NULL COMMENT '지원방법 이메일 여부'
	private String appyVisYn = "";		// varchar(1) NOT NULL COMMENT '지원방법 방문여부'
	private String appyPostYn = "";		// varchar(1) NOT NULL COMMENT '지원방법 우편접수 여부'
	private String emil = "";			// varchar(100) NOT NULL COMMENT '이메일 주소'
	private String addr = "";			// varchar(300) NOT NULL COMMENT '업체주소'
	private String appyQstn = "";		// varchar(300) NOT NULL COMMENT '지원문의'
	private String prdSrtDt = "";		// varchar(10) NOT NULL COMMENT '접수 시작일'
	private String prdEndDt = "";		// varchar(10) NOT NULL COMMENT '접수 종료일'
	private String prdRcrtYn = "";		// varchar(10) NOT NULL COMMENT '충원시까지 여부'
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String hitCnt = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '등록일시'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getRcrtRoomIdx() {
		return rcrtRoomIdx;
	}
	public void setRcrtRoomIdx(String rcrtRoomIdx) {
		this.rcrtRoomIdx = rcrtRoomIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getFld() {
		return fld;
	}
	public void setFld(String fld) {
		this.fld = fld;
	}
	public String getPsct() {
		return psct;
	}
	public void setPsct(String psct) {
		this.psct = psct;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getBnf() {
		return bnf;
	}
	public void setBnf(String bnf) {
		this.bnf = bnf;
	}
	public String getAppyEmilYn() {
		return appyEmilYn;
	}
	public void setAppyEmilYn(String appyEmilYn) {
		this.appyEmilYn = appyEmilYn;
	}
	public String getAppyVisYn() {
		return appyVisYn;
	}
	public void setAppyVisYn(String appyVisYn) {
		this.appyVisYn = appyVisYn;
	}
	public String getAppyPostYn() {
		return appyPostYn;
	}
	public void setAppyPostYn(String appyPostYn) {
		this.appyPostYn = appyPostYn;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAppyQstn() {
		return appyQstn;
	}
	public void setAppyQstn(String appyQstn) {
		this.appyQstn = appyQstn;
	}
	public String getPrdSrtDt() {
		return prdSrtDt;
	}
	public void setPrdSrtDt(String prdSrtDt) {
		this.prdSrtDt = prdSrtDt;
	}
	public String getPrdEndDt() {
		return prdEndDt;
	}
	public void setPrdEndDt(String prdEndDt) {
		this.prdEndDt = prdEndDt;
	}
	public String getPrdRcrtYn() {
		return prdRcrtYn;
	}
	public void setPrdRcrtYn(String prdRcrtYn) {
		this.prdRcrtYn = prdRcrtYn;
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
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
}
