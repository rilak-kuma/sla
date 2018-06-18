package kr.maxerve.dto;

/**
* MoveInApplyDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 입주신청
* TBL_MVIN_APLY
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class MoveInApplyDTO {
	private String mvinAplyIdx = "";	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '입주신청 인덱스'
	private String oztnNm = "";			// VARCHAR(50) NOT NULL COMMENT '단체명'
	private String oztnTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '단체 형태 공통코드(014)'
	private String oztnTypEtc = "";		// VARCHAR(100) NOT NULL COMMENT '단체형태 기타'
	private String svcAct = "";			// VARCHAR(50) NOT NULL COMMENT '주요활동(서비스)'
	private String ceoNm = "";			// VARCHAR(50) NOT NULL COMMENT '대표자명'
	private String ceoPhn = "";			// VARCHAR(50) NOT NULL COMMENT '대표자 휴대전화'
	private String ceoEmil = "";		// VARCHAR(200) NOT NULL COMMENT '대표자 이메일'
	private String CRN = "";			// VARCHAR(50) NOT NULL COMMENT '사업자등록번호'
	private String estDt = "";			// VARCHAR(10) NOT NULL COMMENT '설립일'
	private String oztnAddr = "";		// VARCHAR(50) NOT NULL COMMENT '단체주소'
	private String hpg = "";			// VARCHAR(200) NOT NULL COMMENT '홈페이지'
	private String ofcPhn = "";			// VARCHAR(50) NOT NULL COMMENT '사무실 전화'
	private String ofcFax = "";			// VARCHAR(50) NOT NULL COMMENT '사무실 팩스'
	private String mngrNm = "";			// VARCHAR(50) NOT NULL COMMENT '담당자명'
	private String mngrPhn = "";		// VARCHAR(50) NOT NULL COMMENT '담당자전화'
	private String mngrEmil = "";		// VARCHAR(200) NOT NULL COMMENT '담당자 이메일'
	private String ftePsct = "";		// INT(11) NOT NULL COMMENT '상근인원'
	private String mainAct = "";		// VARCHAR(1000) NOT NULL COMMENT '주요활동/연혁'
	private String aplyNm = "";			// VARCHAR(50) NOT NULL COMMENT '신청인명'
	private String oztnItdc = "";		// VARCHAR(1000) NOT NULL COMMENT '단체소개'
	private String sclInv = "";			// VARCHAR(1000) NOT NULL COMMENT '사회혁신'
	private String sclIsu = "";			// VARCHAR(1000) NOT NULL COMMENT '사회문제'
	private String isuIdea = "";		// VARCHAR(1000) NOT NULL COMMENT '문제해결 아이디어'
	private String epctEft = "";		// VARCHAR(1000) NOT NULL COMMENT '기대효과'
	private String oztnCcpc = "";		// VARCHAR(1000) NOT NULL COMMENT '핵심역량'
	private String bssPlan = "";		// VARCHAR(1000) NOT NULL COMMENT '사업계획'
	private String cprPlan = "";		// VARCHAR(1000) NOT NULL COMMENT '협업계획'
	private String cmmnRsc = "";		// VARCHAR(1000) NOT NULL COMMENT '공유자원'
	private String mvinSrtDt = "";		// VARCHAR(10) NOT NULL COMMENT '입주기간 시작일'
	private String mvinEndDt = "";		// VARCHAR(10) NOT NULL COMMENT '입주기간 종료일'
	private String mvinPlc = "";		// VARCHAR(50) NOT NULL COMMENT '입주장소'
	private String mngrCmmt = "";		// VARCHAR(1000) NOT NULL COMMENT '관리자 코멘트',
	private String aplyPgrCd = "";		// VARCHAR(50) NOT NULL COMMENT '입주신청상태 공통코드(022)'
	private String mvinGrpCd = "";		// VARCHAR(50) NOT NULL COMMENT '입주그룹 공통코드(025)'
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시'

	public String getMvinAplyIdx() {
		return mvinAplyIdx;
	}
	public void setMvinAplyIdx(String mvinAplyIdx) {
		this.mvinAplyIdx = mvinAplyIdx;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getOztnTypCd() {
		return oztnTypCd;
	}
	public void setOztnTypCd(String oztnTypCd) {
		this.oztnTypCd = oztnTypCd;
	}
	public String getOztnTypEtc() {
		return oztnTypEtc;
	}
	public void setOztnTypEtc(String oztnTypEtc) {
		this.oztnTypEtc = oztnTypEtc;
	}
	public String getSvcAct() {
		return svcAct;
	}
	public void setSvcAct(String svcAct) {
		this.svcAct = svcAct;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getCeoEmil() {
		return ceoEmil;
	}
	public void setCeoEmil(String ceoEmil) {
		this.ceoEmil = ceoEmil;
	}
	public String getCRN() {
		return CRN;
	}
	public void setCRN(String cRN) {
		CRN = cRN;
	}
	public String getEstDt() {
		return estDt;
	}
	public void setEstDt(String estDt) {
		this.estDt = estDt;
	}
	public String getOztnAddr() {
		return oztnAddr;
	}
	public void setOztnAddr(String oztnAddr) {
		this.oztnAddr = oztnAddr;
	}
	public String getHpg() {
		return hpg;
	}
	public void setHpg(String hpg) {
		this.hpg = hpg;
	}
	public String getOfcPhn() {
		return ofcPhn;
	}
	public void setOfcPhn(String ofcPhn) {
		this.ofcPhn = ofcPhn;
	}
	public String getOfcFax() {
		return ofcFax;
	}
	public void setOfcFax(String ofcFax) {
		this.ofcFax = ofcFax;
	}
	public String getMngrNm() {
		return mngrNm;
	}
	public void setMngrNm(String mngrNm) {
		this.mngrNm = mngrNm;
	}
	public String getMngrPhn() {
		return mngrPhn;
	}
	public void setMngrPhn(String mngrPhn) {
		this.mngrPhn = mngrPhn;
	}
	public String getMngrEmil() {
		return mngrEmil;
	}
	public void setMngrEmil(String mngrEmil) {
		this.mngrEmil = mngrEmil;
	}
	public String getFtePsct() {
		return ftePsct;
	}
	public void setFtePsct(String ftePsct) {
		this.ftePsct = ftePsct;
	}
	public String getMainAct() {
		return mainAct;
	}
	public void setMainAct(String mainAct) {
		this.mainAct = mainAct;
	}
	public String getAplyNm() {
		return aplyNm;
	}
	public void setAplyNm(String aplyNm) {
		this.aplyNm = aplyNm;
	}
	public String getOztnItdc() {
		return oztnItdc;
	}
	public void setOztnItdc(String oztnItdc) {
		this.oztnItdc = oztnItdc;
	}
	public String getSclInv() {
		return sclInv;
	}
	public void setSclInv(String sclInv) {
		this.sclInv = sclInv;
	}
	public String getSclIsu() {
		return sclIsu;
	}
	public void setSclIsu(String sclIsu) {
		this.sclIsu = sclIsu;
	}
	public String getIsuIdea() {
		return isuIdea;
	}
	public void setIsuIdea(String isuIdea) {
		this.isuIdea = isuIdea;
	}
	public String getEpctEft() {
		return epctEft;
	}
	public void setEpctEft(String epctEft) {
		this.epctEft = epctEft;
	}
	public String getOztnCcpc() {
		return oztnCcpc;
	}
	public void setOztnCcpc(String oztnCcpc) {
		this.oztnCcpc = oztnCcpc;
	}
	public String getBssPlan() {
		return bssPlan;
	}
	public void setBssPlan(String bssPlan) {
		this.bssPlan = bssPlan;
	}
	public String getCprPlan() {
		return cprPlan;
	}
	public void setCprPlan(String cprPlan) {
		this.cprPlan = cprPlan;
	}
	public String getCmmnRsc() {
		return cmmnRsc;
	}
	public void setCmmnRsc(String cmmnRsc) {
		this.cmmnRsc = cmmnRsc;
	}
	public String getMvinSrtDt() {
		return mvinSrtDt;
	}
	public void setMvinSrtDt(String mvinSrtDt) {
		this.mvinSrtDt = mvinSrtDt;
	}
	public String getMvinEndDt() {
		return mvinEndDt;
	}
	public void setMvinEndDt(String mvinEndDt) {
		this.mvinEndDt = mvinEndDt;
	}
	public String getMvinPlc() {
		return mvinPlc;
	}
	public void setMvinPlc(String mvinPlc) {
		this.mvinPlc = mvinPlc;
	}
	public String getAplyPgrCd() {
		return aplyPgrCd;
	}
	public void setAplyPgrCd(String aplyPgrCd) {
		this.aplyPgrCd = aplyPgrCd;
	}
	public String getMvinGrpCd() {
		return mvinGrpCd;
	}
	public void setMvinGrpCd(String mvinGrpCd) {
		this.mvinGrpCd = mvinGrpCd;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getMngrCmmt() {
		return mngrCmmt;
	}
	public void setMngrCmmt(String mngrCmmt) {
		this.mngrCmmt = mngrCmmt;
	}
}
