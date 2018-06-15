package kr.maxerve.admin.cmmn.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.maxerve.admin.file.vo.CropImage;
import kr.maxerve.dto.FileDTO;

/**
* ReqFileVO
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
* 2018.06.15      LEEC.J        최초 생성
* </pre>
*/
public class ReqFileVO extends FileDTO {

	@JsonIgnore
	private MultipartFile reqUploadFile;   //업로드 파일
	private String serverSubPath;		   //디렉토리패스
	private String uploadThumbPathUrl;	   //썸네일패스
	private String reqFilePath;		       //실제 패스
	private String maxWidth;
	private String maxHeight;
	private String xyRatio;
	private CropImage cropImage;
	private String type;
	private String removeFilePath;			  //삭제할 파일
	private String thumnailType;

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
	public String getReqFilePath() {
		return reqFilePath;
	}
	public void setReqFilePath(String reqFilePath) {
		this.reqFilePath = reqFilePath;
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
	public String getThumnailType() {
		return thumnailType;
	}
	public void setThumnailType(String thumnailType) {
		this.thumnailType = thumnailType;
	}
	public String getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(String maxHeight) {
		this.maxHeight = maxHeight;
	}
}