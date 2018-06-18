package kr.maxerve.admin.facilities.vo;

import java.util.ArrayList;
import java.util.List;

import kr.maxerve.dto.FacilitiesFloorDTO;
import kr.maxerve.dto.FacilitiesInfoDTO;

public class FloorRoomMVO extends FacilitiesFloorDTO{
	List<FacilitiesInfoDTO> roomList = new ArrayList<>(); // 호실 목록

	public List<FacilitiesInfoDTO> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<FacilitiesInfoDTO> roomList) {
		this.roomList = roomList;
	}
	
}
