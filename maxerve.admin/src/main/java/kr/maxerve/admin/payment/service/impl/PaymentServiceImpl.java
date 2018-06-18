package kr.maxerve.admin.payment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.adjust.vo.ReqPaymentVO;
import kr.maxerve.admin.payment.dao.PaymentDAO;
import kr.maxerve.admin.payment.service.PaymentService;
import kr.maxerve.admin.payment.vo.PaymentMVO;

/**
* PaymentServiceImpl
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* 결제
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	@Resource(name="paymentDAO")
	private PaymentDAO paymentDAO;

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.payment.service.PaymentService#selectCount(kr.maxerve.admin.adjust.vo.ReqPaymentVO)
	 */
	@Override
	public int selectCount(ReqPaymentVO vo) throws Exception {
		return paymentDAO.selectCount(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.payment.service.PaymentService#selectList(kr.maxerve.admin.adjust.vo.ReqPaymentVO)
	 */
	@Override
	public List<PaymentMVO> selectList(ReqPaymentVO vo) throws Exception {
		return paymentDAO.selectList(vo);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.admin.payment.service.PaymentService#selectInfo(java.lang.String)
	 */
	@Override
	public PaymentMVO selectInfo(String payIdx) throws Exception {
		return paymentDAO.selectInfo(payIdx);
	}

}
