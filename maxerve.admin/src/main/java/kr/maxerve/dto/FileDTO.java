package kr.maxerve.dto;

/**
* FileVO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 파일
* TBL_FILE
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class FileDTO {
	private String filePath = "";			// varchar(200) Not Null Comment '파일패스'
	private String tmnPath = "";			// 섬네일패스
	private String origFileNm = "";			// varchar(50) Not Null Comment '원본파일명'
	private String fileTypCd = "";			// varchar(50) Not Null Comment '파일 타입 공통코드'
	private String wdth = "";				// int(11) NOT NULL COMMENT '이미지 폭'
	private String hgth = "";				// int(11) NOT NULL COMMENT '이미지 높이'
	private String fileSize = "";			// int(11) Not Null Comment '파일크기'
	private String cnttTyp = "";			// varchar(50) Not Null Comment '컨텐츠 타입'
	private String creDttm = "";			// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrigFileNm() {
		return origFileNm;
	}
	public void setOrigFileNm(String origFileNm) {
		this.origFileNm = origFileNm;
	}
	public String getFileTypCd() {
		return fileTypCd;
	}
	public void setFileTypCd(String fileTypCd) {
		this.fileTypCd = fileTypCd;
	}
	public String getWdth() {
		return wdth;
	}
	public void setWdth(String wdth) {
		this.wdth = wdth;
	}
	public String getHgth() {
		return hgth;
	}
	public void setHgth(String hgth) {
		this.hgth = hgth;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getCnttTyp() {
		return cnttTyp;
	}
	public void setCnttTyp(String cnttTyp) {
		this.cnttTyp = cnttTyp;
	}
	public String getCreDttm() {
		return creDttm;
	}
	public void setCreDttm(String creDttm) {
		this.creDttm = creDttm;
	}
	public String getTmnPath() {
		return tmnPath;
	}
	public void setTmnPath(String tmnPath) {
		this.tmnPath = tmnPath;
	}

}