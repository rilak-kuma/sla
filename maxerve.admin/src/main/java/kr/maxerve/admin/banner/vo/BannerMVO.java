package kr.maxerve.admin.banner.vo;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.dto.BannerDTO;

public class BannerMVO extends BannerDTO {
	
	private String rownum;				//rownum
	private String useYnNm;				//사용중여부명
	private String plfCdNm;				//플랫폼명
	private String dep1;				//공통코드 dep1
	
	private String pageIndex;			//페이지 index
	private PaginationInfo pageInfo;	//페이지정보
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getPlfCdNm() {
		return plfCdNm;
	}
	public void setPlfCdNm(String plfCdNm) {
		this.plfCdNm = plfCdNm;
	}
	public String getDep1() {
		return dep1;
	}
	public void setDep1(String dep1) {
		this.dep1 = dep1;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public PaginationInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PaginationInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
