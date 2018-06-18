package kr.maxerve.admin.sms.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.maxerve.admin.basic.service.SimpleResourceService;
import kr.maxerve.admin.sms.dao.SmsDAO;
import kr.maxerve.admin.sms.service.SmsService;
import kr.maxerve.dto.SimpleResourceDTO;
import kr.maxerve.dto.SmsDTO;

/**
* SmsServiceImpl
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
@Service("smsService")
public class SmsServiceImpl implements SmsService {

	@Resource(name="smsDAO")
	private SmsDAO smsDAO;

	@Resource(name="simpleResourceService")
	private SimpleResourceService simpleResourceService;

	@Value("#{propertiesService['sms.callback.number']}")
	private String smsCallbackNumber;

	/* (non-Javadoc)
	 * @see kr.maxerve.sms.service.SmsService#sendReserve(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendReserve(String siplRscTypCd, String pgrCd, String tran_phone) throws Exception {
		SimpleResourceDTO param = new SimpleResourceDTO();
		param.setSiplRscTypCd(siplRscTypCd);
		param.setPgrCd(pgrCd);

		String tran_msg = simpleResourceService.selectInfo(param);

		if (StringUtils.isEmpty(tran_msg)) {
			tran_msg = "";
		}

		SmsDTO param1 = new SmsDTO();

		param1.setTran_phone(tran_phone);
		param1.setTran_msg(tran_msg);
		param1.setTran_type("4");

		send(param1);
	}

	/* (non-Javadoc)
	 * @see kr.maxerve.sms.service.SmsService#send(kr.maxerve.dto.SmsDTO)
	 */
	@Override
	public void send(SmsDTO vo) throws Exception {
		vo.setTran_callback(smsCallbackNumber);

		smsDAO.insert(vo);
	}

}
