package kr.maxerve.admin.cmmn.vo;

import java.util.ArrayList;
import java.util.List;

import kr.maxerve.dto.CommonCodeDTO;


/**
 * @author LEEC.J
 *
 * 2018. 6. 15.
 */
public class MultiCommonCodeVO extends CommonCodeDTO {
	List<CommonCodeDTO> subList = new ArrayList<CommonCodeDTO>();

	public List<CommonCodeDTO> getSubList() {
		return subList;
	}

	public void setSubList(List<CommonCodeDTO> subList) {
		this.subList = subList;
	}
}
