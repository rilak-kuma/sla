package kr.maxerve.admin.banner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.banner.vo.BannerMVO;
import kr.maxerve.admin.banner.vo.ReqBannerVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.BannerDTO;

@Repository("bannerDAO")
public class BannerDAO extends BaseDAOSupport {

	public void insertBanner(BannerDTO vo) {
		getSqlSession().insert("kr.maxerve.admin.banner.bannerDAO.insertBanner", vo);
	}
	
	public List<BannerMVO> selectBannerList(ReqBannerVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.banner.bannerDAO.selectBannerList", vo);
	}
	
	public int selectBannerListCnt(ReqBannerVO vo) {
		return getSqlSession().selectOne("kr.maxerve.admin.banner.bannerDAO.selectBannerListCnt", vo);
	}

	public List<BannerMVO> selectUseBannerList(ReqBannerVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.banner.bannerDAO.selectUseBannerList", vo);
	}
	
	public BannerMVO selectBannerInfo(String bnnrIdx) {
		return getSqlSession().selectOne("kr.maxerve.admin.banner.bannerDAO.selectBannerInfo", bnnrIdx);
	}

	public void updateBanner(BannerDTO vo) {
		getSqlSession().update("kr.maxerve.admin.banner.bannerDAO.updateBanner", vo);
	}
	
	public List<BannerMVO> selectOrdList(ReqBannerVO vo) {
		return getSqlSession().selectList("kr.maxerve.admin.banner.bannerDAO.selectOrdList",vo);
	}

	public void deleteBanner(String bnnrIdx) {
		getSqlSession().update("kr.maxerve.admin.banner.bannerDAO.deleteBanner", bnnrIdx);
	}
}
