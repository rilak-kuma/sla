package kr.maxerve.admin.information.service;

import java.util.List;

import kr.maxerve.admin.information.vo.NewsLetterMVO;
import kr.maxerve.admin.information.vo.ReqNewsLetterVO;
import kr.maxerve.admin.information.vo.ReqSubscriberVO;
import kr.maxerve.admin.information.vo.SubscriberMVO;
import kr.maxerve.dto.NewsLetterDTO;

public interface NewsletterService {

	List<NewsLetterMVO> selectNewsletterList(ReqNewsLetterVO vo) throws Exception;

	NewsLetterDTO selectNewsletterInfo(String newsLttrIdx) throws Exception;

	void insertNewsletter(NewsLetterDTO vo) throws Exception;
	
	void deleteNewsletter(NewsLetterDTO vo) throws Exception;
	
	List<SubscriberMVO> selectSubscriberList(ReqSubscriberVO vo) throws Exception;

	SubscriberMVO selectSCBCnt() throws Exception;

	List<SubscriberMVO> selectSubscriberListExcel(ReqSubscriberVO vo) throws Exception;
}
