package kr.maxerve.dto;

/**
* SimpleResourceDTO
* @author LEEC.J
* @since 2018.06.12
* @version 1.0
* @see
*
* <pre>
* 단순리소스
* TBL_SIPL_RSC
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.12     LEEC.J        최초 생성
* </pre>
*/
public class SimpleResourceDTO {
	private String siplRscIdx;		// VARCHAR(50) NOT NULL COMMENT '단순리소스 인덱스'
	private String siplRscTypCd;	// VARCHAR(50) NOT NULL COMMENT '단순리소스 종류 공통코드(024)'
	private String pgrCd;			// VARCHAR(50) NOT NULL COMMENT '진행상태 공통코드(020)'
	private String siplRscCntt;		// VARCHAR(300) NOT NULL COMMENT '내용'

	public String getSiplRscIdx() {
		return siplRscIdx;
	}
	public void setSiplRscIdx(String siplRscIdx) {
		this.siplRscIdx = siplRscIdx;
	}
	public String getSiplRscTypCd() {
		return siplRscTypCd;
	}
	public void setSiplRscTypCd(String siplRscTypCd) {
		this.siplRscTypCd = siplRscTypCd;
	}
	public String getPgrCd() {
		return pgrCd;
	}
	public String getSiplRscCntt() {
		return siplRscCntt;
	}
	public void setSiplRscCntt(String siplRscCntt) {
		this.siplRscCntt = siplRscCntt;
	}
	public void setPgrCd(String pgrCd) {
		this.pgrCd = pgrCd;
	}
}
