package kr.maxerve.admin.mail.service;

import kr.maxerve.admin.mail.vo.MailVO;

public interface MailService {
	/**
	 * 메일발송
	 * @param from
	 * @param to
	 * @param subject
	 * @param msg
	 * @throws Exception
	 */
	public void sendMail(MailVO vo) throws Exception;
}
