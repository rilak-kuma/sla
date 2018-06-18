package kr.maxerve.admin.payment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.adjust.vo.ReqPaymentVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.admin.payment.vo.PaymentMVO;

/**
* PaymentDAO
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
@Repository("paymentDAO")
public class PaymentDAO extends BaseDAOSupport {
	/**
	 * 결제수
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int selectCount(ReqPaymentVO vo) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.payment.paymentDAO.selectCount", vo);
	}

	/**
	 * 결제목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<PaymentMVO> selectList(ReqPaymentVO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.payment.paymentDAO.selectList", vo);
	}

	/**
	 * 결제상세
	 * @param payIdx
	 * @return
	 * @throws Exception
	 */
	public PaymentMVO selectInfo(String payIdx) throws Exception {
		return getSqlSession().selectOne("kr.maxerve.admin.payment.paymentDAO.selectInfo", payIdx);
	}
}
