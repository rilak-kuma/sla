package kr.maxerve.dto;

/**
* ChannelDTO
* @author LEEC.J
* 2018.06.22
* @version 1.0
* @see
*
* <pre>
* 회원채널
* TBL_MBR_CNL
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.22     LEEC.J        최초 생성
* </pre>
*/
public class ChannelDTO {
	private String mbrIdx;			// int(11) NOT NULL COMMENT '회원인덱스'
	private String cnlTypCd;		// varchar(50) NOT NULL COMMENT '채널구분 공통코드'
	private String cnlUrl;			// varchar(50) NOT NULL COMMENT '채널주소'

	public String getMbrIdx() {
		return mbrIdx;
	}
	public void setMbrIdx(String mbrIdx) {
		this.mbrIdx = mbrIdx;
	}
	public String getCnlTypCd() {
		return cnlTypCd;
	}
	public void setCnlTypCd(String cnlTypCd) {
		this.cnlTypCd = cnlTypCd;
	}
	public String getCnlUrl() {
		return cnlUrl;
	}
	public void setCnlUrl(String cnlUrl) {
		this.cnlUrl = cnlUrl;
	}
}
