package kr.maxerve.admin.member.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.MoveInApplyDTO;

public class NewMoveInApplyUVO extends MoveInApplyDTO{

	@NotEmpty
	private String mvinAplyIdx = "";	// INT(11) NOT NULL AUTO_INCREMENT COMMENT '입주신청 인덱스'
	@NotEmpty
	private String mvinSrtDt = "";		// VARCHAR(10) NOT NULL COMMENT '입주기간 시작일'
	@NotEmpty
	private String mvinEndDt = "";		// VARCHAR(10) NOT NULL COMMENT '입주기간 종료일'
	@NotEmpty
	private String mvinPlc = "";		// VARCHAR(50) NOT NULL COMMENT '입주장소'
	@NotEmpty
	private String aplyPgrCd = "";		// VARCHAR(50) NOT NULL COMMENT '입주신청상태 공통코드(022)'
	
	public String getMvinAplyIdx() {
		return mvinAplyIdx;
	}
	public void setMvinAplyIdx(String mvinAplyIdx) {
		this.mvinAplyIdx = mvinAplyIdx;
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
}
