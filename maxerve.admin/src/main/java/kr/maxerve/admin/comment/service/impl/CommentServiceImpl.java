package kr.maxerve.admin.comment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxerve.admin.comment.dao.CommentDAO;
import kr.maxerve.admin.comment.service.CommentService;
import kr.maxerve.admin.comment.vo.CommentVO;
import kr.maxerve.dto.CommentDTO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource(name="commentDAO")
	CommentDAO commentDAO;

	@Override
	public List<CommentVO> selectCommentList(CommentDTO cmtDTO) throws Exception {
		return commentDAO.selectCommentList(cmtDTO);
	}

	@Override
	public void deleteComment(CommentDTO vo) throws Exception {
		commentDAO.deleteComment(vo);
	}

	@Override
	public List<CommentVO> selectCommentLikeList(CommentVO cmtVO) throws Exception {
		return commentDAO.selectCommentLikeList(cmtVO);
	}
}
