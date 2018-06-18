package kr.maxerve.admin.adjust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.adjust.dao.SponsorDAO;
import kr.maxerve.admin.adjust.service.SponsorService;
import kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqSponsorVO;
import kr.maxerve.admin.adjust.vo.SponsorMVO;

/**
* SponsorServiceImpl
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 후원
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Service("sponsorService")
public class SponsorServiceImpl implements SponsorService {

	@Resource(name="sponsorDAO")
	SponsorDAO sponsorDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.SponsorService#selectList(kr.maxerve.admin.adjust.vo.ReqSponsorVO)
	 */
	@Override
	public List<SponsorMVO> selectList(ReqSponsorVO vo) throws Exception {
		return sponsorDAO.selectList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.SponsorService#selectCount(kr.maxerve.admin.adjust.vo.ReqSponsorVO)
	 */
	@Override
	public int selectCount(ReqSponsorVO vo) throws Exception {
		return sponsorDAO.selectCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.SponsorService#selectAdjustCount(kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO)
	 */
	@Override
	public int selectAdjustCount(ReqSponsorAdjustVO vo) throws Exception {
		return sponsorDAO.selectAdjustCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.SponsorService#selectAdjustList(kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO)
	 */
	@Override
	public List<SponsorMVO> selectAdjustList(ReqSponsorAdjustVO vo) throws Exception {
		return sponsorDAO.selectAdjustList(vo);
	}



}
