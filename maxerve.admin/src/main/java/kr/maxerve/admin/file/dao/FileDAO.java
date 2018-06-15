package kr.maxerve.admin.file.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.cmmn.vo.ReqFileVO;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.FileDTO;

@Repository("fileDAO")
public class FileDAO extends BaseDAOSupport {
	/**
	 * 파일등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertFile(ReqFileVO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.file.fileDAO.insertFile", vo);
	}

	/**
	 * 첨부파일등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertAtcFile(AttachFileDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.file.fileDAO.insertAtcFile", vo);
	}

	/**
	 * 첨부파일삭제
	 * @param vo
	 * @throws Exception
	 */
	public void deleteAtcFile(AttachFileDTO vo) throws Exception {
		getSqlSession().delete("kr.maxerve.file.fileDAO.deleteAtcFile", vo);
	}

	/**
	 * 첨부파일목록
	 * @param vo
	 * @throws Exception
	 */
	public List<FileMVO> selectAttachList(AttachFileDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.file.fileDAO.selectAttachList", vo);
	}

	public void updateFile(ReqFileVO vo) throws Exception {
		getSqlSession().update("kr.maxerve.file.fileDAO.updateFile", vo);
	}

	public FileDTO selectFile(FileDTO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.file.fileDAO.selectFileList", vo);
	}

	public List<FileDTO> selectFileList(FileDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.file.fileDAO.selectFileList", vo);
	}

	public void deleteFile(FileDTO vo) throws Exception {
		getSqlSession().delete("kr.maxerve.file.fileDAO.deleteFile", vo);
	}
}
