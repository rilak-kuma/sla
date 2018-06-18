package kr.maxerve.admin.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.ReferenceRoomYoutubeDTO;

@Repository("youtubeDAO")
public class YoutubeDAO extends BaseDAOSupport{

	public List<ReferenceRoomYoutubeDTO> selectYutbList(String refRoomIdx) {
		return getSqlSession().selectList("kr.maxerve.admin.activity.YoutubeDAO.selectYutbList", refRoomIdx);
	}

}
