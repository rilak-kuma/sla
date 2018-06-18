package kr.maxerve.admin.tag.service;

import java.util.List;

import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.TagDTO;

public interface TagService {
	public void insertTagList(List<TagDTO> list) throws Exception;
	public void insertTag(TagDTO vo) throws Exception;
	public void deleteTagList(List<TagDTO> list) throws Exception;
	public void deleteTag(TagDTO vo) throws Exception;
	public void insertAttachTagList(List<AttachTagDTO> list) throws Exception;
	public void insertAttachTag(AttachTagDTO vo) throws Exception;
	public void updateAttachTagList(List<AttachTagDTO> vo) throws Exception;
	public void updateAttachTag(AttachTagDTO vo) throws Exception;
	public void deleteAttachTagList(List<AttachTagDTO> list) throws Exception;
	public void deleteAttachTag(AttachTagDTO vo) throws Exception;
	public List<TagDTO> selectAttachTagList(AttachTagDTO attachTagDTO) throws Exception;
}
