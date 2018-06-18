package kr.maxerve.admin.comment.service;

import java.util.List;

import kr.maxerve.admin.comment.vo.CommentVO;
import kr.maxerve.dto.CommentDTO;

public interface CommentService {

	List<CommentVO> selectCommentList(CommentDTO cmtDTO) throws Exception;

	void deleteComment(CommentDTO vo) throws Exception;

	List<CommentVO> selectCommentLikeList(CommentVO cmtVO) throws Exception;
}
