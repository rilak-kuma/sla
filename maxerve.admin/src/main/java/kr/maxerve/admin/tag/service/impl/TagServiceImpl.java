package kr.maxerve.admin.tag.service.impl;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.tag.dao.TagDAO;
import kr.maxerve.admin.tag.service.TagService;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.TagDTO;

@Service("tagService")
public class TagServiceImpl implements TagService {

	@Resource(name="tagDAO")
	private TagDAO tagDAO;

	@Resource(name="propertiesService")
	private Properties properties;

	@Override
	public void insertTagList(List<TagDTO> list) throws Exception{
		for (int i = 0, len= list.size(); i < len; i++) {
			tagDAO.insertTag(list.get(i));
		}
	}
	
	@Override
	public void insertTag(TagDTO vo) throws Exception{
		tagDAO.insertTag(vo);
	}
	
	@Override
	public void deleteTagList(List<TagDTO> list) throws Exception{
		for (int i = 0, len= list.size(); i < len; i++) {
			tagDAO.deleteTag(list.get(i));
		}
	}
	
	@Override
	public void deleteTag(TagDTO vo) throws Exception{
		tagDAO.deleteTag(vo);
	}

	@Override
	public void insertAttachTagList(List<AttachTagDTO> list) throws Exception{
		for (int i = 0, len= list.size(); i < len; i++) {
			tagDAO.insertAttachTag(list.get(i));
		}
	}

	@Override
	public void insertAttachTag(AttachTagDTO vo) throws Exception{
		tagDAO.insertAttachTag(vo);
	}

	@Override
	public void updateAttachTagList(List<AttachTagDTO> list) throws Exception{
		for (int i = 0, len= list.size(); i < len; i++) {
			tagDAO.updateAttachTag(list.get(i));
		}
	}

	@Override
	public void updateAttachTag(AttachTagDTO vo) throws Exception{
		tagDAO.updateAttachTag(vo);
	}

	@Override
	public void deleteAttachTagList(List<AttachTagDTO> list) throws Exception{
		for (int i = 0, len= list.size(); i < len; i++) {
			tagDAO.deleteAttachTag(list.get(i));
		}
	}

	@Override
	public void deleteAttachTag(AttachTagDTO vo) throws Exception{
		tagDAO.deleteAttachTag(vo);
	}

	@Override
	public List<TagDTO> selectAttachTagList(AttachTagDTO vo) throws Exception{
		return tagDAO.selectAttachTagList(vo);
	}
}
