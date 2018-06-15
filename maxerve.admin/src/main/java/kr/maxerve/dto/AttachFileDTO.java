package kr.maxerve.dto;

/**
* AttachFileDTO.java
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
public class AttachFileDTO {
	private String atcLocCd;		// varchar(50) Not Null Comment '첨부소재지 공통코드'
	private String atcLocIdx;		// int(11) Not Null Comment '첨부소재지 인덱스'
	private String ord;				// int(11) NOT NULL COMMENT '순서'
	private String filePath;		// varchar(200) Not Null Comment '파일경로'
	private String fileInfo;		// varchar(100) NOT NULL COMMENT '파일설명'
	
	public String getAtcLocCd() {
		return atcLocCd;
	}
	public void setAtcLocCd(String atcLocCd) {
		this.atcLocCd = atcLocCd;
	}
	public String getAtcLocIdx() {
		return atcLocIdx;
	}
	public void setAtcLocIdx(String atcLocIdx) {
		this.atcLocIdx = atcLocIdx;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
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

}
