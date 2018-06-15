package kr.maxerve.admin.file.vo;

import kr.maxerve.dto.FileDTO;

/**
* FileMVO.java
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
public class FileMVO extends FileDTO {
	private String fileInfo = "";	// 첨부설명

	public String getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
}
