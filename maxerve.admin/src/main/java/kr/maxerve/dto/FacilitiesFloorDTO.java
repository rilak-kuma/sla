package kr.maxerve.dto;

/**
* FacilitiesFloorDTO.java
* @author LEEC.J
* @since 2018.06.15
* @version 1.0
* @see
*
* <pre>
* 파일
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.15      LEEC.J         최초 생성
* </pre>
*/
public class FacilitiesFloorDTO {
	private String flr = "";			// INT(11) NOT NULL COMMENT '층수',
	private String filePath = "";	// VARCHAR(200) NOT NULL COMMENT '층별안내 이미지',
	private String fileInfo = "";	// VARCHAR(20) NOT NULL COMMENT '층별안내 이미지 설명',
	private String creDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
	private String modDttm = "";		// DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

	public String getFlr() {
		return flr;
	}
	public void setFlr(String flr) {
		this.flr = flr;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
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
}
