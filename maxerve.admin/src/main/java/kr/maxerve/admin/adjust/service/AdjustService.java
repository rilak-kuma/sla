package kr.maxerve.admin.adjust.service;

import java.util.List;

import kr.maxerve.admin.adjust.vo.AdjustHistoryMVO;
import kr.maxerve.admin.adjust.vo.AdjustOrganizationMVO;
import kr.maxerve.admin.adjust.vo.AdjustSummaryMVO;
import kr.maxerve.admin.adjust.vo.PaymentSummaryMVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO;
import kr.maxerve.dto.AdjustHistoryDTO;
import kr.maxerve.dto.AdjustOrganizationDTO;
import kr.maxerve.dto.PayFeesDTO;

/**
* AdjustService
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
public interface AdjustService {
	/**
	 * 정산업체 목록
	 * @return
	 * @throws Exception
	 */
	public List<AdjustOrganizationMVO> selectOrganizationList() throws Exception;

	/**
	 * 정산단체등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertOrganization(List<AdjustOrganizationDTO> vo) throws Exception;

	/**
	 * 수수료
	 * @param payMthd
	 * @return
	 * @throws Exception
	 */
	public String selectPayFees(String payMthd) throws Exception;

	/**
	 * 수수료등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertPayFees(List<PayFeesDTO> vo) throws Exception;

	/**
	 * 정산목록수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectSummaryCount(ReqAdjustVO vo) throws Exception;

	/**
	 * 정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustSummaryMVO> selectSummaryList(ReqAdjustVO vo) throws Exception;

	/**
	 * 정산결제요약
	 * @param dt
	 * @return
	 * @throws Exception
	 */
	public PaymentSummaryMVO selectPaymentSummary(String dt) throws Exception;

	/**
	 * 단체별정산목록수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectSummaryOrganizationCount(ReqOrganizationSummaryVO vo) throws Exception;

	/**
	 * 단체별정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustSummaryMVO> selectSummaryOrganizationList(ReqOrganizationSummaryVO vo) throws Exception;

	/**
	 * 단체별정산수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectHistoryCount(ReqAdjustHistoryVO vo) throws Exception;

	/**
	 * 단체별정산목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<AdjustHistoryMVO> selectHistoryList(ReqAdjustHistoryVO vo) throws Exception;

	/**
	 * 정산완료등록
	 * @param vo
	 * @throws Exception
	 */
	public void insertHistory(AdjustHistoryDTO vo) throws Exception;
}
