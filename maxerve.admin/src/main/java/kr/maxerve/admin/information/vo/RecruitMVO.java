package kr.maxerve.admin.information.vo;

import kr.maxerve.dto.RecruitRoomDTO;

public class RecruitMVO extends RecruitRoomDTO{
	
	private String ceoNm;				//등록자이름(CEO명)
	private String oztnNm;				//단체명
	private String blog;				//블로그
	private String modMngrMbrNm;		//수정자 이름(관리자)
	private String modMngrMbrDpt;		//수정자 부서(관리자)
	
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getModMngrMbrNm() {
		return modMngrMbrNm;
	}
	public void setModMngrMbrNm(String modMngrMbrNm) {
		this.modMngrMbrNm = modMngrMbrNm;
	}
	public String getModMngrMbrDpt() {
		return modMngrMbrDpt;
	}
	public void setModMngrMbrDpt(String modMngrMbrDpt) {
		this.modMngrMbrDpt = modMngrMbrDpt;
	}
	
}
