package kr.maxerve.dto;

public class PaymentDTO {
	private String payIdx = "";			// INT(11) NOT NULL AUTO_INCREMENT COMMENT '결제 인덱스',
	private String mbrIdx = "";			// INT(11) NOT NULL COMMENT '회원코드',
	private String locTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '소재지 공통코드(010)',
	private String locIdx = "";			// INT(11) NOT NULL COMMENT '소재지 인덱스',
	private String prc = "";			// VARCHAR(10) NOT NULL COMMENT '결제금액',
	private String rstCd = "";			// VARCHAR(10) NOT NULL COMMENT '결과코드',
	private String rstMsg = "";			// VARCHAR(1000) NOT NULL COMMENT '결과메시지',
	private String tid = "";			// VARCHAR(50) NOT NULL COMMENT '거래ID',
	private String moid = "";			// VARCHAR(50) NOT NULL COMMENT '주문번호',
	private String payMthd = "";		// VARCHAR(10) NOT NULL COMMENT '결제수단',
	private String authDate = "";		// VARCHAR(14) NOT NULL COMMENT '승인일시 YYMMDDHH24mmss',
	private String authCd = "";			// VARCHAR(50) NOT NULL COMMENT '승인번호',
	private String buyerNm = "";		// VARCHAR(50) NOT NULL COMMENT '구매자명',
	private String goodsNm = "";		// VARCHAR(50) NOT NULL COMMENT '상품명',
	private String cardCd = "";			// VARCHAR(50) NOT NULL COMMENT '카드사 코드',
	private String cardNm = "";			// VARCHAR(50) NOT NULL COMMENT '결제카드사명',
	private String cardQuota = "";		// VARCHAR(10) NOT NULL COMMENT '할부개월(00:일시불,02:2개월)',
	private String cardBin = "";		// VARCHAR(50) NOT NULL COMMENT '카드BIN',
	private String cardPoint = "";		// VARCHAR(10) NOT NULL COMMENT '카드사포인트(0:미사용,1:포인트사용,2:세이브포인트사용)',
	private String vanCd = "";			// VARCHAR(10) NOT NULL COMMENT '밴코드(01(나이스),04(코밴),05(스마트로),06(신한직승인),07(다우))',
	private String bankCd = "";			// VARCHAR(10) NOT NULL COMMENT '은행 코드',
	private String bankNm = "";			// VARCHAR(10) NOT NULL COMMENT '은행명',
	private String rcptTyp = "";		// VARCHAR(1) NOT NULL COMMENT '현금 영수증 타입 (0:발행되지않음,1:소득공제,2:지출증빙)',
	private String rcptAuthCd = "";		// VARCHAR(10) NOT NULL COMMENT '현금영수증 승인 번호',
	private String rcptTid = "";		// VARCHAR(10) NOT NULL COMMENT '현금 영수증 TID',
	private String carrier = "";		// VARCHAR(10) NOT NULL COMMENT '이통사구분',
	private String dstAddr = "";		// VARCHAR(50) NOT NULL COMMENT '휴대폰번호',
	private String vbankBankCd = "";	// VARCHAR(10) NOT NULL COMMENT '가상계좌은행코드',
	private String vbankBankNm = "";	// VARCHAR(10) NOT NULL COMMENT '가상계좌은행명',
	private String vbankNbr = "";		// VARCHAR(50) NOT NULL COMMENT '가상계좌번호',
	private String vbankExpDt = "";		// VARCHAR(10) NOT NULL COMMENT '가상계좌입금예정일',
	private String vbankExpTm = "";		// VARCHAR(10) NOT NULL COMMENT '가상계좌입금예정일시분',
	private String apprYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '성공여부',
	private String cnclMsg = "";		// VARCHAR(100) NOT NULL COMMENT '취소사유',
	private String CnclRstCd = "";			// VARCHAR(10) NOT NULL COMMENT '취소코드',
	private String CnclRstMsg = "";  		// VARCHAR(100) NOT NULL COMMENT '취소 결과메시지',
	private String CnclDt = "";				// VARCHAR(10) NOT NULL COMMENT '취소일',
	private String CnclTm = "";				// VARCHAR(10) NOT NULL COMMENT '취소시간',
	private String CnclNmbr = "";				// VARCHAR(10) NOT NULL COMMENT '취소번호',
	private String CnclTid = "";	    		// VARCHAR(50) NOT NULL COMMENT '취소거래번호',
	private String ErrCd = "";				// VARCHAR(10) NOT NULL COMMENT '상세 에러코드',
	private String ErrMsg = "";				// VARCHAR(10) NOT NULL COMMENT '상세 에러메시지',
	private String CnclAuthDt = "";  		// VARCHAR(14) NOT NULL COMMENT '취소승인일시 YYMMDDHH24mmss',
	private String CnclSttCd = "";			// VARCHAR(1) NOT NULL COMMENT '거래상태코드 0: 승인, 1:전취소, 2:후취소',
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
	private String modDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getPayIdx() {
		return payIdx;
	}
	public void setPayIdx(String payIdx) {
		this.payIdx = payIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
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
	public String getRstCd() {
		return rstCd;
	}
	public void setRstCd(String rstCd) {
		this.rstCd = rstCd;
	}
	public String getRstMsg() {
		return rstMsg;
	}
	public void setRstMsg(String rstMsg) {
		this.rstMsg = rstMsg;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMoid() {
		return moid;
	}
	public void setMoid(String moid) {
		this.moid = moid;
	}
	public String getPayMthd() {
		return payMthd;
	}
	public void setPayMthd(String payMthd) {
		this.payMthd = payMthd;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getBuyerNm() {
		return buyerNm;
	}
	public void setBuyerNm(String buyerNm) {
		this.buyerNm = buyerNm;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getCardCd() {
		return cardCd;
	}
	public void setCardCd(String cardCd) {
		this.cardCd = cardCd;
	}
	public String getCardNm() {
		return cardNm;
	}
	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}
	public String getCardQuota() {
		return cardQuota;
	}
	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}
	public String getCardBin() {
		return cardBin;
	}
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	public String getCardPoint() {
		return cardPoint;
	}
	public void setCardPoint(String cardPoint) {
		this.cardPoint = cardPoint;
	}
	public String getVanCd() {
		return vanCd;
	}
	public void setVanCd(String vanCd) {
		this.vanCd = vanCd;
	}
	public String getBankCd() {
		return bankCd;
	}
	public void setBankCd(String bankCd) {
		this.bankCd = bankCd;
	}
	public String getBankNm() {
		return bankNm;
	}
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}
	public String getRcptTyp() {
		return rcptTyp;
	}
	public void setRcptTyp(String rcptTyp) {
		this.rcptTyp = rcptTyp;
	}
	public String getRcptAuthCd() {
		return rcptAuthCd;
	}
	public void setRcptAuthCd(String rcptAuthCd) {
		this.rcptAuthCd = rcptAuthCd;
	}
	public String getRcptTid() {
		return rcptTid;
	}
	public void setRcptTid(String rcptTid) {
		this.rcptTid = rcptTid;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getDstAddr() {
		return dstAddr;
	}
	public void setDstAddr(String dstAddr) {
		this.dstAddr = dstAddr;
	}
	public String getVbankBankCd() {
		return vbankBankCd;
	}
	public void setVbankBankCd(String vbankBankCd) {
		this.vbankBankCd = vbankBankCd;
	}
	public String getVbankBankNm() {
		return vbankBankNm;
	}
	public void setVbankBankNm(String vbankBankNm) {
		this.vbankBankNm = vbankBankNm;
	}
	public String getVbankNbr() {
		return vbankNbr;
	}
	public void setVbankNbr(String vbankNbr) {
		this.vbankNbr = vbankNbr;
	}
	public String getVbankExpDt() {
		return vbankExpDt;
	}
	public void setVbankExpDt(String vbankExpDt) {
		this.vbankExpDt = vbankExpDt;
	}
	public String getVbankExpTm() {
		return vbankExpTm;
	}
	public void setVbankExpTm(String vbankExpTm) {
		this.vbankExpTm = vbankExpTm;
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
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getApprYn() {
		return apprYn;
	}
	public void setApprYn(String apprYn) {
		this.apprYn = apprYn;
	}
	public String getCnclRstCd() {
		return CnclRstCd;
	}
	public void setCnclRstCd(String cnclRstCd) {
		CnclRstCd = cnclRstCd;
	}
	public String getCnclRstMsg() {
		return CnclRstMsg;
	}
	public void setCnclRstMsg(String cnclRstMsg) {
		CnclRstMsg = cnclRstMsg;
	}
	public String getCnclDt() {
		return CnclDt;
	}
	public void setCnclDt(String cnclDt) {
		CnclDt = cnclDt;
	}
	public String getCnclTm() {
		return CnclTm;
	}
	public void setCnclTm(String cnclTm) {
		CnclTm = cnclTm;
	}
	public String getCnclTid() {
		return CnclTid;
	}
	public void setCnclTid(String cnclTid) {
		CnclTid = cnclTid;
	}
	public String getErrCd() {
		return ErrCd;
	}
	public void setErrCd(String errCd) {
		ErrCd = errCd;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	public String getCnclAuthDt() {
		return CnclAuthDt;
	}
	public void setCnclAuthDt(String cnclAuthDt) {
		CnclAuthDt = cnclAuthDt;
	}
	public String getCnclSttCd() {
		return CnclSttCd;
	}
	public void setCnclSttCd(String cnclSttCd) {
		CnclSttCd = cnclSttCd;
	}
	public String getCnclNmbr() {
		return CnclNmbr;
	}
	public void setCnclNmbr(String cnclNmbr) {
		CnclNmbr = cnclNmbr;
	}
	public String getCnclMsg() {
		return cnclMsg;
	}
	public void setCnclMsg(String cnclMsg) {
		this.cnclMsg = cnclMsg;
	}
}
