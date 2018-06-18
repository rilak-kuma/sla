/*******************************************************************************
*  Project      : BGF Retail Mobile Store Management
*  Program ID   : CommonDAO.java
*  Description  : DAO의 자동화 설정
*
********************************************************************************
*  Program History
*  Date        Author    Description
*  ----------  --------  --------------------------------------------------------
*  2014-08-28  LEEC.J    Created.
*******************************************************************************/

package kr.maxerve.admin.tag.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.TagDTO;

@Repository("tagDAO")
public class TagDAO extends BaseDAOSupport {

	public void insertTag(TagDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.tag.tagDAO.insertTag", vo);
	}
	public void deleteTag(TagDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.tag.tagDAO.deleteTag", vo);
	}

	public void insertAttachTag(AttachTagDTO vo) throws Exception {
		getSqlSession().insert("kr.maxerve.admin.tag.tagDAO.insertAttachTag", vo);
	}

	public void updateAttachTag(AttachTagDTO vo) throws Exception {
		getSqlSession().update("kr.maxerve.admin.tag.tagDAO.updateAttachTag", vo);
	}

	public void deleteAttachTag(AttachTagDTO vo) throws Exception {
		getSqlSession().delete("kr.maxerve.admin.tag.tagDAO.deleteAttachTag", vo);
	}

	public List<TagDTO> selectAttachTagList(AttachTagDTO vo) throws Exception {
		return getSqlSession().selectList("kr.maxerve.admin.tag.tagDAO.selectAttachTagList", vo);
	}
}
