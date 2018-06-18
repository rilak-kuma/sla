package kr.maxerve.admin.member.vo;

import kr.maxerve.dto.MemberDTO;
/**
 * 온라인멤버관리 조회
 * extends MemberDTO
 */
public class MemberMVO extends MemberDTO {

	private String grpCnt;				//입주단체수
	private String mvinGrpCdNm;			//입주그룹명
	private String ftePsct;				//입주단체 상근인원 합

	private String mvinAplyIdx;			//입주신청인덱스
	private String creDttmOne;			//가입일 조회조건1
	private String creDttmTwo;			//가입일 조회조건2

	private String mbrTypCdNm;			//혁신멤버 구분 공통코드명(015)
	private String sclTypCdNm;			//소셜 종류 공통코드(006)
	private String locTypCdNm;			//위치정보종류 공통코드(016)
	private String invrMbrCnt;			//등록직원수
	private String mbrCtgrCdNm;			//업종 카테고리명
	private String olineAllMbrCnt;		//온라인멤버 전체 수
	private String mbrTypeCnt;			//혁신멤버별 멤버수
	private String mvinGrpCd;			//입주그룹 공통코드

	private String assOztnNm;		// 소속단체

	private String newsLttrYn;		// 뉴스레터수신여부
	public String getGrpCnt() {
		return grpCnt;
	}
	public void setGrpCnt(String grpCnt) {
		this.grpCnt = grpCnt;
	}
	public String getMvinGrpCdNm() {
		return mvinGrpCdNm;
	}
	public void setMvinGrpCdNm(String mvinGrpCdNm) {
		this.mvinGrpCdNm = mvinGrpCdNm;
	}
	public String getFtePsct() {
		return ftePsct;
	}
	public void setFtePsct(String ftePsct) {
		this.ftePsct = ftePsct;
	}
	public String getMvinAplyIdx() {
		return mvinAplyIdx;
	}
	public void setMvinAplyIdx(String mvinAplyIdx) {
		this.mvinAplyIdx = mvinAplyIdx;
	}
	public String getCreDttmOne() {
		return creDttmOne;
	}
	public void setCreDttmOne(String creDttmOne) {
		this.creDttmOne = creDttmOne;
	}
	public String getCreDttmTwo() {
		return creDttmTwo;
	}
	public void setCreDttmTwo(String creDttmTwo) {
		this.creDttmTwo = creDttmTwo;
	}
	public String getMbrTypCdNm() {
		return mbrTypCdNm;
	}
	public void setMbrTypCdNm(String mbrTypCdNm) {
		this.mbrTypCdNm = mbrTypCdNm;
	}
	public String getSclTypCdNm() {
		return sclTypCdNm;
	}
	public void setSclTypCdNm(String sclTypCdNm) {
		this.sclTypCdNm = sclTypCdNm;
	}
	public String getLocTypCdNm() {
		return locTypCdNm;
	}
	public void setLocTypCdNm(String locTypCdNm) {
		this.locTypCdNm = locTypCdNm;
	}
	public String getInvrMbrCnt() {
		return invrMbrCnt;
	}
	public void setInvrMbrCnt(String invrMbrCnt) {
		this.invrMbrCnt = invrMbrCnt;
	}
	public String getMbrCtgrCdNm() {
		return mbrCtgrCdNm;
	}
	public void setMbrCtgrCdNm(String mbrCtgrCdNm) {
		this.mbrCtgrCdNm = mbrCtgrCdNm;
	}
	public String getOlineAllMbrCnt() {
		return olineAllMbrCnt;
	}
	public void setOlineAllMbrCnt(String olineAllMbrCnt) {
		this.olineAllMbrCnt = olineAllMbrCnt;
	}
	public String getMbrTypeCnt() {
		return mbrTypeCnt;
	}
	public void setMbrTypeCnt(String mbrTypeCnt) {
		this.mbrTypeCnt = mbrTypeCnt;
	}
	public String getMvinGrpCd() {
		return mvinGrpCd;
	}
	public void setMvinGrpCd(String mvinGrpCd) {
		this.mvinGrpCd = mvinGrpCd;
	}
	public String getNewsLttrYn() {
		return newsLttrYn;
	}
	public void setNewsLttrYn(String newsLttrYn) {
		this.newsLttrYn = newsLttrYn;
	}
	public String getAssOztnNm() {
		return assOztnNm;
	}
	public void setAssOztnNm(String assOztnNm) {
		this.assOztnNm = assOztnNm;
	}
}
