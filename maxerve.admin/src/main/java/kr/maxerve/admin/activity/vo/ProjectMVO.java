package kr.maxerve.admin.activity.vo;

import kr.maxerve.dto.ProjectDTO;

public class ProjectMVO extends ProjectDTO{

	private String rownum;				//rownum
	private String ctgrIdxNm;			//카테고리명
	private String ceoNm;				//등록자이름(CEO명)
	private String oztnNm;				//단체명
	private String modMngrMbrNm;		//수정자(관리자)명
	private String modMngrMbrDpt;		//수정자(관리자)부서
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getCtgrIdxNm() {
		return ctgrIdxNm;
	}
	public void setCtgrIdxNm(String ctgrIdxNm) {
		this.ctgrIdxNm = ctgrIdxNm;
	}
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
