package kr.maxerve.dto;

public class ReferenceRoomYoutubeDTO {
	private String refRoomIdx;		// Int(11) NOT NULL COMMENT '자료실 인덱스'
	private String yutbUrl;			// varchar(200) NOT NULL COMMENT '유튜브 주소'
	private String ord;				// int(11) NOT NULL COMMENT '순서'

	public String getRefRoomIdx() {
		return refRoomIdx;
	}
	public void setRefRoomIdx(String refRoomIdx) {
		this.refRoomIdx = refRoomIdx;
	}
	public String getYutbUrl() {
		return yutbUrl;
	}
	public void setYutbUrl(String yutbUrl) {
		this.yutbUrl = yutbUrl;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
}
