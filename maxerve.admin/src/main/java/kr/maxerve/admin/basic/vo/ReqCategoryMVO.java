package kr.maxerve.admin.basic.vo;

import kr.maxerve.dto.CategoryDTO;

/**
* ReqCategoryDVO
* @author LEEC.J
* @since 2018.06.23
* @version 1.0
* @see
*
* <pre>
* 카테고리삭제
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.23     LEEC.J        최초 생성
* 2017.01.13     정재훈        대상카테고리 변수명 오타수정
* </pre>
*/
public class ReqCategoryMVO extends CategoryDTO {
	String tCtgrIdx = "";		// 대상카테고리
	String tCtgrOrd = "";		// 대상순서

	public String gettCtgrIdx() {
		return tCtgrIdx;
	}
	public void settCtgrIdx(String tCgtrIdx) {
		this.tCtgrIdx = tCgtrIdx;
	}
	public String gettCtgrOrd() {
		return tCtgrOrd;
	}
	public void settCtgrOrd(String tCtgrOrd) {
		this.tCtgrOrd = tCtgrOrd;
	}
}
