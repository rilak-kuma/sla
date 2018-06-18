package kr.maxerve.admin.member.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.MoveInApplyDTO;

public class NewMoveInApplyOztnUVO extends MoveInApplyDTO{
	
	@NotEmpty
	private String mvinAplyIdx = "";	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '입주신청 인덱스'
	@NotEmpty
	private String oztnTypCd = "";		// VARCHAR(50) NOT NULL COMMENT '단체 형태 공통코드(014)'
	@NotEmpty
	private String svcAct = "";			// VARCHAR(50) NOT NULL COMMENT '주요활동(서비스)'
	@NotEmpty
	private String ceoPhn = "";			// VARCHAR(50) NOT NULL COMMENT '대표자 휴대전화'
	@NotEmpty
	private String oztnAddr = "";		// VARCHAR(50) NOT NULL COMMENT '단체주소'
	@NotEmpty
	private String ofcPhn = "";			// VARCHAR(50) NOT NULL COMMENT '사무실 전화'
	@NotEmpty
	private String ofcFax = "";			// VARCHAR(50) NOT NULL COMMENT '사무실 팩스'
	@NotEmpty
	private String mngrNm = "";			// VARCHAR(50) NOT NULL COMMENT '담당자명'
	@NotEmpty
	private String mngrPhn = "";		// VARCHAR(50) NOT NULL COMMENT '담당자전화'
	@NotEmpty
	private String mngrEmil = "";		// VARCHAR(200) NOT NULL COMMENT '담당자 이메일'
	@NotEmpty
	private String ftePsct = "";		// INT(11) NOT NULL COMMENT '상근인원'
	@NotEmpty
	private String mvinGrpCd = "";		// VARCHAR(50) NOT NULL COMMENT '입주그룹 공통코드(025)'
	
	public String getMvinAplyIdx() {
		return mvinAplyIdx;
	}
	public void setMvinAplyIdx(String mvinAplyIdx) {
		this.mvinAplyIdx = mvinAplyIdx;
	}
	public String getOztnTypCd() {
		return oztnTypCd;
	}
	public void setOztnTypCd(String oztnTypCd) {
		this.oztnTypCd = oztnTypCd;
	}
	public String getSvcAct() {
		return svcAct;
	}
	public void setSvcAct(String svcAct) {
		this.svcAct = svcAct;
	}
	public String getCeoPhn() {
		return ceoPhn;
	}
	public void setCeoPhn(String ceoPhn) {
		this.ceoPhn = ceoPhn;
	}
	public String getOztnAddr() {
		return oztnAddr;
	}
	public void setOztnAddr(String oztnAddr) {
		this.oztnAddr = oztnAddr;
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
	public String getMvinGrpCd() {
		return mvinGrpCd;
	}
	public void setMvinGrpCd(String mvinGrpCd) {
		this.mvinGrpCd = mvinGrpCd;
	}
}
