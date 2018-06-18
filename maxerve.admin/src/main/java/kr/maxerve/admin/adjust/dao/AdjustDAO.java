package kr.maxerve.admin.adjust.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.adjust.vo.AdjustHistoryMVO;
import kr.maxerve.admin.adjust.vo.AdjustOrganizationMVO;
import kr.maxerve.admin.adjust.vo.AdjustSummaryMVO;
import kr.maxerve.admin.adjust.vo.PaymentSummaryMVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.AdjustHistoryDTO;
import kr.maxerve.dto.AdjustOrganizationDTO;
import kr.maxerve.dto.PayFeesDTO;

/**
* AdjustController
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 정산
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Repository("adjustDAO")
public class AdjustDAO extends BaseDAOSupport {
	/**
	 * 정산단체목록
	 * @return
	 * @throws Exception
	 */
	public List<AdjustOrganizationMVO> selectOrganizationList() throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.adjustDAO.selectOrganizationList");
	}

	/**
	 * 정산단체등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertOrganization(AdjustOrganizationDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.adjust.adjustDAO.insertOrganization", vo);
	}

	/**
	 * 수수료
	 * @param payMthd
	 * @return
	 * @throws Exception
	 */
	public String selectFees(String payMthd) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.adjustDAO.selectFees", payMthd);
	}

	/**
	 * 수수료등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertFees(PayFeesDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.adjust.adjustDAO.insertFees", vo);
	}

	/**
	 * 정산목록수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectSummaryCount(ReqAdjustVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.adjustDAO.selectSummaryCount", vo);
	}

	/**
	 * 정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustSummaryMVO> selectSummaryList(ReqAdjustVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.adjustDAO.selectSummaryList", vo);
	}

	/**
	 * 정산결제요약
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public PaymentSummaryMVO selectPaymentSummary(ReqAdjustVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.adjustDAO.selectPaymentSummary", vo);
	}

	/**
	 * 단체별정산목록수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectSummaryOrganizationCount(ReqOrganizationSummaryVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.adjustDAO.selectSummaryOrganizationCount", vo);
	}

	/**
	 * 단체별정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustSummaryMVO> selectSummaryOrganizationList(ReqOrganizationSummaryVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.adjustDAO.selectSummaryOrganizationList", vo);
	}

	/**
	 * 단체별정산수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectHistoryCount(ReqAdjustHistoryVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.adjust.adjustDAO.selectHistoryCount", vo);
	}

	/**
	 * 단체별정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustHistoryMVO> selectHistoryList(ReqAdjustHistoryVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.adjust.adjustDAO.selectHistoryList", vo);
	}

	/**
	 * 정산완료등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertHistory(AdjustHistoryDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.adjust.adjustDAO.insertHistory", vo);
	}
}
