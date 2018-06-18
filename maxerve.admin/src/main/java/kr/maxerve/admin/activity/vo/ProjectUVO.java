package kr.maxerve.admin.activity.vo;

import kr.maxerve.dto.ProjectDTO;

public class ProjectUVO extends ProjectDTO{

	private String pjtIdx	="";			// int(11) NOT NULL AUTOINCREMENT COMMENT '프로젝트 인덱스'
	private String useYn = "";			// VARCHAR(1) NOT NULL DEFAULT 'Y' COMMENT '사용여부'
	
	public String getPjtIdx() {
		return pjtIdx;
	}
	public void setPjtIdx(String pjtIdx) {
		this.pjtIdx = pjtIdx;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
