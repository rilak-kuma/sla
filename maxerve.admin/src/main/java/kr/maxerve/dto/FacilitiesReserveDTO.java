package kr.maxerve.dto;

/**
* FacilitiesReserveDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 시설예약
* TBL_FCT_RSVT
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class FacilitiesReserveDTO {

	private String fctRsvtIdx = "";		// int(11) Not Null AutoIncrement Comment '시설예약 인덱스'
	private String mbrIdx = "";			// int(11) Not Null Comment '회원인덱스(비회원 0)'
	private String rsvtPsct = "";		// int(11) Not Null Comment '인원'
	private String srtDttm = "";		// datetime Not Null Comment '사용 시작일시'
	private String endDttm = "";		// datetime Not Null Comment '사용 종료일시'
	private String fctMstIdx = "";		// int(11) Not Null Comment '시설 인덱스'
	private String oztnNm = "";			// varchar(50) Not Null Comment '단체명'
	private String rsvtNm = "";			// varchar(50) Not Null Comment '담당자명'
	private String rsvtPhnNmbr = "";	// varchar(50) Not Null Comment '담당자 연락처'
	private String rsvtEmil = "";		// varchar(200) Not Null Comment '담당자 이메일'
	private String rsvtAss = "";		// varchar(50) Not Null Comment '담당자 소속/부서'
	private String rsvtPrps = "";		// varchar(300) Not Null Comment '예약목적'
	private String evtNm = "";			// varchar(50) Not Null Comment '행사명'
	private String evtInfo = "";		// varchar(300) Not Null Comment '행사내용'
	private String useTnm = "";			// varchar(1000) Not Null Comment '사용기자재'
	private String clnPlan = "";		// varchar(1000) Not Null Comment '뒤처리 계획'
	private String safeMngPlan = "";	// varchar(1000) Not Null Comment '안전 관리 계획'
	private String powYn = "";			// varchar(1) Not Null Comment '전기사용여부'
	private String powInfo = "";		// varchar(1000) Not Null Comment '전기사용품목 및 와트'
	private String bnnrFtgYn = "";		// varchar(1) Not Null Comment '현수막 정문 사용여부'
	private String bnnrInfo = "";		// varchar(300) Not Null Comment '현수막 수량 및 사이즈'
	private String prmYn = "";			// varchar(1) Not Null Comment '홍보물부착여부'
	private String bnnrFobYn = "";		// varchar(1) Not Null Comment '현수막 미래청 사용여부'
	private String prmInfo = "";		// varchar(300) Not Null Comment '홍보물 종류'
	private String msgToMngr = "";		// varchar(1000) Not Null Comment '예약 담당자에게 메시지'
	private String rsvtPgr = "";		// varchar(50) Not Null Comment '예약진행상태 공통코드(020)'
	private String rsvtTm = "";			// varchar(10) Not Null Comment '예약시간(분단위)'
	private String prc = "";			// varchar(10) NOT NULL COMMENT '결제금액'
	private String creDttm = "";		// datetime Not Null Default CurrentTimestamp Comment '예약일'
	private String apprDt = "";			// varchar(10) Not Null Comment '승인일'
	private String delYn = "";			// varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부'
	public String getFctRsvtIdx() {
		return fctRsvtIdx;
	}
	public void setFctRsvtIdx(String fctRsvtIdx) {
		this.fctRsvtIdx = fctRsvtIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getRsvtPsct() {
		return rsvtPsct;
	}
	public void setRsvtPsct(String rsvtPsct) {
		this.rsvtPsct = rsvtPsct;
	}
	public String getSrtDttm() {
		return srtDttm;
	}
	public void setSrtDttm(String srtDttm) {
		this.srtDttm = srtDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public String getFctMstIdx() {
		return fctMstIdx;
	}
	public void setFctMstIdx(String fctMstIdx) {
		this.fctMstIdx = fctMstIdx;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getRsvtNm() {
		return rsvtNm;
	}
	public void setRsvtNm(String rsvtNm) {
		this.rsvtNm = rsvtNm;
	}
	public String getRsvtPhnNmbr() {
		return rsvtPhnNmbr;
	}
	public void setRsvtPhnNmbr(String rsvtPhnNmbr) {
		this.rsvtPhnNmbr = rsvtPhnNmbr;
	}
	public String getRsvtEmil() {
		return rsvtEmil;
	}
	public void setRsvtEmil(String rsvtEmil) {
		this.rsvtEmil = rsvtEmil;
	}
	public String getRsvtAss() {
		return rsvtAss;
	}
	public void setRsvtAss(String rsvtAss) {
		this.rsvtAss = rsvtAss;
	}
	public String getRsvtPrps() {
		return rsvtPrps;
	}
	public void setRsvtPrps(String rsvtPrps) {
		this.rsvtPrps = rsvtPrps;
	}
	public String getEvtNm() {
		return evtNm;
	}
	public void setEvtNm(String evtNm) {
		this.evtNm = evtNm;
	}
	public String getEvtInfo() {
		return evtInfo;
	}
	public void setEvtInfo(String evtInfo) {
		this.evtInfo = evtInfo;
	}
	public String getUseTnm() {
		return useTnm;
	}
	public void setUseTnm(String useTnm) {
		this.useTnm = useTnm;
	}
	public String getClnPlan() {
		return clnPlan;
	}
	public void setClnPlan(String clnPlan) {
		this.clnPlan = clnPlan;
	}
	public String getSafeMngPlan() {
		return safeMngPlan;
	}
	public void setSafeMngPlan(String safeMngPlan) {
		this.safeMngPlan = safeMngPlan;
	}
	public String getPowYn() {
		return powYn;
	}
	public void setPowYn(String powYn) {
		this.powYn = powYn;
	}
	public String getPowInfo() {
		return powInfo;
	}
	public void setPowInfo(String powInfo) {
		this.powInfo = powInfo;
	}
	public String getBnnrFtgYn() {
		return bnnrFtgYn;
	}
	public void setBnnrFtgYn(String bnnrFtgYn) {
		this.bnnrFtgYn = bnnrFtgYn;
	}
	public String getBnnrInfo() {
		return bnnrInfo;
	}
	public void setBnnrInfo(String bnnrInfo) {
		this.bnnrInfo = bnnrInfo;
	}
	public String getPrmYn() {
		return prmYn;
	}
	public void setPrmYn(String prmYn) {
		this.prmYn = prmYn;
	}
	public String getBnnrFobYn() {
		return bnnrFobYn;
	}
	public void setBnnrFobYn(String bnnrFobYn) {
		this.bnnrFobYn = bnnrFobYn;
	}
	public String getPrmInfo() {
		return prmInfo;
	}
	public void setPrmInfo(String prmInfo) {
		this.prmInfo = prmInfo;
	}
	public String getMsgToMngr() {
		return msgToMngr;
	}
	public void setMsgToMngr(String msgToMngr) {
		this.msgToMngr = msgToMngr;
	}
	public String getRsvtPgr() {
		return rsvtPgr;
	}
	public void setRsvtPgr(String rsvtPgr) {
		this.rsvtPgr = rsvtPgr;
	}
	public String getRsvtTm() {
		return rsvtTm;
	}
	public void setRsvtTm(String rsvtTm) {
		this.rsvtTm = rsvtTm;
	}
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getApprDt() {
		return apprDt;
	}
	public void setApprDt(String apprDt) {
		this.apprDt = apprDt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}