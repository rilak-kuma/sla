package kr.maxerve.admin.banner.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.banner.dao.BannerDAO;
import kr.maxerve.admin.banner.service.BannerService;
import kr.maxerve.admin.banner.vo.BannerIVO;
import kr.maxerve.admin.banner.vo.BannerMVO;
import kr.maxerve.admin.banner.vo.BannerUVO;
import kr.maxerve.admin.banner.vo.ReqBannerVO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.dto.BannerDTO;

@Service("bannerService")
public class BannerServiceImpl implements BannerService{

	@Resource(name="bannerDAO")
	BannerDAO bannerDAO;
	
	@Resource(name="propertiesService")
	private Properties propertiesService;
	
	@Resource(name="fileService")
	private FileService fileService;
	
	@Override
	public void insertBanner(BannerIVO vo) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getFilePath())){
			String moveFile = fileService.moveFile(vo.getFilePath());
			vo.setFilePath(moveFile);
			vo.setBnnrRscLocCd(CommonCodeUtil.COMMON_CODE_EMPTY);
			vo.setBnnrRscLocIdx(NumberUtils.INTEGER_ZERO.toString());
		}
		BannerDTO dto = vo;
		bannerDAO.insertBanner(dto);
	}
	
	@Override
	public List<BannerMVO> selectBannerList(ReqBannerVO vo)  throws Exception {
		
		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(bannerDAO.selectBannerListCnt(vo));										// 전체 데이터 수
		vo.setPageInfo(pageInfo);
		
		return bannerDAO.selectBannerList(vo);
	}

	@Override
	public List<BannerMVO> selectUseBannerList(ReqBannerVO vo) throws Exception {
		return bannerDAO.selectUseBannerList(vo);
	}
	
	@Override
	public BannerMVO selectBannerInfo(String bnnrIdx) throws Exception {
		return bannerDAO.selectBannerInfo(bnnrIdx);
	}
	
	@Override
	public void updateBanner(BannerUVO vo) throws Exception {
		
		if(StringUtils.isNotEmpty(vo.getFilePath())){
			String moveFile = fileService.moveFile(vo.getFilePath());
			vo.setFilePath(moveFile);
			vo.setBnnrRscLocCd(CommonCodeUtil.COMMON_CODE_EMPTY);
			vo.setBnnrRscLocIdx(NumberUtils.INTEGER_ZERO.toString());
		}
		BannerDTO dto = vo;
		bannerDAO.updateBanner(dto);
	}
	
	@Override
	public List<BannerMVO> selectOrdList(ReqBannerVO vo) throws Exception {
		return bannerDAO.selectOrdList(vo);
	}

	@Override
	public void deleteBanner(String bnnrIdx) throws Exception {
		bannerDAO.deleteBanner(bnnrIdx);
	}
}
