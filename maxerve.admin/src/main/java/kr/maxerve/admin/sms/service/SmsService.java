package kr.maxerve.admin.sms.service;

import kr.maxerve.dto.SmsDTO;

/**
* SmsService
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
public interface SmsService {
	/**
	 * 예약관련 SMS 발송
	 * @param siplRscTypCd
	 * @param pgrCd
	 * @throws Exception
	 */
	public void sendReserve(String siplRscTypCd, String pgrCd, String tran_phone) throws Exception;

	/**
	 * SMS발송
	 * @param vo
	 * @throws Exception
	 */
	public void send(SmsDTO vo) throws Exception;
}
