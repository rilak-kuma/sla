package kr.maxerve.admin.adjust.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqSponsorVO;
import kr.maxerve.admin.adjust.vo.SponsorMVO;
import kr.maxerve.admin.framework.BaseDAOSupport;

@Repository("sponsorDAO")
public class SponsorDAO extends BaseDAOSupport{

	/**
	 * 후원현황목록
	 * @param vo
	 * @return
	 */
	public List<SponsorMVO> selectList(ReqSponsorVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.SponsorDAO.selectList", vo);
	}

	/**
	 * 후원현황수
	 * @param vo
	 * @return
	 */
	public int selectCount(ReqSponsorVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.SponsorDAO.selectCount", vo);
	}

	/**
	 * 후원금정산수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectAdjustCount(ReqSponsorAdjustVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.SponsorDAO.selectAdjustCount", vo);
	}

	/**
	 * 후원금정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SponsorMVO> selectAdjustList(ReqSponsorAdjustVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.SponsorDAO.selectAdjustList", vo);
	}
}
