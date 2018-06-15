package kr.maxerve.admin.editor.vo;

import org.springframework.web.multipart.MultipartFile;

public class EditorUploadVO {
	private String CKEditorFuncNum;
	private String CKEditor;
	private String langCode;
	private MultipartFile upload;
	
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
	public String getCKEditor() {
		return CKEditor;
	}
	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
}
