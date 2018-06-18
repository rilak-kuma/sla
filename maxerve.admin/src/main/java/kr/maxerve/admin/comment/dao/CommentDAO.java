package kr.maxerve.admin.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.maxerve.admin.comment.vo.CommentVO;
import kr.maxerve.admin.framework.BaseDAOSupport;
import kr.maxerve.dto.CommentDTO;

@Repository("commentDAO")
public class CommentDAO extends BaseDAOSupport{

	public List<CommentVO> selectCommentList(CommentDTO cmtDTO) {
		return getSqlSession().selectList("kr.maxerve.admin.comment.commonCodeDAO.selectCommentList",cmtDTO);
	}

	public void deleteComment(CommentDTO vo) {
		getSqlSession().update("kr.maxerve.admin.comment.commonCodeDAO.deleteComment",vo);
	}

	public List<CommentVO> selectCommentLikeList(CommentVO cmtVO) {
		return getSqlSession().selectList("kr.maxerve.admin.comment.commonCodeDAO.selectCommentLikeList",cmtVO);
	}
}
