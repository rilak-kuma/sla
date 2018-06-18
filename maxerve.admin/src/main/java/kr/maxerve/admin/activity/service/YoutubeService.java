package kr.maxerve.admin.activity.service;

import java.util.List;

import kr.maxerve.dto.ReferenceRoomYoutubeDTO;

public interface YoutubeService {

	List<ReferenceRoomYoutubeDTO> selectYutbList(String refRoomIdx) throws Exception;
}
