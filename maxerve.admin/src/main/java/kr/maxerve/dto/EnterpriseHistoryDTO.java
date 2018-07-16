package kr.maxerve.dto;

/**
* EnterpriseHistoryDTO
* @author LEEC.J
* @since 2018.06.13
* @version 1.0
* @see
*
* <pre>
* 기업연혁
* TBL_ETPS_HSTR
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.13     LEEC.J        최초 생성
* </pre>
*/
public class EnterpriseHistoryDTO {
	private String etpsHstrIdx;		// INT(11) NOT NULL AUTO_INCREMENT COMMENT '기업연혁 인덱스'
	private String mbrIdx;			// INT(11) NOT NULL COMMENT '혁신멤버 인덱스'
	private String hstrDt;			// VARCHAR(10) NOT NULL COMMENT '날짜'
	private String hstrCntt;		// VARCHAR(300) NOT NULL COMMENT '내용'

	public String getEtpsHstrIdx() {
		return etpsHstrIdx;
	}
	public void setEtpsHstrIdx(String etpsHstrIdx) {
		this.etpsHstrIdx = etpsHstrIdx;
	}
	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getHstrDt() {
		return hstrDt;
	}
	public void setHstrDt(String hstrDt) {
		this.hstrDt = hstrDt;
	}
	public String getHstrCntt() {
		return hstrCntt;
	}
	public void setHstrCntt(String hstrCntt) {
		this.hstrCntt = hstrCntt;
	}
}
