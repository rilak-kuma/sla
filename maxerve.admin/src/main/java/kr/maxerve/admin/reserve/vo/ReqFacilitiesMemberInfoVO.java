package kr.maxerve.admin.reserve.vo;

import kr.maxerve.dto.FacilitiesMemberDTO;

public class ReqFacilitiesMemberInfoVO extends FacilitiesMemberDTO{

	private String recruitmentPrd;	//모집기간
	private String memberCnt;		//회원수
	private String useYn;			//진행상태
	private String lmtMbCnt;		//모집정원
	private String sortType;		//정렬기준
    
	public String getRecruitmentPrd() {
		return recruitmentPrd;
	}
	public void setRecruitmentPrd(String recruitmentPrd) {
		this.recruitmentPrd = recruitmentPrd;
	}
	public String getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(String memberCnt) {
		this.memberCnt = memberCnt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getLmtMbCnt() {
		return lmtMbCnt;
	}
	public void setLmtMbCnt(String lmtMbCnt) {
		this.lmtMbCnt = lmtMbCnt;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
