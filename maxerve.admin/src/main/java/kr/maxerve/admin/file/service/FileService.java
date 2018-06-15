package kr.maxerve.admin.file.service;

import java.util.List;

import kr.maxerve.admin.cmmn.vo.ReqFileVO;
import kr.maxerve.admin.editor.vo.EditorUploadVO;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.FileDTO;

public interface FileService {
	public FileDTO editorUpload(EditorUploadVO editorUploadVO) throws Exception;
	public List<FileDTO> uploadFile(ReqFileVO fileVO) throws Exception;
	public List<FileDTO> removeFile(FileDTO fileVO) throws Exception;
	public FileDTO selectFile(FileDTO fileVO) throws Exception;
	public List<FileDTO> selectFileList(FileDTO fileVO) throws Exception;
	/**
	 * 임시파일 이동
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String moveFile(String filePath) throws Exception;
	/**
	 * 에디터 본문 파일이동
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String moveEditor(String body) throws Exception;
	public List<FileMVO> selectAttachList(AttachFileDTO vo) throws Exception;
}
