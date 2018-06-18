package kr.maxerve.admin.adjust.service;

import java.util.List;

import kr.maxerve.admin.adjust.vo.ReqSponsorAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqSponsorVO;
import kr.maxerve.admin.adjust.vo.SponsorMVO;

public interface SponsorService {

	/**
	 * 후원현황목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SponsorMVO> selectList(ReqSponsorVO vo) throws Exception;

	/**
	 * 후원현황수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(ReqSponsorVO vo) throws Exception;

	/**
	 * 후원금정산수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectAdjustCount(ReqSponsorAdjustVO vo) throws Exception;

	/**
	 * 후원금정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<SponsorMVO> selectAdjustList(ReqSponsorAdjustVO vo) throws Exception;
}
