package kr.maxerve.admin.activity.vo;

import kr.maxerve.dto.SympathyTalkDTO;

public class SympathyTalkMVO extends SympathyTalkDTO{
	
	private String rownum;				//rownum
	private String maxOrd;				//노출순서 MAX값
	private String fswTalkTypCdNm;		//공감토크 종류 공통코드명(007)
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getMaxOrd() {
		return maxOrd;
	}
	public void setMaxOrd(String maxOrd) {
		this.maxOrd = maxOrd;
	}
	public String getFswTalkTypCdNm() {
		return fswTalkTypCdNm;
	}
	public void setFswTalkTypCdNm(String fswTalkTypCdNm) {
		this.fswTalkTypCdNm = fswTalkTypCdNm;
	}
}
