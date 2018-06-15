package kr.maxerve.dto;

public class TourAttendDTO {
	private String tourAplyIdx = "";		// int(11) NOT NULL COMMENT '투어 신청 인덱스'
	private String ord = "";				// int(11) NOT NULL COMMENT '순서'
	private String attNm = "";			// varchar(50) NOT NULL COMMENT '참석자명'
	private String oztnNm = "";			// varchar(50) NOT NULL COMMENT '단체명'
	private String phnNmbr = "";	// VARCHAR(50) NOT NULL COMMENT '휴대폰번호',
	private String emil = "";		// VARCHAR(200) NOT NULL COMMENT '이메일',

	public String getTourAplyIdx() {
		return tourAplyIdx;
	}
	public void setTourAplyIdx(String tourAplyIdx) {
		this.tourAplyIdx = tourAplyIdx;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getAttNm() {
		return attNm;
	}
	public void setAttNm(String attNm) {
		this.attNm = attNm;
	}
	public String getOztnNm() {
		return oztnNm;
	}
	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	public String getPhnNmbr() {
		return phnNmbr;
	}
	public void setPhnNmbr(String phnNmbr) {
		this.phnNmbr = phnNmbr;
	}
	public String getEmil() {
		return emil;
	}
	public void setEmil(String emil) {
		this.emil = emil;
	}
}
