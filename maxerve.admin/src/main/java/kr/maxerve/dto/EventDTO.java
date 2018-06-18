package kr.maxerve.dto;

/**
* EventDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 이벤트/행사
* TBL_EVT
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class EventDTO {
	private String evtIdx = "";			// int(11) NOT NULL AUTOINCREMENT COMMENT '행사 인덱스'
	private String mbrIdx = "";			// int(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String mngrMbrIdx = "";		// INT(11) NOT NULL DEFAULT '0' COMMENT '수정자 인덱스',
	private String ctgrIdx = "";			// INT(11) NOT NULL COMMENT '카테고리 인덱스(009003)',
	private String evtTitl = "";			// varchar(50) NOT NULL COMMENT '제목'
	private String tmnPath = "";			// varchar(200) NOT NULL COMMENT '섬네일'
	private String evtSrtDttm = "";		// datetime NOT NULL COMMENT '이벤트 시작 일시'
	private String evtEndDttm = "";		// datetime NOT NULL COMMENT '이벤트 종료 일시'
	private String aplySrtDttm = "";		// datetime DEFAULT NULL COMMENT '신청 시작 일시'
	private String aplyEndDttm = "";		// datetime DEFAULT NULL COMMENT '신청 종료 일시'
	private String aplyFreeYn = "";			// VARCHAR(1) NOT NULL COMMENT '이벤트무신청 여부',
	private String evtPlc = "";			// varchar(50) DEFAULT NULL COMMENT '이벤트 장소'
	private String psct = "";			// VARCHAR(10) NOT NULL COMMENT '모집인원',
	private String psctFreeYn = "";		// VARCHAR(1) NOT NULL COMMENT '모집인원 무제한 여부',
	private String evtInfo = "";			// varchar(1000) NOT NULL COMMENT '이벤트 정보'
	private String body = "";			// mediumtext NOT NULL COMMENT '본문'
	private String payFreeYn = "";		// varchar(1) NOT NULL COMMENT '무료여부'
	private String locTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '위치정보 공통코드(016)',
	private String locLat = "";			// VARCHAR(20) NOT NULL COMMENT '위도',
	private String locLng = "";			// VARCHAR(20) NOT NULL COMMENT '경도',
	private String locImgPath = "";	// VARCHAR(200) NOT NULL COMMENT '위치정보 이미지',
	private String locImgInfo = "";	// VARCHAR(100) NOT NULL COMMENT '위치정보 상세안내',
	private String payPgYn = "";			// varchar(1) NOT NULL COMMENT '혁신파크 결제여부'
	private String bankCd = "";			// varchar(3) NOT NULL COMMENT '은행 코드'
	private String bankAcnb = "";		// varchar(50) NOT NULL COMMENT '계좌번호'
	private String bankAchd = "";		// varchar(50) NOT NULL COMMENT '예금주'
	private String evtPrc = "";			// varchar(10) noT NULL COMMENT '참가비용'
	private String hitCnt = "";			// INT(11) NOT NULL DEFAULT '0' COMMENT '조회수',
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부',
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '등록일시'
	private String modDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'

	public String getEvtIdx() {
		return evtIdx;
	}
	public void setEvtIdx(String evtIdx) {
		this.evtIdx = evtIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getEvtTitl() {
		return evtTitl;
	}
	public void setEvtTitl(String evtTitl) {
		this.evtTitl = evtTitl;
	}
	public String getTmnPath() {
		return tmnPath;
	}
	public void setTmnPath(String tmnPath) {
		this.tmnPath = tmnPath;
	}
	public String getEvtSrtDttm() {
		return evtSrtDttm;
	}
	public void setEvtSrtDttm(String evtSrtDttm) {
		this.evtSrtDttm = evtSrtDttm;
	}
	public String getEvtEndDttm() {
		return evtEndDttm;
	}
	public void setEvtEndDttm(String evtEndDttm) {
		this.evtEndDttm = evtEndDttm;
	}
	public String getAplySrtDttm() {
		return aplySrtDttm;
	}
	public void setAplySrtDttm(String aplySrtDttm) {
		this.aplySrtDttm = aplySrtDttm;
	}
	public String getAplyEndDttm() {
		return aplyEndDttm;
	}
	public void setAplyEndDttm(String aplyEndDttm) {
		this.aplyEndDttm = aplyEndDttm;
	}
	public String getEvtPlc() {
		return evtPlc;
	}
	public void setEvtPlc(String evtPlc) {
		this.evtPlc = evtPlc;
	}
	public String getPsct() {
		return psct;
	}
	public void setPsct(String psct) {
		this.psct = psct;
	}
	public String getEvtInfo() {
		return evtInfo;
	}
	public void setEvtInfo(String evtInfo) {
		this.evtInfo = evtInfo;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPayFreeYn() {
		return payFreeYn;
	}
	public void setPayFreeYn(String payFreeYn) {
		this.payFreeYn = payFreeYn;
	}
	public String getPayPgYn() {
		return payPgYn;
	}
	public void setPayPgYn(String payPgYn) {
		this.payPgYn = payPgYn;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getBankAcnb() {
		return bankAcnb;
	}
	public void setBankAcnb(String bankAcnb) {
		this.bankAcnb = bankAcnb;
	}
	public String getBankAchd() {
		return bankAchd;
	}
	public void setBankAchd(String bankAchd) {
		this.bankAchd = bankAchd;
	}
	public String getEvtPrc() {
		return evtPrc;
	}
	public void setEvtPrc(String evtPrc) {
		this.evtPrc = evtPrc;
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
	public String getCtgrIdx() {
		return ctgrIdx;
	}
	public void setCtgrIdx(String ctgrIdx) {
		this.ctgrIdx = ctgrIdx;
	}
	public String getLocTypCd() {
		return locTypCd;
	}
	public void setLocTypCd(String locTypCd) {
		this.locTypCd = locTypCd;
	}
	public String getLocLat() {
		return locLat;
	}
	public void setLocLat(String locLat) {
		this.locLat = locLat;
	}
	public String getLocLng() {
		return locLng;
	}
	public void setLocLng(String locLng) {
		this.locLng = locLng;
	}
	public String getAplyFreeYn() {
		return aplyFreeYn;
	}
	public void setAplyFreeYn(String aplyFreeYn) {
		this.aplyFreeYn = aplyFreeYn;
	}
	public String getPsctFreeYn() {
		return psctFreeYn;
	}
	public void setPsctFreeYn(String psctFreeYn) {
		this.psctFreeYn = psctFreeYn;
	}
	public String getLocImgPath() {
		return locImgPath;
	}
	public void setLocImgPath(String locImgPath) {
		this.locImgPath = locImgPath;
	}
	public String getLocImgInfo() {
		return locImgInfo;
	}
	public void setLocImgInfo(String locImgInfo) {
		this.locImgInfo = locImgInfo;
	}
	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMngrMbrIdx() {
		return mngrMbrIdx;
	}
	public void setMngrMbrIdx(String mngrMbrIdx) {
		this.mngrMbrIdx = mngrMbrIdx;
	}
}
