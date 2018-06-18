package kr.maxerve.admin.banner.vo;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;

import kr.maxerve.dto.BannerDTO;

public class BannerIVO extends BannerDTO {
	
	@NotEmpty
	private String plfCd; 			//varchar(50) NOT NULL COMMENT '플랫폼 공통코드(027)',
	@NotEmpty
	private String titl; 			//varchar(50) NOT NULL COMMENT '제목',
	@NotEmpty
	private String info; 			//varchar(200) NOT NULL COMMENT '정보',
	@NotEmpty
	private String filePath; 		//varchar(200) NOT NULL COMMENT '배너파일',
	@NotEmpty
	private String ord; 			//int(11) NOT NULL COMMENT '순서',
	@NotEmpty
	private String useYn; 			//varchar(1) NOT NULL DEFAULT 'Y' COMMENT '전시여부',
	@NotEmpty
	private String useSrtDt; 		//varchar(10) NOT NULL COMMENT '전시시작일',
	@NotEmpty
	private String useEndDt; 		//varchar(10) NOT NULL COMMENT '전시종료일',
	
	public String getPlfCd() {
		return plfCd;
	}
	public void setPlfCd(String plfCd) {
		this.plfCd = plfCd;
	}
	public String getTitl() {
		return titl;
	}
	public void setTitl(String titl) {
		this.titl = titl;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getUseSrtDt() {
		return useSrtDt;
	}
	public void setUseSrtDt(String useSrtDt) {
		this.useSrtDt = useSrtDt;
	}
	public String getUseEndDt() {
		return useEndDt;
	}
	public void setUseEndDt(String useEndDt) {
		this.useEndDt = useEndDt;
	}

}
