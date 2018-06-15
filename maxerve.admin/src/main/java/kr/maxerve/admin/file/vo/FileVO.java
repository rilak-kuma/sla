package kr.maxerve.admin.file.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* FileVO.java
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
@SuppressWarnings("serial")
public class FileVO implements Serializable {


	private String filePath;			// varchar(200) Not Null Comment '파일패스'
	private String origFileNm;			// varchar(50) Not Null Comment '원본파일명'
	private String fileTypCd;			// varchar(50) Not Null Comment '파일 타입 공통코드'
	private String wdth;				// int(11) NOT NULL COMMENT '이미지 폭'
	private String hgth;				// int(11) NOT NULL COMMENT '이미지 높이'
	private String fileSize;			// int(11) Not Null Comment '파일크기'
	private String cnttTyp;				// varchar(50) Not Null Comment '컨텐츠 타입'
	private String creDttm;				// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시'

	@JsonIgnore
	private MultipartFile reqUploadFile;   //업로드 파일
	private String serverSubPath;		   //디렉토리패스
	private String uploadThumbPathUrl;	   //썸네일패스
	private String newFilePath;		       //실제 패스
	private String maxWidth;
	private String xyRatio;
	private CropImage cropImage;
	private String type;
	private String removeFilePath;			  //삭제할 파일


	public FileVO() {}

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
	public MultipartFile getReqUploadFile() {
		return reqUploadFile;
	}
	public void setReqUploadFile(MultipartFile reqUploadFile) {
		this.reqUploadFile = reqUploadFile;
	}
	public String getServerSubPath() {
		return serverSubPath;
	}
	public void setServerSubPath(String serverSubPath) {
		this.serverSubPath = serverSubPath;
	}
	public String getUploadThumbPathUrl() {
		return uploadThumbPathUrl;
	}
	public void setUploadThumbPathUrl(String uploadThumbPathUrl) {
		this.uploadThumbPathUrl = uploadThumbPathUrl;
	}
	public String getNewFilePath() {
		return newFilePath;
	}
	public void setNewFilePath(String newFilePath) {
		this.newFilePath = newFilePath;
	}
	public String getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(String maxWidth) {
		this.maxWidth = maxWidth;
	}
	public String getXyRatio() {
		return xyRatio;
	}
	public void setXyRatio(String xyRatio) {
		this.xyRatio = xyRatio;
	}
	public CropImage getCropImage() {
		return cropImage;
	}
	public void setCropImage(CropImage cropImage) {
		this.cropImage = cropImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemoveFilePath() {
		return removeFilePath;
	}
	public void setRemoveFilePath(String removeFilePath) {
		this.removeFilePath = removeFilePath;
	}
}