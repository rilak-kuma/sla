package kr.maxerve.admin.member.vo;

import kr.maxerve.dto.MoveInApplyDTO;
/**
 * 입주단체관리 VO
 * extends MoveInApplyDTO
 */
public class MoveInOztnMVO extends MoveInApplyDTO{


	private String onlineMb;		//온라인멤버여부
	private String oztnTypCdNm;		//단체형태명
	private String aplyPgrCdNm;		//입주신청상태명
	private String mvinGrpCdNm;		//입주그룹명
	private String grpCnt;			//입주단체수
	private String ftePsct;			//입주단체 상근인원 합
	
	public String getOnlineMb() {
		return onlineMb;
	}
	public void setOnlineMb(String onlineMb) {
		this.onlineMb = onlineMb;
	}
	public String getOztnTypCdNm() {
		return oztnTypCdNm;
	}
	public void setOztnTypCdNm(String oztnTypCdNm) {
		this.oztnTypCdNm = oztnTypCdNm;
	}
	public String getAplyPgrCdNm() {
		return aplyPgrCdNm;
	}
	public void setAplyPgrCdNm(String aplyPgrCdNm) {
		this.aplyPgrCdNm = aplyPgrCdNm;
	}
	public String getMvinGrpCdNm() {
		return mvinGrpCdNm;
	}
	public void setMvinGrpCdNm(String mvinGrpCdNm) {
		this.mvinGrpCdNm = mvinGrpCdNm;
	}
	public String getGrpCnt() {
		return grpCnt;
	}
	public void setGrpCnt(String grpCnt) {
		this.grpCnt = grpCnt;
	}
	public String getFtePsct() {
		return ftePsct;
	}
	public void setFtePsct(String ftePsct) {
		this.ftePsct = ftePsct;
	}
}
