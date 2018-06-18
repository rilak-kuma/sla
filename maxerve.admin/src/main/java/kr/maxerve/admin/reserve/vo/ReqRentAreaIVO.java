package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import kr.maxerve.dto.RentTheAreaDTO;

/**
* ReqRentAreaIVO
* @author LEEC.J
* @since 2018.06.07
* @version 1.0
* @see
*
* <pre>
* 대관일정등록
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.07     LEEC.J        최초 생성
* </pre>
*/
public class ReqRentAreaIVO extends RentTheAreaDTO {
	@NotEmpty
	private String rtaSrtDt = "";	// 대관시작일
	@NotEmpty
	private String rtaSrtTm = "";	// 대관시작시간
	@NotEmpty
	private String rtaEndDt = "";	// 대관종료일
	@NotEmpty
	private String rtaEndTm = "";	// 대관종료시간
	@NotEmpty
	private String useYn = "";		// VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '사용여부'
	@NotEmpty
	private List<String> rtaFctList = new ArrayList<>();	// 대관장소목록

	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRtaSrtDt() {
		return rtaSrtDt;
	}
	public void setRtaSrtDt(String rtaSrtDt) {
		this.rtaSrtDt = rtaSrtDt;
	}
	public String getRtaSrtTm() {
		return rtaSrtTm;
	}
	public void setRtaSrtTm(String rtaSrtTm) {
		this.rtaSrtTm = rtaSrtTm;
	}
	public String getRtaEndDt() {
		return rtaEndDt;
	}
	public void setRtaEndDt(String rtaEndDt) {
		this.rtaEndDt = rtaEndDt;
	}
	public String getRtaEndTm() {
		return rtaEndTm;
	}
	public void setRtaEndTm(String rtaEndTm) {
		this.rtaEndTm = rtaEndTm;
	}
	public List<String> getRtaFctList() {
		return rtaFctList;
	}
	public void setRtaFctList(List<String> rtaFctList) {
		this.rtaFctList = rtaFctList;
	}
}
