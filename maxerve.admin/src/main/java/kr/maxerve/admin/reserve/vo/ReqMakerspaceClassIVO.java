package kr.maxerve.admin.reserve.vo;

import java.util.ArrayList;
import java.util.List;

import kr.maxerve.dto.MakerspaceClassCurriculumDTO;
import kr.maxerve.dto.MakerspaceClassDTO;
import kr.maxerve.dto.MakerspaceClassGroupDTO;

public class ReqMakerspaceClassIVO extends MakerspaceClassDTO {
	List<MakerspaceClassCurriculumDTO> curriculumList = new ArrayList<>(); // 교육과정 목록
	List<MakerspaceClassGroupDTO> groupList = new ArrayList<>(); // 반 목록

	public List<MakerspaceClassCurriculumDTO> getCurriculumList() {
		return curriculumList;
	}
	public void setCurriculumList(List<MakerspaceClassCurriculumDTO> curriculumList) {
		this.curriculumList = curriculumList;
	}
	public List<MakerspaceClassGroupDTO> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<MakerspaceClassGroupDTO> groupList) {
		this.groupList = groupList;
	}
}
