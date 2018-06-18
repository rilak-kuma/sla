package kr.maxerve.admin.comment.vo;

import kr.maxerve.dto.CommentDTO;

public class CommentVO extends CommentDTO{
	
	private String oztnNm;		//단체명
	private String likeCnt;		//좋아요 수
	private String likeLocCd;	//좋아요 소재지 공통코드

	public String getOztnNm() {
		return oztnNm;
	}

	public void setOztnNm(String oztnNm) {
		this.oztnNm = oztnNm;
	}
	
	public String getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(String likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getLikeLocCd() {
		return likeLocCd;
	}

	public void setLikeLocCd(String likeLocCd) {
		this.likeLocCd = likeLocCd;
	}
}
