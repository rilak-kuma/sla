package kr.maxerve.admin.information.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.information.vo.NoticeMVO;
import kr.maxerve.admin.information.vo.ReqNoticeVO;
import kr.maxerve.dto.NoticeDTO;

@Repository("noticeDAO")
public class NoticeDAO extends BaseDAOSupport {

	public List<NoticeMVO> selectNoticelist(ReqNoticeVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.information.noticeDAO.selectNoticelist", vo);
	}

	public int selectNoticelistCnt(ReqNoticeVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.noticeDAO.selectNoticelistCnt", vo);
	}

	public void insertNotice(NoticeDTO vo) {
		getSqlSession().insert("kr.maxerve.admin.information.noticeDAO.insertNotice", vo);
	}

	public String selectNoticeIdx(NoticeDTO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.noticeDAO.selectNoticeIdx", vo);
	}

	public NoticeMVO selectNoticeInfo(String ntcIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.noticeDAO.selectNoticeInfo", ntcIdx);
	}

	public void updateNotice(NoticeDTO vo) {
		getSqlSession().update("kr.maxerve.admin.information.noticeDAO.updateNotice", vo);
	}

	public void deleteNotice(NoticeDTO vo) {
		getSqlSession().update("kr.maxerve.admin.information.noticeDAO.deleteNotice", vo);
	}
}
