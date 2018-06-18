package kr.maxerve.admin.information.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.information.vo.NewsLetterMVO;
import kr.maxerve.admin.information.vo.ReqNewsLetterVO;
import kr.maxerve.admin.information.vo.ReqSubscriberVO;
import kr.maxerve.admin.information.vo.SubscriberMVO;
import kr.maxerve.dto.NewsLetterDTO;

@Repository("newsletterDAO")
public class NewsletterDAO extends BaseDAOSupport{

	public List<NewsLetterMVO> selectNewsletterList(ReqNewsLetterVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.information.NewsletterDAO.selectNewsletterList",vo);
	}

	public int selectNewsletterListCnt(ReqNewsLetterVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.NewsletterDAO.selectNewsletterListCnt",vo);
	}

	public NewsLetterDTO selectNewsletterInfo(String newsLttrIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.NewsletterDAO.selectNewsletterInfo",newsLttrIdx);
	}

	public void insertNewsletter(NewsLetterDTO vo) {
		getSqlSession().delete("kr.maxerve.admin.information.NewsletterDAO.insertNewsletter",vo);
	}

	public void deleteNewsletter(NewsLetterDTO vo) {
		getSqlSession().delete("kr.maxerve.admin.information.NewsletterDAO.deleteNewsletter",vo);
	}

	public List<SubscriberMVO> selectSubscriberList(ReqSubscriberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.information.NewsletterDAO.selectSubscriberList",vo);
	}

	public int selectSubscriberListCnt(ReqSubscriberVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.information.NewsletterDAO.selectSubscriberListCnt",vo);
	}

	public SubscriberMVO selectSCBCnt() {
		return getSqlSession().selectOne("kr.maxerve.admin.information.NewsletterDAO.selectSCBCnt");
	}
	
	public List<SubscriberMVO> selectSubscriberListExcel(ReqSubscriberVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.information.NewsletterDAO.selectSubscriberListExcel",vo);
	}
}
