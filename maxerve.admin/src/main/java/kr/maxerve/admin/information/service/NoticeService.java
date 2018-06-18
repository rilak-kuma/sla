package kr.maxerve.admin.information.service;

import java.util.List;

import kr.maxerve.admin.information.vo.NoticeIVO;
import kr.maxerve.admin.information.vo.NoticeMVO;
import kr.maxerve.admin.information.vo.NoticeUVO;
import kr.maxerve.admin.information.vo.ReqNoticeVO;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.NoticeDTO;

public interface NoticeService {

	List<NoticeMVO> selectNoticelist(ReqNoticeVO vo) throws Exception;

	void insertNotice(NoticeIVO vo, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception;

	NoticeMVO selectNoticeInfo(String ntcIdx) throws Exception;

	void updateNotice(NoticeUVO vo, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception;

	void deleteNotice(NoticeDTO vo) throws Exception;

}
