package kr.maxerve.admin.banner.service;

import java.util.List;

import kr.maxerve.admin.banner.vo.BannerIVO;
import kr.maxerve.admin.banner.vo.BannerMVO;
import kr.maxerve.admin.banner.vo.BannerUVO;
import kr.maxerve.admin.banner.vo.ReqBannerVO;

public interface BannerService {

	public void insertBanner(BannerIVO vo) throws Exception;
	
	public List<BannerMVO> selectBannerList(ReqBannerVO vo) throws Exception;
	
	public List<BannerMVO> selectUseBannerList(ReqBannerVO vo) throws Exception;

	public BannerMVO selectBannerInfo(String bnnrIdx) throws Exception;

	public void updateBanner(BannerUVO vo) throws Exception;
	
	public List<BannerMVO> selectOrdList(ReqBannerVO vo) throws Exception;

	public void deleteBanner(String bnnrIdx) throws Exception;
}
