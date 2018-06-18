package kr.maxerve.admin.information.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.information.dao.NewsletterDAO;
import kr.maxerve.admin.information.service.NewsletterService;
import kr.maxerve.admin.information.vo.NewsLetterMVO;
import kr.maxerve.admin.information.vo.ReqNewsLetterVO;
import kr.maxerve.admin.information.vo.ReqSubscriberVO;
import kr.maxerve.admin.information.vo.SubscriberMVO;
import kr.maxerve.dto.NewsLetterDTO;

@Service("newsletterService")
public class NewsletterServiceImpl implements NewsletterService {

	@Resource(name="newsletterDAO")
	NewsletterDAO newsletterDAO;
	
	@Resource(name="propertiesService")
	Properties propertiesService;

	@Override
	public List<NewsLetterMVO> selectNewsletterList(ReqNewsLetterVO vo) throws Exception {
		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(newsletterDAO.selectNewsletterListCnt(vo));									// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return newsletterDAO.selectNewsletterList(vo);
	}

	@Override
	public NewsLetterDTO selectNewsletterInfo(String newsLttrIdx) throws Exception {
		return newsletterDAO.selectNewsletterInfo(newsLttrIdx);
	}

	@Override
	public void insertNewsletter(NewsLetterDTO vo) throws Exception {
		newsletterDAO.insertNewsletter(vo);
	}

	@Override
	public void deleteNewsletter(NewsLetterDTO vo) throws Exception {
		newsletterDAO.deleteNewsletter(vo);
	}
	
	@Override
	public List<SubscriberMVO> selectSubscriberList(ReqSubscriberVO vo) throws Exception {
		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(newsletterDAO.selectSubscriberListCnt(vo));									// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return newsletterDAO.selectSubscriberList(vo);
	}

	@Override
	public SubscriberMVO selectSCBCnt() throws Exception {
		return newsletterDAO.selectSCBCnt();
	}

	@Override
	public List<SubscriberMVO> selectSubscriberListExcel(ReqSubscriberVO vo) throws Exception {
		return newsletterDAO.selectSubscriberListExcel(vo);
	}
}
