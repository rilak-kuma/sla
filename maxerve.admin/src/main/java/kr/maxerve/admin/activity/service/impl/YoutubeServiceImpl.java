package kr.maxerve.admin.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.activity.dao.YoutubeDAO;
import kr.maxerve.admin.activity.service.YoutubeService;
import kr.maxerve.dto.ReferenceRoomYoutubeDTO;

@Service("youtubeService")
public class YoutubeServiceImpl implements YoutubeService {

	@Resource(name="youtubeDAO")
	YoutubeDAO youtubeDAO;
	
	@Override
	public List<ReferenceRoomYoutubeDTO> selectYutbList(String refRoomIdx) throws Exception {
		return youtubeDAO.selectYutbList(refRoomIdx);
	}

}
