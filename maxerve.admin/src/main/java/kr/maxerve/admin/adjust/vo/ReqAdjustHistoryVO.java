package kr.maxerve.admin.adjust.vo;

import kr.maxerve.dto.AdjustHistoryDTO;

/**
* ReqAdjustHistoryVO
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
public class ReqAdjustHistoryVO extends AdjustHistoryDTO {
	private int countPerPage;
	private int limitIndex;
	private String srtDt = "";		// 시작일
	private String endDt = "";		// 종료일
	private String oztnNm = "";		// 단체명
	private String ajmYn = "";		// 정산여부

	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	public int getLimitIndex() {
		return limitIndex;
	}
	public void setLimitIndex(int limitIndex) {
		this.limitIndex = limitIndex;
	}
	public String getSrtDt() {
		return srtDt;
	}
	public void setSrtDt(String srtDt) {
		this.srtDt = srtDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getAjmYn() {
		return ajmYn;
	}
	public void setAjmYn(String ajmYn) {
		this.ajmYn = ajmYn;
	}
}
