package kr.maxerve.admin.adjust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.maxerve.admin.adjust.dao.AdjustDAO;
import kr.maxerve.admin.adjust.service.AdjustService;
import kr.maxerve.admin.adjust.vo.AdjustHistoryMVO;
import kr.maxerve.admin.adjust.vo.AdjustOrganizationMVO;
import kr.maxerve.admin.adjust.vo.AdjustSummaryMVO;
import kr.maxerve.admin.adjust.vo.PaymentSummaryMVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO;
import kr.maxerve.admin.adjust.vo.ReqAdjustVO;
import kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO;
import kr.maxerve.admin.utils.DateTimeUtil;
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
@Service("adjustService")
public class AdjustServiceImpl implements AdjustService {

	@Resource(name="adjustDAO")
	private AdjustDAO adjustDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectOrganizationList()
	 */
	@Override
	public List<AdjustOrganizationMVO> selectOrganizationList() throws Exception {
		return adjustDAO.selectOrganizationList();
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#insertOrganization(kr.maxerve.admin.adjust.vo.AdjustOrganizationIVO)
	 */
	@Override
	public void insertOrganization(List<AdjustOrganizationDTO> vo) throws Exception {
		for (AdjustOrganizationDTO item : vo) {
			adjustDAO.insertOrganization(item);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectPayFees(java.lang.String)
	 */
	@Override
	public String selectPayFees(String payMthd) throws Exception {
		return adjustDAO.selectFees(payMthd);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#insertPayFees(java.util.List)
	 */
	@Override
	public void insertPayFees(List<PayFeesDTO> vo) throws Exception {
		for (PayFeesDTO payFeesDTO : vo) {
			adjustDAO.insertFees(payFeesDTO);
		}
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectSummaryCount(kr.maxerve.admin.adjust.vo.ReqAdjustVO)
	 */
	@Override
	public int selectSummaryCount(ReqAdjustVO vo) throws Exception {
		return adjustDAO.selectSummaryCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectSummaryList(kr.maxerve.admin.adjust.vo.ReqAdjustVO)
	 */
	@Override
	public List<AdjustSummaryMVO> selectSummaryList(ReqAdjustVO vo) throws Exception {
		return adjustDAO.selectSummaryList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectPaymentSummary(java.lang.String)
	 */
	@Override
	public PaymentSummaryMVO selectPaymentSummary(String dt) throws Exception {
		int length = dt.length();

		ReqAdjustVO param = new ReqAdjustVO();

		switch (length) {
		case 4:
			param.setSrtDt(dt + ".01.01 00:00:00");
			param.setEndDt(dt + ".12.31 23:59:59");
			break;

		case 7:
			param.setSrtDt(dt + ".01 00:00:00");
			param.setEndDt(dt + "." + DateTimeUtil.fnLastday(Integer.valueOf(StringUtils.left(dt, 4)), Integer.valueOf(StringUtils.right(dt, 2))) + " 23:59:59");
			break;

		case 10:
			param.setSrtDt(dt + " 00:00:00");
			param.setEndDt(dt + " 23:59:59");
			break;
		}

		return adjustDAO.selectPaymentSummary(param);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectSummaryOrganizationCount(kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO)
	 */
	@Override
	public int selectSummaryOrganizationCount(ReqOrganizationSummaryVO vo) throws Exception {
		return adjustDAO.selectSummaryOrganizationCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectSummaryOrganizationList(kr.maxerve.admin.adjust.vo.ReqOrganizationSummaryVO)
	 */
	@Override
	public List<AdjustSummaryMVO> selectSummaryOrganizationList(ReqOrganizationSummaryVO vo) throws Exception {
		return adjustDAO.selectSummaryOrganizationList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectHistoryCount(kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO)
	 */
	@Override
	public int selectHistoryCount(ReqAdjustHistoryVO vo) throws Exception {
		return adjustDAO.selectHistoryCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.adjust.service.AdjustService#selectHistoryList(kr.maxerve.admin.adjust.vo.ReqAdjustHistoryVO)
	 */
	@Override
	public List<AdjustHistoryMVO> selectHistoryList(ReqAdjustHistoryVO vo) throws Exception {
		return adjustDAO.selectHistoryList(vo);
	}

	@Override
	public void insertHistory(AdjustHistoryDTO vo) throws Exception {
		adjustDAO.insertHistory(vo);
	}
}
