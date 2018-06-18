package kr.maxerve.admin.sms.dao;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.SmsDTO;

/**
* SmsDAO
* @author LEEC.J
* @since 2018.06.14
* @version 1.0
* @see
*
* <pre>
* SMS
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.14     LEEC.J        최초 생성
* </pre>
*/
@Repository("smsDAO")
public class SmsDAO extends BaseDAOSupport {
	/**
	 * 문자발송
	 * @param vo
	 * @throws Exception
	 */
	public void insert(SmsDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.sms.smsDAO.insert", vo);
	}
}
